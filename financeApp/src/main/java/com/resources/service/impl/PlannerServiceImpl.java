package com.resources.service.impl;

import com.resources.dto.*;
import com.resources.entity.Planner;
import com.resources.repository.PlannerRepository;
import com.resources.service.*;
import com.resources.service.config.ConfigService;
import static com.resources.service.impl.PlannerStatusEnum.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Praca
 */
@Service
public class PlannerServiceImpl extends ConfigService implements PlannerService {

    private double scheduleAmount;
    private double realExpenses;
    private double difference;

    @Autowired
    PlannerRepository plannerRepository;

    @Autowired
    TransactionService transactionService;

    @Autowired
    CategoryService categoryService;

    List<HistoryTradeDto> historyTradeList;
    List<CategoryDto> categoryList;

    @Override
    public void savePlanner(Planner planner) {
        plannerRepository.save(planner);
    }

    @Override
    public List<PlannerDto> findAll() {
        historyTradeList = transactionService.findAllTransactionForUser(getUserEmail());
        categoryList = categoryService.findAllCategory();
        List<Planner> planner = plannerRepository.findAllByUserId(getUserId(getUserEmail()));
        List<PlannerDto> plannerDto = planner.stream().map(budgetPlan -> mapToPlannerDto(budgetPlan)).toList();

        return plannerDto;
    }

    private PlannerDto mapToPlannerDto(Planner planner) {
        PlannerDto plannerDto = new PlannerDto();

        scheduleAmount = planner.getScheduledAmount();
        realExpenses = getRealExpenses(planner);
        difference = scheduleAmount + realExpenses;

        plannerDto.setId(planner.getId());
        plannerDto.setUserId(planner.getUserId());
        plannerDto.setCategoryId(planner.getCategoryId());
        plannerDto.setCategory(getCategory(planner));
        plannerDto.setScheduledAmount(scheduleAmount);
        plannerDto.setRealExpenses(realExpenses);
        plannerDto.setDifference(difference);
        plannerDto.setStatus(getStatusName());
        plannerDto.setTitle(planner.getTitle());
        return plannerDto;
    }

    private double getRealExpenses(Planner planner) {
        return historyTradeList.stream()
                .filter(category -> category.getProduct().getCategory().getCategoryId() == planner.getCategoryId())
                .map(amount -> amount.getAmount())
                .reduce(0.0, Double::sum);
    }

    private String getStatusName() {
        String statusName = UNKNOW.getStatusName();
        for (PlannerStatusEnum enumValues : PlannerStatusEnum.values()) {
            if (enumValues.getStatusId() == getStatusId()) {
                statusName = enumValues.getStatusName();
            }
        }
        return statusName;
    }

    private int getStatusId() {
        if (difference > 0) {
            return MORE.getStatusId();
        }
        if (difference == 0) {
            return EXELLENT.getStatusId();
        }
        if (difference < 0) {
            return LESS.getStatusId();
        }
        return UNKNOW.getStatusId();
    }

    private CategoryDto getCategory(Planner planner) {
        for (CategoryDto category : categoryList) {
            if (category.getCategoryId() == planner.getCategoryId()) {
                return category;
            }
        }
        return categoryList.get(planner.getCategoryId());
    }
}

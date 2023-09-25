package com.resources.service.impl;

import com.resources.dto.*;
import com.resources.dto.dtoMapper.PlannerDtoMapper;
import com.resources.entity.Planner;
import com.resources.repository.PlannerRepository;
import com.resources.service.*;
import com.resources.service.config.ConfigService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Praca
 */
@Service
public class PlannerServiceImpl extends ConfigService implements PlannerService {

    @Autowired
    PlannerRepository plannerRepository;

    @Autowired
    TransactionService transactionService;

    @Autowired
    CategoryService categoryService;

    PlannerSupportFieldsDto plannerSupportFields;

    @Override
    public void savePlanner(Planner planner) {
        plannerRepository.save(planner);
    }

    @Override
    public List<PlannerDto> findAll() {
        plannerSupportFields.setHistoryTradeList(transactionService.findAllTransactionForUser(getUserEmail()));
        plannerSupportFields.setCategoryList(categoryService.findAllCategory());
        List<Planner> planner = plannerRepository.findAllByUserId(getUserId(getUserEmail()));

        return PlannerDtoMapper.mapToPlannerDtos(planner, plannerSupportFields);
    }
}
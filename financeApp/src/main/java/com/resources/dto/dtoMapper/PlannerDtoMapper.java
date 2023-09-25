package com.resources.dto.dtoMapper;

import com.resources.dto.*;
import com.resources.entity.Planner;
import com.resources.service.impl.PlannerStatusEnum;
import static com.resources.service.impl.PlannerStatusEnum.*;
import java.util.List;

/**
 *
 * @author szela
 */
public class PlannerDtoMapper {

    private static double scheduleAmount;
    private static double realExpenses;
    private static double difference;
    private static PlannerSupportFieldsDto plannerSupportFields;

    private PlannerDtoMapper() {

    }

    public static List<PlannerDto> mapToPlannerDtos(List<Planner> planner, PlannerSupportFieldsDto plannerSupportFields) {
        PlannerDtoMapper.plannerSupportFields = plannerSupportFields;
        return planner.stream().map(budgetPlan -> mapToPlannerDto(budgetPlan)).toList();
    }

    private static PlannerDto mapToPlannerDto(Planner planner) {
        scheduleAmount = planner.getScheduledAmount();
        realExpenses = getRealExpenses(planner);
        difference = scheduleAmount + realExpenses;
        return PlannerDto.builder()
                .id(planner.getId())
                .userId(planner.getUserId())
                .categoryId(planner.getCategoryId())
                .category(getCategory(planner))
                .scheduledAmount(scheduleAmount)
                .realExpenses(realExpenses)
                .difference(difference)
                .status(getStatusName())
                .title(planner.getTitle())
                .build();
    }

    private static double getRealExpenses(Planner planner) {
        return plannerSupportFields.getHistoryTradeList().stream()
                .filter(category -> category.getProduct().getCategory().getCategoryId() == planner.getCategoryId())
                .map(amount -> amount.getAmount())
                .reduce(0.0, Double::sum);
    }

    private static String getStatusName() {
        String statusName = UNKNOW.getStatusName();
        for (PlannerStatusEnum enumValues : PlannerStatusEnum.values()) {
            if (enumValues.getStatusId() == getStatusId()) {
                statusName = enumValues.getStatusName();
            }
        }
        return statusName;
    }

    private static int getStatusId() {
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

    private static CategoryDto getCategory(Planner planner) {
        for (CategoryDto category : plannerSupportFields.getCategoryList()) {
            if (category.getCategoryId() == planner.getCategoryId()) {
                return category;
            }
        }
        return plannerSupportFields.getCategoryList().get(planner.getCategoryId());
    }
}

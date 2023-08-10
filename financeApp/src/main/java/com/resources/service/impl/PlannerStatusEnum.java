package com.resources.service.impl;

import lombok.Getter;

/**
 *
 * @author Praca
 */
@Getter
public enum PlannerStatusEnum {

    LESS(-1, "You spend more"),
    EXELLENT(0, "Excellent"),
    MORE(1, "You spend less"),
    UNKNOW(2, "Status unknow");

    private final int statusId;
    private final String statusName;

    PlannerStatusEnum(int statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }
}

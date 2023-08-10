package com.resources.service.impl;

/**
 *
 * @author Praca
 */
public enum FeesStatusEnum {
    UNPAID(0, "UNPAID"),
    PAID(1, "PAID"),
    UNKNOW(2, "UNKNOW");

    private final int feesStatusId;
    private final String feesStatusName;

    FeesStatusEnum(int feesStatusId, String feesStatusName) {
        this.feesStatusId = feesStatusId;
        this.feesStatusName = feesStatusName;
    }
}

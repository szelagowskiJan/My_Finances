package com.resources.service.impl;

/**
 *
 * @author Praca
 */
public enum FeesStatusEnum {
    UNPAID(0, "UNPAID"),
    PAID(1, "PAID"),
    UNKNOW(2, "UNKNOW");

    public final int feesStatusId;
    public final String feesStatusName;

    FeesStatusEnum(int feesStatusId, String feesStatusName) {
        this.feesStatusId = feesStatusId;
        this.feesStatusName = feesStatusName;
    }
}

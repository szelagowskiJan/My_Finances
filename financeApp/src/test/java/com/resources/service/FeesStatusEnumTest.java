package com.resources.service;

import com.resources.service.impl.FeesStatusEnum;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;

/**
 *
 * @author Praca
 */
public class FeesStatusEnumTest {

    @InjectMocks
    FeesStatusEnum feesStatusEnum;

    @Test
    public void feesStatusEnum_UNPAIDValue_success() {
        Assertions.assertEquals(0, feesStatusEnum.UNPAID.feesStatusId);
    }

    @Test
    public void feesStatusEnum_UNPAIDName_success() {
        Assertions.assertEquals("UNPAID", feesStatusEnum.UNPAID.feesStatusName);
    }

    @Test
    public void feesStatusEnum_PAIDValue_success() {
        Assertions.assertEquals(1, feesStatusEnum.PAID.feesStatusId);
    }

    @Test
    public void feesStatusEnum_PAIDName_success() {
        Assertions.assertEquals("PAID", feesStatusEnum.PAID.feesStatusName);
    }

    @Test
    public void feesStatusEnum_UNKNOWValue_success() {
        Assertions.assertEquals(2, feesStatusEnum.UNKNOW.feesStatusId);
    }

    @Test
    public void feesStatusEnum_UNKNOWName_success() {
        Assertions.assertEquals("UNKNOW", feesStatusEnum.UNKNOW.feesStatusName);
    }
}

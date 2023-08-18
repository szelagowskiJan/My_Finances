package com.resources.service.serviceConfig;

import com.resources.entity.BillsWallet;
import java.util.Date;

/**
 *
 * @author Praca
 */
public class BillsWalletServiceConfig {

    protected BillsWallet getCorectDetailsBillsWallet() {
        Date dtFrom = new Date();
        dtFrom.setTime(61202516585000L);
        Date dtTo = new Date();
        dtTo.setTime(61202516595000L);
        BillsWallet billsWallet = new BillsWallet();
        billsWallet.setId(5);
        billsWallet.setTitle("Gas");
        billsWallet.setAmount(500);
        billsWallet.setDtTo(dtTo);
        billsWallet.setDtFrom(dtFrom);
        billsWallet.setUserId(3);
        return billsWallet;
    }
}

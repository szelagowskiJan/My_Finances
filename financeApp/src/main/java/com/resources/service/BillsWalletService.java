package com.resources.service;

import com.resources.entity.BillsWallet;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Praca
 */
@Service
public interface BillsWalletService {

    List<BillsWallet> findAll();

    void save(BillsWallet billsWallet);

    void removeWallet(int plannedExpensesId);

    double getWalletBalance();
}

package com.resources.service.impl;

import com.resources.entity.BillsWallet;
import com.resources.repository.BillsWalletRepository;
import com.resources.service.BillsWalletService;
import com.resources.service.config.ConfigService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Praca
 */
@Service
public class BillsWalletServiceImpl extends ConfigService implements BillsWalletService{

    @Autowired
    private BillsWalletRepository billsWalletRepository;
    
    @Override
    public List<BillsWallet> findAll() {
        return billsWalletRepository.findByUserId(getUserId(getUserEmail()));
    }

    @Override
    public void save(BillsWallet billsWallet) {
        billsWalletRepository.save(billsWallet);
    }
    
    @Override
    public void removeWallet(int plannedExpensesId) {
        billsWalletRepository.deleteById(plannedExpensesId);
    }
    
    @Override
    public double getWalletBalance() {
        List<BillsWallet> allBillsWallet = findAll();
        return allBillsWallet.stream()
                .map(balance -> balance.getAmount()).reduce(0.0, Double::sum);
    }
}

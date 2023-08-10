package com.resources.service.impl;

import com.resources.dto.HistoryTradeDto;
import com.resources.repository.HistoryTradeRepository;
import com.resources.service.BillsWalletService;
import com.resources.service.SummaryService;
import com.resources.service.config.ConfigService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Praca
 */
@Service
public class SummaryServiceImpl extends ConfigService implements SummaryService {

    @Autowired
    private HistoryTradeRepository historyTradeRepository;

    @Autowired
    private BillsWalletService billsWalletService;

    @Override
    public double getBalance() {
        List<HistoryTradeDto> mounthBalance = findAllTransactionForUserInThisMounth(getUserEmail());
        double sumAllTransactionAmount = mounthBalance.stream()
                .map(balance -> balance.getAmount())
                .reduce(0.0, Double::sum);
        double sumAllBillsWalletAmount = billsWalletService.getWalletBalance();
        return sumAllTransactionAmount - sumAllBillsWalletAmount;
    }

    @Override
    public double getFinancialIncome() {
        List<HistoryTradeDto> mounthBalance = findAllTransactionForUserInThisMounth(getUserEmail());
        return mounthBalance.stream()
                .filter(category -> category.getProduct().getCategory().getCategoryId() == CategoryEnum.PROFIT.categoryId)
                .map(balance -> balance.getAmount()).reduce(0.0, Double::sum);
    }

    @Override
    public double getExpenses() {
        List<HistoryTradeDto> mounthBalance = findAllTransactionForUserInThisMounth(getUserEmail());
        return mounthBalance.stream()
                .filter(category -> category.getProduct().getCategory().getCategoryId() != CategoryEnum.PROFIT.categoryId)
                .map(balance -> balance.getAmount()).reduce(0.0, Double::sum);
    }

    @Override
    public double getIncomePerHour() {
        List<HistoryTradeDto> mounthBalance = findAllTransactionForUserInThisMounth(getUserEmail());
        double financialIncome = mounthBalance.stream()
                .filter(category -> category.getProduct().getCategory().getCategoryId() == CategoryEnum.PROFIT.categoryId)
                .map(balance -> balance.getAmount()).reduce(0.0, Double::sum);
        BigDecimal incomePerHour = new BigDecimal(financialIncome / getWorkHourInMonth()).setScale(2, RoundingMode.HALF_UP);
        return incomePerHour.doubleValue();
    }

    private List<HistoryTradeDto> findAllTransactionForUserInThisMounth(String email) {
        List<HistoryTradeDto> allTransaction = allTransactionForUser(email);
        return allTransaction
                .stream()
                .filter(transaction -> dateToCalendar(transaction.getTradeDt()).get(Calendar.YEAR) == rightNow.get(Calendar.YEAR)
                && dateToCalendar(transaction.getTradeDt()).get(Calendar.MONTH) == rightNow.get(Calendar.MONTH))
                .toList();
    }

    private int getWorkHourInMonth() {
        return 168;
    }
}

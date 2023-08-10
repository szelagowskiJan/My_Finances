package com.resources.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author Praca
 */
@Service
public interface SummaryService {

    double getBalance();

    double getFinancialIncome();

    double getExpenses();

    double getIncomePerHour();
}

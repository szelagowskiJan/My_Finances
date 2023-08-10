package com.resources.controller;

import com.resources.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Praca
 */
@Controller
public class SummaryController {

    @Autowired
    private SummaryService summaryService;

    @GetMapping("/summary")
    public String summary(Model model) {
        double balance = summaryService.getBalance();
        double financialIncome = summaryService.getFinancialIncome();
        double expenses = summaryService.getExpenses();
        double incomePerHour = summaryService.getIncomePerHour();

        model.addAttribute("balance", balance);
        model.addAttribute("financialIncome", financialIncome);
        model.addAttribute("expenses", expenses);
        model.addAttribute("incomePerHour", incomePerHour);
        return "summary";
    }
}

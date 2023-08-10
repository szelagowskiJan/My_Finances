package com.resources.controller;

import com.resources.controller.config.ConfigController;
import com.resources.entity.BillsWallet;
import com.resources.service.BillsWalletService;
import com.resources.service.SummaryService;
import jakarta.validation.Valid;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Praca
 */
@Controller
public class BillsWalletController extends ConfigController {

    @Autowired
    private BillsWalletService billsWalletService;

    @Autowired
    private SummaryService summaryService;

    @GetMapping("/billsWallet")
    public String billsWallet(Model model) {
        List<BillsWallet> wallet = billsWalletService.findAll();
        model.addAttribute("wallet", wallet);
        return "billsWallet";
    }

    @PostMapping("/billsWallet/add")
    public String addBillsWallet(@Valid @ModelAttribute("billsWallet") BillsWallet billsWallet, BindingResult result) {
        setDateFrom(billsWallet);
        billsWallet.setUserId(getUserEntity().getUserId());
        if (!validate(billsWallet).isEmpty()) {
            return validate(billsWallet);
        }
        billsWalletService.save(billsWallet);
        return "redirect:/billsWallet?success";
    }

    @PostMapping("/billsWallet/removeWallet")
    public String walletRemove(@RequestParam String id) {
        int walletIdToRemove = getId(id);
        if (walletIdToRemove > 0) {
            billsWalletService.removeWallet(walletIdToRemove);
            return "redirect:/billsWallet?success";
        }
        return "redirect:/billsWallet?errorRemoveWallet";
    }

    private BillsWallet setDateFrom(BillsWallet billsWallet) {
        Calendar calendar = Calendar.getInstance();
        billsWallet.setDtFrom(calendar.getTime());
        return billsWallet;
    }

    private String validate(BillsWallet billsWallet) {
        if (billsWallet.getDtFrom() == null || billsWallet.getDtTo() == null || billsWallet.getTitle().equals("") || billsWallet.getAmount() <= 0) {
            return "redirect:/billsWallet?emptyField";
        }
        if (billsWallet.getDtFrom().after(billsWallet.getDtTo())) {
            return "redirect:/billsWallet?wrongDate";
        }
        if (summaryService.getBalance() - billsWallet.getAmount() < 0) {
            return "redirect:/billsWallet?notEnough";
        }
        return "";
    }
}

package com.resources.controller;

import com.resources.controller.config.ConfigController;
import com.resources.dto.*;
import com.resources.entity.*;
import com.resources.service.*;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Praca
 */
@Controller
public class TransactionController extends ConfigController{
    
    @Autowired
    private TransactionService transactionService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private UserService userService;
    
    private HistoryTradeDto historyTransactionToUpdate;
    
    @GetMapping("/transaction")
    public String transaction(Model model) {
        List<HistoryTradeDto> historyList = transactionService.findAllTransactionForUser(getUserEmail());
        List<CategoryDto> categoryList = categoryService.findAllCategory();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("historyList", historyList);
        model.addAttribute("updateTransaction", historyTransactionToUpdate);
        return "transaction";
    }
    
    @PostMapping("/transaction/add")
    public String transaction(@Valid @ModelAttribute("historyTrade") HistoryTrade historyTrade, BindingResult result) {
        historyTrade.setUserId(getUserEntity().getUserId());

        if (historyTrade.getTradeDt() == null || historyTrade.getTitle().isEmpty() || historyTrade.getAmount() == 0) {
            return "redirect:/transaction?emptyField";
        }
        transactionService.saveTransaction(historyTrade);
        return "redirect:/transaction?success";
    }
    
    @PostMapping("/transaction/removeTransaction")
    public String transactionRemove(@RequestParam String id) {
        int transactionIdToRemove = getId(id);
        if (transactionIdToRemove > 0) {
            transactionService.removeTransaction(transactionIdToRemove);
            return "redirect:/transaction?success";
        }
        return "redirect:/transaction?errorRemoveTransaction";
    }
    
    @GetMapping("/transaction/addUpdateTransactionForm")
    public String transactionUpdate(@RequestParam String id, Model model) {
        int transactionIdToUpdate = getId(id);
        if (transactionIdToUpdate > 0) {
            historyTransactionToUpdate = transactionService.findById(transactionIdToUpdate);
            model.addAttribute("updateTransaction", historyTransactionToUpdate);
            return "redirect:/transaction?update";
        }
        return "redirect:/transaction?errorUpdate";
    }
    
    @PostMapping("/transaction/update")
    public String transactionUpdate(@Valid @ModelAttribute("updateTransaction") HistoryTrade historyTrade) {
        historyTrade.setUserId(getUserEntity().getUserId());
        
        if (historyTrade.getTradeDt() == null || historyTrade.getTitle().isEmpty() || historyTrade.getAmount() == 0){
            return "redirect:/transaction?emptyField";
        }
        transactionService.saveTransaction(historyTrade);
        return "redirect:/transaction?success";
    }
    
    @GetMapping("/products")
    public String products(Model model) {
        List<ProductDto> productsList = productService.findAllProducts();
        model.addAttribute("products", productsList);
        return "transaction";
    }
    
    @PostMapping("/products")
    public String products(Model model, @Valid @ModelAttribute("product") Product product) {
        List<ProductDto> productsList = productService.findAllProducts();
        model.addAttribute("products", productsList);
        return "transaction";
    }
}

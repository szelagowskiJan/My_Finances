package com.resources.controller;

import com.resources.controller.config.ConfigController;
import com.resources.dto.HistoryFeesDto;
import com.resources.dto.MediaDto;
import com.resources.entity.HistoryFees;
import com.resources.service.HistoryFeesService;
import com.resources.service.MediaService;
import com.resources.service.SummaryService;
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
public class FeesController extends ConfigController {

    @Autowired
    private HistoryFeesService historyFeesService;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private SummaryService summaryService;

    @GetMapping("/fees")
    public String fees(Model model) {
        List<HistoryFeesDto> feesList = historyFeesService.findAllHistoryFees();
        List<MediaDto> mediaList = mediaService.findAllMedia();
        model.addAttribute("feesList", feesList);
        model.addAttribute("mediaList", mediaList);
        return "fees";
    }

    @PostMapping("/fees/add")
    public String fees(@Valid @ModelAttribute("feesList") HistoryFees fees, BindingResult bindingResult) {
        fees.setUserId(getUserEntity().getUserId());
        String validateFeesDetails = validateFeesDetails(fees);
        if (!validateFeesDetails.isEmpty()) {
            return validateFeesDetails;
        }
        historyFeesService.addNewHistoryFees(fees);
        return "redirect:/fees?success";
    }

    private String validateFeesDetails(HistoryFees fees) {
        if (fees.getAmountFees() <= 0) {
            return "redirect:/fees?wrongAmount";
        }
        if (fees.getFeesDt() == null || fees.getDistributorsId() == 0 || fees.getTitle().isEmpty()) {
            return "redirect:/fees?emptyValue";
        }
        if (fees.getAmountFees() > summaryService.getBalance()) {
            return "redirect:/fees?notEnoughtMoney";
        }
        return "";
    }
}

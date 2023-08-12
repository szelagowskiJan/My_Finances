package com.resources.controller;

import com.resources.controller.config.ConfigController;
import com.resources.dto.*;
import com.resources.entity.Planner;
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
public class PlannerController extends ConfigController{
    
    @Autowired
    private PlannerService plannerService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/planner")
    public String planner(Model model)
    {
        List<PlannerDto> budgetList = plannerService.findAll();
        List<CategoryDto> categoryList = categoryService.findAllCategory();
        model.addAttribute("budgetList", budgetList);
        model.addAttribute("categoryList", categoryList);
        return "planner";
    }
    
    @PostMapping("/planner/add")
    public String planner(@Valid @ModelAttribute("Planner") Planner planner, BindingResult binding)
    {
        if(planner.getCategoryId() == 0 || planner.getTitle().isEmpty() || planner.getScheduledAmount() <= 0)
        {
            return "redirect:/planner?success";
        }
        planner.setUserId(getUserEntity().getUserId());
        plannerService.savePlanner(planner);
        return "redirect:/planner?success";
    }
}

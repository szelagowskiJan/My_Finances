package com.resources.controller;

import com.resources.dto.UserDto;
import com.resources.service.PasswordForgottenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Praca
 */
@Controller
public class NewAccountPasswordController {

    @Autowired
    private PasswordForgottenService passwordForgottenService;

    String code;

    @GetMapping("/newAccountPassword")
    public String newAccountPassword(@Param("code") String code, Model model) {
        this.code = code;
        UserDto user = new UserDto();
        model.addAttribute("userDto", user);
        return "newAccountPassword";
    }

    @PostMapping("/newAccount")
    public String changePassword(@Valid @ModelAttribute("userDto") UserDto userDto, HttpServletRequest request, BindingResult result) {
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            return "newAccountPassword";
        }
        boolean passwordChange = passwordForgottenService.saveNewPassword(userDto, code);
        if (passwordChange) {
            return "redirect:/newAccountPassword?passwordChangeSuccess";
        }
        return "redirect:/newAccountPassword?passwordChangeNotSuccess";
    }
}

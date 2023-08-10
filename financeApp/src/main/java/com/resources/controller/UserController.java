package com.resources.controller;

import com.resources.entity.UserEntity;
import com.resources.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String user(Model model) {

        UserEntity user = userService.findUserEntityByEmail(getEmail());
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/user")
    public String user(@Valid @ModelAttribute("user") UserEntity user, BindingResult result) {
        user.setEmail(userService.findUserEntityByEmail(getEmail()).getEmail());
        if (user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getEmail().isEmpty()) {
            return "redirect:/user?emptyField";
        }
        userService.changeUserData(user);
        return "redirect:/user?success";
    }

    private String getEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}

package com.resources.controller;

import com.resources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Praca
 */
@Controller
public class ConfirmRegistrationController {

    @Autowired
    UserService userService;

    @GetMapping("/confirmRegistration")
    public String confirmRegistration() {
        return "confirmRegistration";
    }

    @GetMapping("/confirm")
    public String confirm(@Param("code") String code) {
        if (userService.verify(code)) {
            return "redirect:/confirmRegistration?successConfirm";
        } else {
            return "redirect:/confirmRegistration?errorConfirm";
        }
    }
}

package com.resources.controller;

import com.resources.controller.config.ConfigController;
import com.resources.dto.*;
import com.resources.entity.UserEntity;
import com.resources.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.regex.*;
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
public class RegistryController extends ConfigController {

    @Autowired
    private UserService userService;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @GetMapping("/registration")
    public String registration(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, HttpServletRequest request, BindingResult result) throws UnsupportedEncodingException, MessagingException {
        String validateRegistry = validateRegistrationField(userDto, result);
        if (!validateRegistry.isEmpty()) {
            return validateRegistry;
        }
        MailDto mailDto = new MailDto();
        mailDto.setSiteUrl(getSiteURL(request));
        userService.register(userDto, mailDto);
        return "redirect:/registration?success";
    }

    public static boolean validateAddressEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    private String validateRegistrationField(UserDto userDto, BindingResult result) {
        UserEntity existingUser = userService.findUserEntityByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            if (existingUser.getEmail().equals(userDto.getEmail())) {
                return "redirect:/registration?emailExist";
            }
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }
        if (userDto.getFirstName().isEmpty() || userDto.getLastName().isEmpty() || userDto.getEmail().isEmpty() || userDto.getPassword().isEmpty()) {
            return "redirect:/registration?emptyField";
        }
        if (!validateAddressEmail(userDto.getEmail())) {
            return "redirect:/registration?wrongEmail";
        }
        if (userDto.getPassword().isEmpty()) {
            return "redirect:/registration?passwordsIsEmpty";
        }
        if (userDto.getPassword().length() < 7) {
            return "redirect:/registration?passwordsTooShort";
        }
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            return "redirect:/registration?passwordsNotSame";
        }
        return "";
    }
}

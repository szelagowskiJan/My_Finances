package com.resources.controller;

import com.resources.controller.config.ConfigController;
import com.resources.dto.*;
import com.resources.entity.UserEntity;
import com.resources.service.*;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.io.UnsupportedEncodingException;
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
public class PasswordForgottenController extends ConfigController {

    @Autowired
    private PasswordForgottenService passwordForgottenService;

    @Autowired
    private UserService userService;

    private UserDto userDtoMappedFromUserEntity;

    @GetMapping("/passwordForgotten")
    public String forgotten(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "passwordForgotten";
    }

    @PostMapping("/passwordForgotten")
    public String forgotten(@Valid @ModelAttribute("user") UserDto userDto, HttpServletRequest request, BindingResult result) {
        String validate = validateMailField(userDto, result);
        if (!validate.isEmpty()) {
            return validate;
        }
        MailDto mailDto = new MailDto();
        mailDto.setSiteUrl(getSiteURL(request));
        return sendMail(mailDto);
    }

    private String validateMailField(UserDto userDto, BindingResult result) {
        UserEntity existingUser = userService.findUserEntityByEmail(userDto.getEmail());

        if (existingUser == null && existingUser.getEmail() == null && existingUser.getEmail().isEmpty()) {
            if (existingUser.getEmail().equals(userDto.getEmail())) {
                return "redirect:/registration?emailNotExist";
            }
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }
        if (userDto.getEmail().isEmpty()) {
            return "redirect:/registration?emptyField";
        }
        userDtoMappedFromUserEntity = mapUserEntityToUserDto(existingUser);
        return "";
    }

    private UserDto mapUserEntityToUserDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setUserId(userEntity.getUserId());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(userEntity.getPassword());
        userDto.setUserRole(userEntity.getUserRole());
        userDto.setVerificationCode(userEntity.getVerificationCode());
        userDto.setEnabled(userEntity.isEnabled());
        userDto.setBalance(userEntity.getBalance());
        return userDto;
    }

    private String sendMail(MailDto mailDto) {
        try {
            passwordForgottenService.sendMail(userDtoMappedFromUserEntity, mailDto);
        } catch (MessagingException ex) {
            System.err.println("Error in sending an e-mail requesting a password change: " + ex);
            return "redirect:/passwordForgotten?failSendMail";
        } catch (UnsupportedEncodingException ex) {
            System.err.println("Error in sending an e-mail requesting a password change: " + ex);
            return "redirect:/passwordForgotten?failSendMail";
        }
        return "redirect:/passwordForgotten?success";
    }
}

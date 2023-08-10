package com.resources.service.impl;

import com.resources.config.SpringSecurityConfig;
import com.resources.dto.MailDto;
import com.resources.dto.UserDto;
import com.resources.entity.UserEntity;
import com.resources.mail.Mail;
import com.resources.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.resources.service.PasswordForgottenService;
import com.resources.service.config.ConfigService;
import jakarta.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

/**
 *
 * @author Praca
 */
@Service
public class PasswordForgottenServiceImpl extends ConfigService implements PasswordForgottenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMail(UserDto userDto, MailDto mailDto) throws UnsupportedEncodingException, MessagingException {
        userDto = setValuesForUserWhoForgotPassword(userDto);
        saveUserEntity(userDto);
        Mail mail = new Mail();
        mailDto = getMailDetails(mailDto);
        mail.send(userDto, mailDto);
    }

    @Override
    public boolean saveNewPassword(UserDto userDto, String verificationCode) {
        UserEntity user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            user.setPassword(SpringSecurityConfig.passwordEncoder().encode(userDto.getPassword()));
            userRepository.save(user);
            return true;
        }
    }

    private UserEntity saveUserEntity(UserDto userDto) {
        UserEntity user = new UserEntity();
        user = userDtoMappedToUserEntity(userDto);
        return userRepository.save(user);
    }

    private MailDto getMailDetails(MailDto mailDto) {
        mailDto.setSubject("Please confirm the password change");
        mailDto.setContent("Dear [[name]],<br>"
                + "Please click the link below to change your password:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">CHANGE</a></h3>"
                + "Thank you,<br>"
                + "Your Finance App.");
        mailDto.setDetailedPage("newAccountPassword");
        return mailDto;
    }

    private UserDto setValuesForUserWhoForgotPassword(UserDto userDto) {
        String randomCode = UUID.randomUUID().toString();
        userDto.setVerificationCode(randomCode);
        userDto.setEnabled(false);
        return userDto;
    }
}

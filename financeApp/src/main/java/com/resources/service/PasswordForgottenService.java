package com.resources.service;

import com.resources.dto.MailDto;
import com.resources.dto.UserDto;
import jakarta.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Praca
 */
@Service
public interface PasswordForgottenService {

    void sendMail(UserDto userDto, MailDto mailDto) throws UnsupportedEncodingException, MessagingException;

    boolean saveNewPassword(UserDto userDto, String verificationCode);
}

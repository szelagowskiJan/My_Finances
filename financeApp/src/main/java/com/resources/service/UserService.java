package com.resources.service;

import com.resources.dto.*;
import com.resources.entity.UserEntity;
import jakarta.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Praca
 */
@Service
public interface UserService {

    List<UserDto> findAllUsers();

    UserEntity saveUserEntity(UserDto userDto);

    UserEntity findUserEntityByEmail(String email);

    void changeUserData(UserEntity userDto);

    void register(UserDto userDto, MailDto mailDto) throws UnsupportedEncodingException, MessagingException;

    boolean verify(String verificationCode);
}

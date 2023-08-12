package com.resources.service.impl;

import com.resources.config.SpringSecurityConfig;
import com.resources.dto.*;
import com.resources.entity.*;
import com.resources.mail.Mail;
import com.resources.repository.*;
import com.resources.service.UserService;
import com.resources.service.config.ConfigService;
import jakarta.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author Praca
 */
@Service
public class UserServiceImpl extends ConfigService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public UserEntity saveUserEntity(UserDto userDto) {
        UserEntity user = new UserEntity();
        user = userDtoMappedToUserEntity(userDto);
        return userRepository.save(user);
    }

    @Override
    public void changeUserData(UserEntity userDto) {
        UserEntity user = findUserEntityByEmail(userDto.getEmail());

        if (!userDto.getFirstName().isEmpty()) {
            user.setFirstName(userDto.getFirstName());
        }
        if (!userDto.getLastName().isEmpty()) {
            user.setLastName(userDto.getLastName());
        }
        userRepository.save(user);
    }

    @Override
    public UserEntity findUserEntityByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .toList();
    }

    @Override
    public void register(UserDto userDto, MailDto mailDto) throws UnsupportedEncodingException, MessagingException {
        userDto = setRegisterDefaultValues(userDto);
        saveUserEntity(userDto);
        Mail mail = new Mail();
        mailDto = getMailDetails(mailDto);
        mail.send(userDto, mailDto);
    }

    @Override
    public boolean verify(String verificationCode) {
        UserEntity user = userRepository.findByVerificationCode(verificationCode);
        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }
    }

    private UserDto mapToUserDto(UserEntity user) {
        Role role = roleRepository.findById(user.getUserRole());

        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setBalance(user.getBalance());
        userDto.setUserRole(role.getId());
        userDto.setEnabled(user.isEnabled());
        return userDto;
    }

    private MailDto getMailDetails(MailDto mailDto) {
        mailDto.setSubject("Please verify your registration");
        mailDto.setContent("Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your Finance App.");
        mailDto.setDetailedPage("confirm");
        return mailDto;
    }

    private UserDto setRegisterDefaultValues(UserDto userDto) {
        String randomCode = UUID.randomUUID().toString();
        userDto.setVerificationCode(randomCode);
        userDto.setEnabled(false);
        userDto.setBalance(0);
        userDto.setPassword(SpringSecurityConfig.passwordEncoder().encode(userDto.getPassword()));
        return userDto;
    }
}
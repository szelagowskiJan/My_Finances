package com.resources.service;

import com.resources.entity.UserEntity;
import com.resources.repository.RoleRepository;
import com.resources.repository.UserRepository;
import com.resources.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

/**
 *
 * @author Praca
 */
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void verify_CodeNull_false() {
        String code = null;
        UserEntity userEntity = null;
        when(userRepository.findByVerificationCode(null)).thenReturn(userEntity);

        boolean result = userService.verify(code);
        Assertions.assertFalse(result);
    }

    @Test
    public void verify_CodeNotNullButUserIsEnabled_false() {
        String code = "aabbcc432432543";
        UserEntity userEntity = new UserEntity();
        userEntity.setEnabled(true);
        when(userRepository.findByVerificationCode(code)).thenReturn(userEntity);

        boolean result = userService.verify(code);
        Assertions.assertFalse(result);
    }

    @Test
    public void verify_CodeNotNullAndUserNotEnabled_true() {
        String code = "s25fd436ghun75x4vg6hgf43";
        UserEntity userEntity = new UserEntity();
        userEntity.setEnabled(false);
        when(userRepository.findByVerificationCode(code)).thenReturn(userEntity);

        boolean result = userService.verify(code);
        Assertions.assertTrue(result);
    }

    @Test
    public void findUserEntityByEmail_correctEmail_userEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("admin@wp.pl");

        when(userRepository.findByEmail("admin@wp.pl")).thenReturn(userEntity);

        UserEntity fullName = userService.findUserEntityByEmail("admin@wp.pl");
        assertEquals("admin@wp.pl", fullName.getEmail());
    }

    @Test
    public void findUserEntityByEmail_wrongEmail_null() {
        UserEntity userEntity = new UserEntity();

        when(userRepository.findByEmail("@wp.pl")).thenReturn(userEntity);

        UserEntity fullName = userService.findUserEntityByEmail("@wp.pl");
        assertEquals(null, fullName.getEmail());
    }
}

package com.resources.service;

import com.resources.config.SpringSecurityConfig;
import com.resources.dto.MailDto;
import com.resources.dto.UserDto;
import com.resources.entity.UserEntity;
import com.resources.repository.*;
import com.resources.service.impl.UserServiceImpl;
import com.resources.service.serviceConfig.ServiceConfig;
import jakarta.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;
import org.junit.rules.ExpectedException;
import static org.mockito.ArgumentMatchers.same;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

/**
 *
 * @author Praca
 */
public class UserServiceImplTest extends ServiceConfig {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private JavaMailSender mailSender;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

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

    @Test
    public void findAllUsers_correctUserEntity_correctUserDto() {

        List<UserEntity> listUserEntity = new ArrayList<>();
        listUserEntity.add(getCorrectUserEntity());

        when(userRepository.findAll()).thenReturn(listUserEntity);
        when(roleRepository.findById(1)).thenReturn(getUserRole());

        List<UserDto> users = userService.findAllUsers();
        Assert.assertNotNull(users);
    }

    @Test
    public void changeUserData_correctUserEntity_UpdateNameAndLastName() {
        UserEntity userEntity = getCorrectUserEntity();

        when(userRepository.findByEmail(userEntity.getEmail())).thenReturn(userEntity);
        userService.changeUserData(userEntity);

        verify(userRepository).save(same(userEntity));
    }

    @Test
    public void changeUserData_UserEntityWithoutFirstNameAndLastName_dontUpdateNameAndLastName() {
        UserEntity userEntityFromDb = getUserEntityWithoutFirstNameAndLastName();
        UserEntity correctUserEntity = getCorrectUserEntity();

        when(userRepository.findByEmail(correctUserEntity.getEmail())).thenReturn(correctUserEntity);
        userService.changeUserData(userEntityFromDb);

        verify(userRepository).save(same(correctUserEntity));
    }

    @Test
    public void changeUserData_UserEntityEmptyFirstNameAndLastName_dontUpdateNameAndLastName() {
        UserEntity userEntityFromDb = getUserEntityEmptyFirstNameAndLastName();
        UserEntity correctUserEntity = getCorrectUserEntity();

        when(userRepository.findByEmail(correctUserEntity.getEmail())).thenReturn(correctUserEntity);
        userService.changeUserData(userEntityFromDb);

        verify(userRepository).save(same(correctUserEntity));
    }

    @Test
    public void saveUserEntity_correctUserEntity_correctSave() {
        UserDto userDto = getCorrectUserDto();
        UserEntity userEntity = getCorrectUserEntity();
        when(userRepository.save(any())).thenReturn(userEntity);
        UserEntity entity = userService.saveUserEntity(userDto);
        Assert.assertNotNull(entity);
    }
    
    @Test
    public void register_correctRegisterValuesWithoutEmail_MessagingException() throws UnsupportedEncodingException, MessagingException {
        UserDto userDto = getCorrectUserDto();
        userDto.setEmail("@wp.pl");
        MailDto mailDto = new MailDto();
        UserEntity userEntity = getCorrectUserEntity();
        UUID uuid = UUID.randomUUID();
        when(userRepository.save(any())).thenReturn(userEntity);
        exception.expect(MessagingException.class);
        userService.register(userDto, mailDto);
    }
}

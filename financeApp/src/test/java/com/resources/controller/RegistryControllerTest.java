package com.resources.controller;

import com.resources.entity.UserEntity;
import com.resources.service.UserService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.validation.BindingResult;

/**
 *
 * @author Praca
 */
@WebMvcTest(RegistryController.class)
public class RegistryControllerTest {

    @Autowired
    RegistryController controller;

   @MockBean
    UserService userService;
    
    
    @Test
    public void empty_email() {
        boolean result = controller.validateAddressEmail("");

        assertEquals(false, result);
    }
    
    @Test
    public void no_email_name() {
        boolean result = controller.validateAddressEmail("@wp.pl");

        assertEquals(false, result);
    }

    @Test
    public void correct_email() {
        boolean result = controller.validateAddressEmail("pawel@WP.PL");

        assertEquals(true, result);
    }
    
    
}

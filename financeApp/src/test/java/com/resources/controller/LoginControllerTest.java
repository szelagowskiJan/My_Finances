/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.resources.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Praca
 */
public class LoginControllerTest {
    
    @Test
    public void correctLogin() {
        LoginController controller = new LoginController();

        String result = controller.login();

        assertEquals("login", result);

    }
}

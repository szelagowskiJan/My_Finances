package com.resources.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Praca
 */
@Controller
public class SettingsController {
    
    @GetMapping("/settings")
    public String settings() {
        return "settings";
    }
}

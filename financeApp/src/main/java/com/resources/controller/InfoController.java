package com.resources.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Praca
 */
@Controller
public class InfoController {
    
    @GetMapping("/info")
    public String info() {
        return "info";
    }
}

package com.resources.controller.config;

import com.resources.entity.UserEntity;
import com.resources.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Praca
 */
public class ConfigController {

    @Autowired
    private UserService userService;

    protected UserEntity getUserEntity() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userService.findUserEntityByEmail(email);
    }

    protected String getUserEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    protected int getId(String id) {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException ex) {
            System.err.println("Bad format transaction Id:" + ex);
        }
        return 0;
    }

    protected String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}

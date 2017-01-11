package com.ness.automation.service;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ness.automation.authenticatie.AuthUser;

@Component
public class AuthenticationService {

    public long getUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AuthUser) {
            AuthUser user = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return user.getUserID();
        }
        return -1;
    }
}

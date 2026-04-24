package com.assignment4.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final Map<String, String> allowedUsers = new HashMap<>();

    public AuthService() {
        allowedUsers.put("student@university.edu", "pass123");
        allowedUsers.put("admin@university.edu", "admin123");
        allowedUsers.put("thishan@example.com", "pass123");
        allowedUsers.put("user2@example.com", "pass456");
    }

    public boolean isValidCredentials(String email, String password) {
        if (email == null || password == null) {
            return false;
        }

        String expectedPassword = allowedUsers.get(email.trim().toLowerCase());
        return password.equals(expectedPassword);
    }
}

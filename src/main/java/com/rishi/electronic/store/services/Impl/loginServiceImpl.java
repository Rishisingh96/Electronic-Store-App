package com.rishi.electronic.store.services.Impl;

import com.rishi.electronic.store.dto.UserDto;
import com.rishi.electronic.store.entity.Providers;
import com.rishi.electronic.store.services.loginService;
import org.springframework.stereotype.Service;

@Service
public class loginServiceImpl implements loginService {


    @Override
    public void loginUser(UserDto userDto) {
        // Example: Comparing the provider field with a specific provider (e.g., Google)

        // Fix: Check for null before invoking equals()
        if (userDto.getProvider() != null && userDto.getProvider().equals(Providers.GOOGLE)) {
            // Logic for Google login
            System.out.println("User logged in with Google.");
        } else if (userDto.getProvider() != null && userDto.getProvider().equals(Providers.FACEBOOK)) {
            // Logic for Facebook login
            System.out.println("User logged in with Facebook.");
        } else {
            // Default or other provider login logic
            System.out.println("User logged in with another provider.");
        }
    }

    @Override
    public UserDto processGoogleLogin(UserDto userDto) {
// After successful Google OAuth login, set the provider
        userDto.setProvider(Providers.GOOGLE);
        // Further logic like saving user details to the database
        return userDto;
    }
}

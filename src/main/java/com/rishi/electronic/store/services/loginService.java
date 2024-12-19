package com.rishi.electronic.store.services;

import com.rishi.electronic.store.dto.UserDto;

public interface loginService {

    public void loginUser(UserDto userDto);

    public UserDto processGoogleLogin(UserDto userDto);
}

package com.rishi.electronic.store.services;

import com.rishi.electronic.store.dto.RefreshTokenDto;
import com.rishi.electronic.store.dto.UserDto;

public interface RefreshTokenService {
    //create
    RefreshTokenDto createRefreshToken(String username);

    //find by token
    RefreshTokenDto findByToken(String token);

    //verify
    RefreshTokenDto verifyRefreshToken(RefreshTokenDto refreshToken);

    //user
    UserDto getUser(RefreshTokenDto dto);

}

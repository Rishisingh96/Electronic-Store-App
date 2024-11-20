package com.rishi.electronic.store.services.Impl;

import com.rishi.electronic.store.dto.RefreshTokenDto;
import com.rishi.electronic.store.dto.UserDto;
import com.rishi.electronic.store.entity.RefreshToken;
import com.rishi.electronic.store.entity.User;
import com.rishi.electronic.store.exceptions.ResourceNotFoundException;
import com.rishi.electronic.store.repositories.RefreshTokenRepository;
import com.rishi.electronic.store.repositories.UserRepository;
import com.rishi.electronic.store.services.RefreshTokenService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private RefreshTokenRepository refreshTokenRepository;

    private ModelMapper modelMapper;

    private UserRepository userRepository;

    public RefreshTokenServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public RefreshTokenDto createRefreshToken(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        RefreshToken refreshToken = refreshTokenRepository.findByUser(user).orElse(null);
        if(refreshToken == null) {
            refreshToken = RefreshToken
                    .builder()
                    .token(UUID.randomUUID().toString())
                    .expiryDate(Instant.now().plusSeconds(5*24*60*60))
                    .build();
        }else {
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setExpiryDate(Instant.now().plusSeconds(5*24*60*60));
        }

        RefreshToken saveToken = refreshTokenRepository.save(refreshToken);
        return this.modelMapper.map(saveToken, RefreshTokenDto.class);
    }

    @Override
    public RefreshTokenDto findByToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token).orElseThrow(() -> new ResourceNotFoundException("Refresh Token not found "));
        return this.modelMapper.map(refreshToken,RefreshTokenDto.class);
    }

    @Override
    public RefreshTokenDto verifyRefreshToken(RefreshTokenDto refreshToken) {
        var refreshTokens = modelMapper.map(refreshToken, RefreshToken.class);

        if(refreshToken.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(refreshTokens);
            throw new RuntimeException("Refresh Token Expired !!");
        }
        return refreshToken;
    }


    @Override
    public UserDto getUser(RefreshTokenDto dto) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(dto.getToken()).orElseThrow(() -> new ResourceNotFoundException("Token not found"));
        User user = refreshToken.getUser();
        return modelMapper.map(user, UserDto.class);
    }
}

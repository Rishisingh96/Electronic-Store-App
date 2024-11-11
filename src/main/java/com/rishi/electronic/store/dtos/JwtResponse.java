package com.rishi.electronic.store.dtos;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;
    UserDto user;
    private String refreshToken;
}

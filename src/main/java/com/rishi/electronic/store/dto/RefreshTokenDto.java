package com.rishi.electronic.store.dto;

import com.rishi.electronic.store.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class RefreshTokenDto {
    private int id;
    private String token;
    private Instant expiryDate;


}

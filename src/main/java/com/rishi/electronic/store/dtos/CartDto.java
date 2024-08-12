package com.rishi.electronic.store.dtos;

import com.rishi.electronic.store.entites.CartItem;
import com.rishi.electronic.store.entites.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {
    private String cartId;
    private Date createdAt;
    private User user;
    private List<CartItemDto> items = new ArrayList<>();
}

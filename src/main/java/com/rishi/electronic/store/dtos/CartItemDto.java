package com.rishi.electronic.store.dtos;

import com.rishi.electronic.store.entites.Cart;
import com.rishi.electronic.store.entites.Product;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto {

    private int cartItemId;
    private ProductDto product;
    private int quantity;
    private int totalPrice;

}

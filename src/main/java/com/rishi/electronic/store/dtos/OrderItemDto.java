package com.rishi.electronic.store.dtos;

import com.rishi.electronic.store.entites.Order;
import com.rishi.electronic.store.entites.Product;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderItemDto {

    private int orderItemId;

    private int quantity;

    private int totalPrice;

    private ProductDto product;

   // private Order order;
}

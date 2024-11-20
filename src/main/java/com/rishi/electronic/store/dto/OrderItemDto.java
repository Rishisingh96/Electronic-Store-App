package com.rishi.electronic.store.dto;

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

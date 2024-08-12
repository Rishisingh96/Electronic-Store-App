package com.rishi.electronic.store.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateOrderRequest {
    private String cartId;
    private String userId;
    private String orderStatus="PENDING";
    private String paymentStatus ="NOTPAID";
    private String billingAddress;
    private String billingPhone;
    private String billingName;
}

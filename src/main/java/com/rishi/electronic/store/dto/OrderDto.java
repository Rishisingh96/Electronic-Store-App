package com.rishi.electronic.store.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderDto {
    private String orderId;
    private String orderStatus="PENDING";
    private String paymentStatus ="NOTPAID";
    private int orderAmount;
    private String billingAddress;
    private String billingPhone;
    private String billingName;
    private Date orderedDate = new Date();
    private Date deliveredDate;
//    private UserDto user;  //if we want to send use detials
    private List<OrderItemDto> orderItems = new ArrayList<>();
}

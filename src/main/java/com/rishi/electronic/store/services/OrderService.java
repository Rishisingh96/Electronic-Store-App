package com.rishi.electronic.store.services;

import com.rishi.electronic.store.dto.CreateOrderRequest;
import com.rishi.electronic.store.dto.OrderDto;
import com.rishi.electronic.store.dto.PageableResponse;
import com.rishi.electronic.store.exceptions.BadApiRequest;

import java.util.List;

public interface OrderService {

    //create order
    OrderDto createOrder(CreateOrderRequest orderDto) throws BadApiRequest;

    //remove order
    void removeOrder(String orderId);
    //get Orders of user
    List<OrderDto> getOrderOfUser(String userId);

    //get orders
    PageableResponse<OrderDto> getOrders(int pageNumber, int pageSize, String sortBy, String sortDir);
    //order method(logic) related to order
}

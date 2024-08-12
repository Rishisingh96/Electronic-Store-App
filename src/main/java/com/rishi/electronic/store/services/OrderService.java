package com.rishi.electronic.store.services;

import com.rishi.electronic.store.dtos.CreateOrderRequest;
import com.rishi.electronic.store.dtos.OrderDto;
import com.rishi.electronic.store.dtos.PageableResponse;
import com.rishi.electronic.store.exceptions.BadApiRequest;
import org.w3c.dom.stylesheets.LinkStyle;

import java.awt.print.Pageable;
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

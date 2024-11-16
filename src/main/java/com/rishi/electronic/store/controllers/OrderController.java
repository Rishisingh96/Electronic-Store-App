package com.rishi.electronic.store.controllers;

import com.rishi.electronic.store.dtos.ApiResponseMessage;
import com.rishi.electronic.store.dtos.CreateOrderRequest;
import com.rishi.electronic.store.dtos.OrderDto;
import com.rishi.electronic.store.dtos.PageableResponse;
import com.rishi.electronic.store.entites.Order;
import com.rishi.electronic.store.exceptions.BadApiRequest;
import com.rishi.electronic.store.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    //user
    //create
//    @PreAuthorize("hasRole('NORMAL')")
    @PreAuthorize("hasAnyRole('NORMAL','ADMIN')")
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody CreateOrderRequest request) throws BadApiRequest {
        OrderDto order = orderService.createOrder(request);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    //Remove
    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponseMessage> removeOrder(@PathVariable String orderId){
        orderService.removeOrder(orderId);
        ApiResponseMessage responseMessage = ApiResponseMessage.builder()
                .status(HttpStatus.OK)
                .message("order is removed !!")
                .success(true)
                .build();
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    //get orders of the user
    //normal user
    @PreAuthorize("hasAnyRole('NORMAL','ADMIN')")
    @GetMapping("/users/{userId}")
    public  ResponseEntity<List<OrderDto>> getOrderOfUser(@PathVariable String userId ){
        List<OrderDto> ordersOfUser = orderService.getOrderOfUser(userId);
        return new ResponseEntity<>(ordersOfUser, HttpStatus.OK);
    }

    //getOrder all
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<PageableResponse<OrderDto>> getOrders(
    @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
    @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
    @RequestParam(value = "sortBy", defaultValue = "orderedDate", required = false) String sortBy,
    @RequestParam(value = "sortDir", defaultValue = "desc", required = false) String sortDir
    ) {
        PageableResponse<OrderDto> orders = orderService.getOrders(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }
}

package com.rishi.electronic.store.controllers;

import com.rishi.electronic.store.config.AppConstants;
import com.rishi.electronic.store.dtos.AddItemToCartRequest;
import com.rishi.electronic.store.dtos.ApiResponseMessage;
import com.rishi.electronic.store.dtos.CartDto;
import com.rishi.electronic.store.exceptions.BadApiRequest;
import com.rishi.electronic.store.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    //add items to cart
    @PreAuthorize("hasAnyRole('"+AppConstants.ROLE_ADMIN+"','"+ AppConstants.ROLE_NORMAL+"')")
    @PostMapping("/{userId}")
    public ResponseEntity<CartDto> addItemToCar(@PathVariable String userId, @RequestBody AddItemToCartRequest request) throws BadApiRequest {
        CartDto cartDto = cartService.addItemToCart(userId, request);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    //remove items

    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<ApiResponseMessage> removeItemFromCart(@PathVariable String userId, @PathVariable int itemId ){
        cartService.removeItemFromCart(userId,itemId);
        ApiResponseMessage responseMessage = ApiResponseMessage.builder()
                .message("Item is removed !!")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(responseMessage,HttpStatus.OK);
    }

    //create cart clear cart
    @PreAuthorize("hasAnyRole('"+AppConstants.ROLE_ADMIN+"','"+ AppConstants.ROLE_NORMAL+"')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> clearCart(@PathVariable String userId ){
        cartService.clearCart(userId);
        ApiResponseMessage responseMessage = ApiResponseMessage.builder()
                .message("Cart is clear !!")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(responseMessage,HttpStatus.OK);
    }

    //add items to cart
    @PreAuthorize("hasAnyRole('"+AppConstants.ROLE_ADMIN+"','"+ AppConstants.ROLE_NORMAL+"')")
    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable String userId){
        CartDto cartDto = cartService.getCartByUser(userId);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
}

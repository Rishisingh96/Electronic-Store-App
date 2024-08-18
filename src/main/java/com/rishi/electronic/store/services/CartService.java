package com.rishi.electronic.store.services;


import com.rishi.electronic.store.dtos.*;

public interface CartService {
    //add items to cart:
    //case1 : cart for user is not available : we will create the cart and
    //case2 : cart available add the items to cart

    CartDto addItemToCart(String userId, AddItemToCartRequest request);


//    CartDto addItemToCart(String userId);

    //remove item from cart:
    void removeItemFromCart(String userId, int cartItem);
    void clearCart(String userId);

    CartDto getCartByUser(String userId);

}

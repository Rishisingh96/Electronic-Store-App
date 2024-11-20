package com.rishi.electronic.store.services.Impl;
import com.rishi.electronic.store.dto.AddItemToCartRequest;
import com.rishi.electronic.store.dto.CartDto;
import com.rishi.electronic.store.entity.Cart;
import com.rishi.electronic.store.entity.CartItem;
import com.rishi.electronic.store.entity.Product;
import com.rishi.electronic.store.entity.User;
import com.rishi.electronic.store.exceptions.ResourceNotFoundException;
import com.rishi.electronic.store.repositories.CartItemRepository;
import com.rishi.electronic.store.repositories.CartRepository;
import com.rishi.electronic.store.repositories.ProductRepository;
import com.rishi.electronic.store.repositories.UserRepository;
import com.rishi.electronic.store.services.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public CartDto addItemToCart(String userId, AddItemToCartRequest request) {  //AddItemToCartRequest
        int quantity = request.getQuanity();
        String productId = request.getProductId();

//        if(quantity<=0){
//            try {
//                throw new BadApiRequest("Requested quantity is not valid");
//            } catch (BadApiRequest e) {
//                throw new RuntimeException(e);
//            }
//        }
        //fetch the product
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found in database !!"));
        //fetch the user from db
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User id not found !!"));
        Cart cart = null;
        try {
            cart = cartRepository.findByUser(user).get();
        } catch (NoSuchElementException e) {
            cart = new Cart();
            cart.setCartId(UUID.randomUUID().toString());
            cart.setCreatedAt(new Date());
        }
        //perform cart operations
        //if cart items already present : than update
        AtomicReference<Boolean> updated = new AtomicReference<>(false);
        List<CartItem> items = cart.getItems();
        items = items.stream().map(item -> {    //List<CartItem> updatedItems = items.stream().map(item -> {
//        List<CartItem> updatedItems = items.stream().map(item -> {
            if (item.getProduct().getProductId().equals(productId)) {
                //item already present it cart
                item.setQuantity(quantity);
                item.setTotalPrice(quantity * product.getDiscountedPrice());
                updated.set(true);
            }
            return item;
        }).collect(Collectors.toList());
//        cart.setItems(updatedItems);

        //create items
        if (!updated.get()) {
            CartItem cartItem = CartItem.builder()
                    .quantity(quantity)
                    .totalPrice(quantity * product.getDiscountedPrice())
                    .cart(cart)
                    .product(product)
                    .build();
            cart.getItems().add(cartItem);
        }
            cart.setUser(user);
            Cart updatedCart = cartRepository.save(cart);
            return mapper.map(updatedCart, CartDto.class);

    }


    @Override
    public void removeItemFromCart(String userId, int cartItem) {
        //condition

        CartItem cartItem1 = cartItemRepository.findById(cartItem).orElseThrow(() -> new ResourceNotFoundException("Cart Item not found in db"));
        cartItemRepository.delete(cartItem1);

    }

    @Override
    public void clearCart(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User id not found !!"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(()->new ResourceNotFoundException("cart item not found !!"));
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    @Override
    public CartDto getCartByUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User id not found !!"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(()->new ResourceNotFoundException("cart item not found !!"));

        return mapper.map(cart,CartDto.class);
    }
}

package com.rishi.electronic.store.repositories;

import com.rishi.electronic.store.entites.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
}

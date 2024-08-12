package com.rishi.electronic.store.repositories;

import com.rishi.electronic.store.entites.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}

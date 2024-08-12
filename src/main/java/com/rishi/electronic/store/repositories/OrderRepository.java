package com.rishi.electronic.store.repositories;

import com.rishi.electronic.store.entites.Order;
import com.rishi.electronic.store.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String> {

    List<Order> findByUser(User user);
}

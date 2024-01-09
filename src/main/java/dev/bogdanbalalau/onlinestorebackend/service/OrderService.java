package dev.bogdanbalalau.onlinestorebackend.service;

import dev.bogdanbalalau.onlinestorebackend.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll();
    Optional<Order> findById(Integer id);
    Order createOrder(Order order);
}

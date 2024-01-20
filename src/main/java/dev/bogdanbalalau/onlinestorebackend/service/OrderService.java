package dev.bogdanbalalau.onlinestorebackend.service;

import dev.bogdanbalalau.onlinestorebackend.entity.Order;
import dev.bogdanbalalau.onlinestorebackend.entity.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll();
    Optional<Order> findById(Integer id);
    Order createOrder(Order order);
    List<Order> findByUser(User user);
    void deleteAll(List<Order> orders);
}

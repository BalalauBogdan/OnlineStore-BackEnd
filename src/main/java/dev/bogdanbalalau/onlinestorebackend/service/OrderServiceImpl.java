package dev.bogdanbalalau.onlinestorebackend.service;

import dev.bogdanbalalau.onlinestorebackend.entity.Order;
import dev.bogdanbalalau.onlinestorebackend.entity.User;
import dev.bogdanbalalau.onlinestorebackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return this.orderRepository.findById(id);
    }

    @Override
    public Order createOrder(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public List<Order> findByUser(User user) {
        return this.orderRepository.findByUser(user);
    }

    @Override
    public void deleteAll(List<Order> orders) {
        for (Order order: orders) {
            this.orderRepository.delete(order);
        }
    }
}

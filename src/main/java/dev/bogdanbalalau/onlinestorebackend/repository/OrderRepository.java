package dev.bogdanbalalau.onlinestorebackend.repository;

import dev.bogdanbalalau.onlinestorebackend.entity.Order;
import dev.bogdanbalalau.onlinestorebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUser(User user);
}

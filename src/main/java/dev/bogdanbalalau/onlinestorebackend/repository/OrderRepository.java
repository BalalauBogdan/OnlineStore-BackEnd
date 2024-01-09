package dev.bogdanbalalau.onlinestorebackend.repository;

import dev.bogdanbalalau.onlinestorebackend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}

package dev.bogdanbalalau.onlinestorebackend.controller;

import dev.bogdanbalalau.onlinestorebackend.dto.OrderDTO;
import dev.bogdanbalalau.onlinestorebackend.entity.Order;
import dev.bogdanbalalau.onlinestorebackend.service.OrderService;
import dev.bogdanbalalau.onlinestorebackend.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllOrders() {
        List<Order> orderList = this.orderService.findAll();
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("List with all placed orders")
                .data(orderList)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = new Order();
        order.setUser(orderDTO.getUser());
        order.setProducts(orderDTO.getProducts());

        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("Order placed successfully")
                .data(this.orderService.createOrder(order))
                .build();
        return ResponseEntity.ok(response);
    }
}

package com.example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderRepository.getOrderById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @PostMapping
    public String addOrder(@RequestBody List<Product> products) {
        double totalCost = products.stream().mapToDouble(Product::getCost).sum();
        Order newOrder = new Order((long) (orderRepository.getAllOrders().size() + 1),
                LocalDateTime.now(), totalCost, products);
        orderRepository.addOrder(newOrder);
        return "Order added successfully";
    }
}

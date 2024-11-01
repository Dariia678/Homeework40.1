package com.example;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {
    private List<Order> orders = new ArrayList<>();

    public Optional<Order> getOrderById(Long id) {
        return orders.stream().filter(order -> order.getId().equals(id)).findFirst();
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}

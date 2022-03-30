package de.kohlerjens.springboot.jpa.service;

import de.kohlerjens.springboot.jpa.model.Order;
import de.kohlerjens.springboot.jpa.repository.OrderRepository;
import de.kohlerjens.springboot.jpa.tools.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public Order saveOrder(Order order) {
        return repository.save(order);
    }

    public List<Order> saveOrders(List<Order> orders) {
        return repository.saveAll(orders);
    }

    public List<Order> getOrders() {
        return repository.findAll();
    }

    public Order getOrderById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Order> getOrderByDate(Date date) {
        return repository.findByDate(date);
    }

    public String deleteOrder(String id) {
        repository.deleteById(id);
        return "Order removed !! " + id;
    }

    public Order updateOrder(Order order) {
        Order existingOrder = repository.findById(order.getId()).orElse(null);
        if (existingOrder == null) {
            return null;
        }
        if (Utils.checkNullString(order.getName())) {
            existingOrder.setName(order.getName());
        }
        if (Utils.checkNullString(order.getDate())) {
            existingOrder.setDate(order.getDate());
        }
        return repository.save(existingOrder);
    }


}

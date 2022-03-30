package de.kohlerjens.springboot.jpa.repository;

import de.kohlerjens.springboot.jpa.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByDate(Date orderDate);
}
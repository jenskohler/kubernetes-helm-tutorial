package de.kohlerjens.springboot.jpa.repository;

import de.kohlerjens.springboot.jpa.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, String> {
    List<Customer> findByLastName(String lastName);
}
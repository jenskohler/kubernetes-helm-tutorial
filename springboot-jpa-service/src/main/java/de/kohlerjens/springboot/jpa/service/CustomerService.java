package de.kohlerjens.springboot.jpa.service;

import de.kohlerjens.springboot.jpa.model.Customer;
import de.kohlerjens.springboot.jpa.repository.CustomerRepository;
import de.kohlerjens.springboot.jpa.tools.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    public Customer saveCustomer(Customer Customer) {
        return repository.save(Customer);
    }

    public List<Customer> saveCustomers(List<Customer> Customers) {
        return repository.saveAll(Customers);
    }

    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    public Customer getCustomerById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Customer> getCustomerByLastName(String name) {
        return repository.findByLastName(name);
    }

    public String deleteCustomer(String id) {
        repository.deleteById(id);
        return "Customer removed !! " + id;
    }

    public Customer updateCustomer(Customer customer) {
        Customer existingCustomer = repository.findById(customer.getId()).orElse(null);
        if (existingCustomer == null) {
            return null;
        }
        if (Utils.checkNullString(customer.getFirstName())) {
            existingCustomer.setFirstName(customer.getFirstName());
        }
        if (Utils.checkNullString(customer.getLastName())) {
            existingCustomer.setLastName(customer.getLastName());
        }
        if (Utils.checkNullString(customer.getDateOfBirth())) {
            existingCustomer.setDateOfBirth(customer.getDateOfBirth());
        }
        return repository.save(existingCustomer);
    }


}

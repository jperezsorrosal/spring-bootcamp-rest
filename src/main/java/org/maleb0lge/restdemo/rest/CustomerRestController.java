package org.maleb0lge.restdemo.rest;

import org.maleb0lge.restdemo.entity.Customer;
import org.maleb0lge.restdemo.error.CustomerNotFoundException;
import org.maleb0lge.restdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {

        Customer customer = customerService.getCustomer(customerId);

        if (customer == null)
            throw new CustomerNotFoundException(
                    String.format("Customer with id {%s} not found", customerId));

        return customer;
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        // our implementation with hiberrnater uses
        // org.hibernate.Session public abstract void saveOrUpdate(Object object)
        // setting id to non existent or null will insert as it cannot resolve
        // for an existing customer
        customer.setId(0);
        customerService.saveCustomer(customer);

        return customer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer) {

        customerService.saveCustomer(customer);

        return customer;
    }

    @DeleteMapping("/customers/{customerId}")
    public Customer deleteCustomer(@PathVariable int customerId) {
        Customer customer = customerService.getCustomer(customerId);

        if (customer == null)
            throw new CustomerNotFoundException(
                    String.format("Customer with id {%s} doesn't exist", customerId)
            );

        customerService.deleteCustomer(customerId);

        return customer;
    }
}

package com.examly.springapp.service;

import com.examly.springapp.model.Customer;

import java.util.*;

public interface CustomerService {
    Customer registerCustomer(Customer customer);
    Customer getCustomerById(Long customerId);
    Customer getCustomerByUserId(Long userid);
    List<Customer> getAllCustomers();
}
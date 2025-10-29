package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.model.Customer;
import com.examly.springapp.model.User;


public interface CustomerRepo extends JpaRepository<Customer,Long>{
    public Customer findByUser(User user);
}

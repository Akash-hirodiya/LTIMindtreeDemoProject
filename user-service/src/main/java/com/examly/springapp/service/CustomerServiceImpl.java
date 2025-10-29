package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Customer;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.CustomerRepo;
import com.examly.springapp.repository.UserRepo;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public Customer getCustomerByUserId(Long userId) {
        User u=userRepo.findById(userId).get();
        Customer c=customerRepo.findByUser(u);
        return c;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }


    @Override
    public Customer getCustomerById(Long customerId) {
        if(customerRepo.existsById(customerId)){
            return customerRepo.findById(customerId).get();
        }
        return null;
    }

    @Override
    public Customer registerCustomer(Customer customer) {
        User user=userRepo.findById(customer.getUser().getUserId()).get();
        customer.setUser(user);
        return customerRepo.save(customer);
        
    }


}

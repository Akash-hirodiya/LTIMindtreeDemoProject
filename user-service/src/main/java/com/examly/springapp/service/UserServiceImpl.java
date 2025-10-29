package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springapp.exceptions.EmailAlreadyExistException;
import com.examly.springapp.exceptions.UserAlreadyExistException;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;
@Service
public class UserServiceImpl implements UserService {
    @Autowired 
    private UserRepo uRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User register(User user) {
        if(uRepo.findByUsername(user.getUsername())!=null){
            throw new UserAlreadyExistException("User with Username "+user.getUsername()+" already exist.");
        }
        if(uRepo.findByEmail(user.getEmail())!=null){
            throw new EmailAlreadyExistException("User with Email "+user.getEmail()+" already exist.");
        }
        User u=new User();
        u.setUsername(user.getUsername());
        u.setPassword(passwordEncoder.encode(user.getPassword()));
        u.setRole(user.getRole());
        u.setEmail(user.getEmail());
        u.setMobileNumber(user.getMobileNumber());
        return uRepo.save(u);
    } 
    @Override
    public User findByUsername(String username){
        User u=uRepo.findByUsername(username);
        return u;
    }
    // @Override
    // public User login(User user) {
    //     User u=uRepo.findByUsername(user.getUsername());
    //     if(u!=null && u.getPassword().equals(user.getPassword()))
    //         return u;
    //     return null;
    // }  
}

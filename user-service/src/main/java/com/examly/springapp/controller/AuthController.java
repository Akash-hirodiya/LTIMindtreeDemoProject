package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 
import com.examly.springapp.config.JwtUtils;
import com.examly.springapp.config.MyUserDetailsService;
import com.examly.springapp.model.User;
import com.examly.springapp.service.JwtResponse;
import com.examly.springapp.service.UserService;
import com.examly.springapp.service.UserServiceImpl;

@RestController
public class AuthController {
    @Autowired
    private UserServiceImpl service;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
 
     @Autowired
    private JwtUtils jwtUtils;
 
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/api/register")
    public ResponseEntity<String>register(@RequestBody User user){
        User u=service.register(user);
        if(u!=null)
            return new ResponseEntity<>("User Registered Successfully.",HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/api/login")
    public ResponseEntity<?> generateToken(@RequestBody User request) throws Exception{
 
        this.doAuthenticate(request.getUsername(), request.getPassword());
    
    
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(request.getUsername());
            User user = service.findByUsername(request.getUsername());
            String token = this.jwtUtils.generateToken(userDetails,user.getRole(),user.getUserId());
            JwtResponse rs = new JwtResponse();
            rs.setToken(token);
            rs.setUsername(request.getUsername());
            rs.setUserId(user.getUserId());
            rs.setRole(user.getRole());
 
        
            return new ResponseEntity<>(rs, HttpStatus.OK);
    }
 
    private void doAuthenticate(String email, String password) {
    
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(authentication);
    
    
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    
    }
    // @PostMapping("/api/login")
    // public ResponseEntity<User>login(@RequestBody User user){
    //     User u=service.login(user);
    //     if(u!=null)
    //         return new ResponseEntity<>(u,HttpStatus.OK);
    //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
}

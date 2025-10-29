package com.examly.springapp.service;

public class JwtResponse {
    private String token;
    private String username;
    private String role;
    private long userId;
    public String getRole() {
        return role;
    }
 
    public void setRole(String role) {
        this.role = role;
    }
 
    public String getToken() {
        return token;
    }
 
    public void setToken(String token) {
        this.token = token;
    }
 
    public JwtResponse() {
    }
 
    public JwtResponse(String token) {
        this.token = token;
    }
 
    public long getUserId() {
        return userId;
    }
 
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
 
}

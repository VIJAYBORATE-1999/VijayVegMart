package com.vijay.service;

import com.vijay.model.Users;

public interface UsersService {
	
    void registerUser(Users user) throws Exception;
    Users loginUser(String username, String password) throws Exception;

}

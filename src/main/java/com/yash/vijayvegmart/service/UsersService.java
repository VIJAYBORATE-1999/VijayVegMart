package com.yash.vijayvegmart.service;

import com.yash.vijayvegmart.model.Users;

public interface UsersService {
	
    void registerUser(Users user) throws Exception;
    Users loginUser(String username, String password) throws Exception;

}

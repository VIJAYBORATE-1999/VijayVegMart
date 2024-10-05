package com.yash.vijayvegmart.service;

import com.yash.vijayvegmart.model.Users;

public interface UsersService {
	
	
    void registerUser(Users user) throws Exception;
    public boolean getUserByUserName(String user_name) throws Exception;
    public boolean getUserByEmail(String user_email)throws Exception;
    public boolean doesAdminUserExist();
    
    Users loginUser(String username, String password) throws Exception;
    public String decrypt(String s) throws Exception;

}

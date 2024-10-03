package com.yash.vijayvegmart.dao;

import java.util.Optional;

import com.yash.vijayvegmart.model.Users;

public interface UsersDao {
	void saveUser(Users user);
    Optional<Users> getUserByUsername(String username);
    public Optional<Users> getUserByEmail(String email);

}

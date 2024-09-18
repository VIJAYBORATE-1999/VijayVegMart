package com.vijay.dao;

import java.util.Optional;

import com.vijay.model.Users;

public interface UsersDao {
	void saveUser(Users user);
    Optional<Users> getUserByUsername(String username);

}

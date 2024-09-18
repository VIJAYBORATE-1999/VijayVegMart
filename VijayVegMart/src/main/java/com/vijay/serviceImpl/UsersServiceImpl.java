package com.vijay.serviceImpl;

import java.util.Optional;

import com.vijay.dao.UsersDao;
import com.vijay.daoImpl.UsersDAOImpl;
import com.vijay.exception.UsersException;
import com.vijay.model.Users;
import com.vijay.service.UsersService;

public class UsersServiceImpl implements UsersService {

    private UsersDao userDao;

    public UsersServiceImpl() {
        this.userDao = new UsersDAOImpl();
        
       System.out.println("borate555555");
        
    }

    @Override
    public void registerUser(Users user) throws Exception {
    	
    	//System.out.println("Vijayyyyy22222");
    	
        Optional<Users> existingUser = userDao.getUserByUsername(user.getUsername());
        System.out.println("Vijayyyyy22222");
        if (existingUser.isPresent()) {
            throw new UsersException("Username already exists.");
        }
        userDao.saveUser(user);
    }

    @Override
    public Users loginUser(String username, String password) throws Exception {
        Optional<Users> user = userDao.getUserByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        } else {
            throw new UsersException("Invalid username or password.");
        }
    }	
}

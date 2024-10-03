package com.yash.vijayvegmart.serviceImpl;

import java.util.Optional;

import com.yash.vijayvegmart.dao.UsersDao;
import com.yash.vijayvegmart.daoImpl.UsersDAOImpl;
import com.yash.vijayvegmart.exception.UsersException;
import com.yash.vijayvegmart.model.Users;
import com.yash.vijayvegmart.service.UsersService;

public class UsersServiceImpl implements UsersService {

    private UsersDao userDao;

    public UsersServiceImpl() {
        this.userDao = new UsersDAOImpl();
        
        
    }

    @Override
    public void registerUser(Users user) throws Exception {
    	
    	
    	
        Optional<Users> existingUser = userDao.getUserByUsername(user.getUsername());
        Optional<Users> existingUser2 = userDao.getUserByEmail(user.getEmail());
        
        System.out.println("Vijayyyyy22222");
        if (existingUser.isPresent()) {
            throw new UsersException("Username already exists.");
        }
        else if(existingUser2.isPresent()) {
        	 throw new UsersException("Email Id already exists.");
        }
        userDao.saveUser(user);
    }

    @Override
    public Users loginUser(String username, String password) throws Exception {
    	
    	
        Optional<Users> user = userDao.getUserByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
        	
        	
            return user.get();
        } 
        else if(user.isPresent()) {
        	throw new UsersException("Invalid Password.");
        }
        else 
        {
            throw new UsersException("Invalid username or password.");
        }
    }	
}

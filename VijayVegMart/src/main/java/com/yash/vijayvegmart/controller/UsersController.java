package com.yash.vijayvegmart.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;

import com.yash.vijayvegmart.model.Users;
import com.yash.vijayvegmart.service.UsersService;
import com.yash.vijayvegmart.serviceImpl.UsersServiceImpl;

@WebServlet("/users")
public class UsersController extends HttpServlet {
	private UsersService userService;

    @Override
    public void init() {
        userService = new UsersServiceImpl();
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
       PrintWriter out = response.getWriter();
       out.println(action);
        if ("register".equals(action)) {
            registerUser(request, response);
        } else if ("login".equals(action)) {
            loginUser(request, response);
          
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String usertype = request.getParameter("usertype");

        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setUsertype(usertype);
      
        HttpSession session = request.getSession(); 

        try {
        	
       
            userService.registerUser(user);     
            
            /*USING HTTP SESSION */
            
            session.setAttribute("sucessmessage","Registration successful!");
        	  response.sendRedirect("login.jsp"); 
        	  
        	  /*USING HTTP SESSION */
        

        	  
        	
        	   
        } catch (Exception e) {

        	/*USING HTTP SESSION */
        	
        session.setAttribute("error", e.getMessage());
    	session.setAttribute("username", username);
     	  response.sendRedirect("register.jsp");
        
      	/*USING HTTP SESSION */
      	  
      	  
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Users user = userService.loginUser(username, password);

 
            System.out.print("VVVVVVVVVVVV"+user.getUsertype());
           
            if(user.getUsertype().equals("admin"))
            {
            	/*ADMIN USER */
                request.getSession().setAttribute("user", user);
                response.sendRedirect("admin/home.jsp");
            }
            else if(user.getUsertype().equals("vendor"))
            {
            	/*VENDOR USER */
                request.getSession().setAttribute("user", user);
                response.sendRedirect("vendor/home.jsp");           	
            }
            else {
            	
            	/*Normal User = Customer */
                request.getSession().setAttribute("user", user);
                response.sendRedirect("home.jsp");
            	
            }
            
            
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            System.out.print(e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }

}
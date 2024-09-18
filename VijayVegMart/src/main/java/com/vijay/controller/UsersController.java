package com.vijay.controller;

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

import com.vijay.model.Users;
import com.vijay.service.UsersService;
import com.vijay.serviceImpl.UsersServiceImpl;

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
            //
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String check = request.getParameter("check");
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
      
        HttpSession session = request.getSession(); 

        try {
        	
        	if(check!=null) {
            userService.registerUser(user);     
            
            /*USING HTTP SESSION */
            
            session.setAttribute("sucessmessage","Registration successful!");
        	  response.sendRedirect("login.jsp"); 
        	  
        	  /*USING HTTP SESSION */
        	}
        	else {
//        		session.setAttribute("checkmessage","Please Agree Terms and Conditions");
//          	  response.sendRedirect("register.jsp");  
        	       request.setAttribute("checkmessage", "Please Agree Terms and Conditions");
        	       RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
                  dispatcher.forward(request, response);
        	}
        	  
        	
        	  
  /* ------------------------------------------------------------------------ */           
//            request.setAttribute("message", "Registration successful!");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
//            dispatcher.forward(request, response);
 /* ------------------------------------------------------------------------ */       	  
            
        } catch (Exception e) {

        	/*USING HTTP SESSION */
        	
          session.setAttribute("error", e.getMessage());
      	  response.sendRedirect("register.jsp");
      	  
      	/*USING HTTP SESSION */
      	  
      	  
  /* ------------------------------------------------------------------------ */
         //   request.setAttribute("error", e.getMessage());
           // RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
          //  dispatcher.forward(request, response);
      	  
/* --------------------------------------------------------------------- */
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Users user = userService.loginUser(username, password);

 /* --------------------------------------------------------------------- */            
            request.getSession().setAttribute("user", user);
            response.sendRedirect("home.jsp");
  /* --------------------------------------------------------------------- */            
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }

}
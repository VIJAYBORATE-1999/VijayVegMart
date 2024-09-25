package com.yash.vijayvegmart.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String usertype = request.getParameter("usertype");
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            // Registration logic
            Users user = new Users();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setUsertype(usertype);

            HttpSession session = request.getSession();

            try {
                userService.registerUser(user);
                session.setAttribute("sucessmessage", "Registration successful!");
                response.sendRedirect("login.jsp");
            } catch (Exception e) {
                session.setAttribute("error", e.getMessage());
                session.setAttribute("username", username);
                response.sendRedirect("register.jsp");
            }
        } else if ("login".equals(action)) {
            // Login logic

            try {
                Users user = userService.loginUser(username, password);

                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                if (user.getUsertype().equals("admin")) {
                    // Admin user
                    response.sendRedirect("admin/home.jsp");
                } else if (user.getUsertype().equals("vendor")) {
                    // Vendor user
                    response.sendRedirect("vendor/home.jsp");
                } else {
                    // Normal user (Customer)
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
}

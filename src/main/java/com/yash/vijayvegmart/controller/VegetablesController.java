package com.yash.vijayvegmart.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.yash.vijayvegmart.model.Users;
import com.yash.vijayvegmart.model.VegetablesDetails;
import com.yash.vijayvegmart.service.VegetablesService;
import com.yash.vijayvegmart.serviceImpl.UsersServiceImpl;
import com.yash.vijayvegmart.serviceImpl.VegetablesServiceImpl;

@WebServlet("/Vegetable")
@MultipartConfig
public class VegetablesController extends HttpServlet {
    private VegetablesService veg_service;

    public void init() {
        veg_service = new VegetablesServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String veg_name = request.getParameter("veg_name");
            String description = request.getParameter("description");
            String veg_category = request.getParameter("veg_category");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price_per_piece = Double.parseDouble(request.getParameter("price_per_piece"));

            Part part = request.getPart("veg_pic_name");
            String veg_pic_name = part.getSubmittedFileName();
            String action_type = request.getParameter("action_type");
            HttpSession session = request.getSession(false);
            Users user = (Users) session.getAttribute("user");
            int vendorId = user.getId();

            VegetablesDetails veg_details = new VegetablesDetails(vendorId, veg_name, quantity, description, price_per_piece, veg_pic_name, veg_category);

            // Ensure the img directory exists
            String path = getServletContext().getRealPath("") + "img";
            File imgDir = new File(path);
            if (!imgDir.exists()) {
                boolean created = imgDir.mkdirs();
                if (created) {
                    System.out.println("Directory created: " + imgDir.getAbsolutePath());
                } else {
                    System.out.println("Failed to create directory.");
                }
            }


            action_type = "add";
     if ("add".equals(action_type)) {
                veg_service.addVegetbale(veg_details);
                
                // Check for file upload
                if (part != null && part.getSize() > 0) {
                    System.out.println("Image Path: " + path);
                    System.out.println("Image Name: " + veg_pic_name);
                    part.write(path + File.separator + veg_pic_name); // Save image in path
                    session.setAttribute("sucessmessage", "Vegetable Added Successfully");
                    response.sendRedirect(request.getContextPath() + "/vendor/home.jsp"); // Redirect to success page
                } else {
                    System.out.println("No file uploaded.");
                    session.setAttribute("error", "No file uploaded.");
                    response.sendRedirect(request.getContextPath() + "/vendor/home.jsp");
                }
                
                // Redirect to success page
            } 
     else if ("update".equals(action_type)) {
                // Update logic here
            }
     else if ("delete".equals(action_type)) {
                // Delete logic here
            }

        } catch (Exception e) {
            e.printStackTrace();
            HttpSession session = request.getSession(false);
            session.setAttribute("error", e.getMessage());
            response.sendRedirect(request.getContextPath() + "/error.jsp"); // Redirect to error page
        }
    }
}

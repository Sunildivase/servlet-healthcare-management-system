package com.healthcareApp.controller;

import com.healthcareApp.service.DepartmentService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DepartmentController extends HttpServlet {

    DepartmentService departmentService = new DepartmentService();

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        System.out.println("========Inside the doGet() method=============");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        System.out.println("========Inside the doPost() method=============");

    }

    public void service(HttpServletRequest request, HttpServletResponse response){
        System.out.println("========Inside the service() method=============");

    }
    public void destroy(HttpServletRequest request, HttpServletResponse response){
        System.out.println("========Inside the destroy() method=============");

    }

}

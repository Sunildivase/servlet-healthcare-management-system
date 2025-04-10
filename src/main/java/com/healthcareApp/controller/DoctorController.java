package com.healthcareApp.controller;

import com.healthcareApp.service.DoctorService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoctorController extends HttpServlet {

     DoctorService doctorService = new DoctorService();

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        System.out.println("========inside the doGet()===========");
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response){
        System.out.println("========inside the doPost()===========");


    }

    public void service(HttpServletRequest request,HttpServletResponse response){
        System.out.println("========inside the service()===========");

    }

    public void destroy(HttpServletRequest request,HttpServletResponse response){
        System.out.println("========inside the destroy()===========");

    }
}

package com.healthcareApp.controller;

import com.healthcareApp.model.Doctor;
import com.healthcareApp.service.DoctorService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorController extends HttpServlet {

     DoctorService doctorService = new DoctorService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("========inside the doGet()===========");

        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        writer.println("<html><body><h1>Doctor List </h1><table border='1'>");
        writer.println("<tr>" +
                "<th>doctorId</th>"+
                "<th>firstName</th>"+
                "<th>lastName</th>"+
                "<th>age</th>"+
                "<th>gender</th>"+
                "<th>contactNo</th>"+
                "<th>speciality</th>"+
                "<th>experience</th>"+
                "</tr>"
        );

        try{
            List<Doctor> doctorList = doctorService.displayDoctor();
            for(Doctor doctor : doctorList){
                writer.println(
                        "<tr>"+
                                "<td>" + doctor.getDoctorId()+"</td>" +
                                "<td>" + doctor.getFirstName() +"</td>" +
                                "<td>" + doctor.getLastName() + "</td>" +
                                "<td>" + doctor.getAge() + "</td>" +
                                "<td>" + doctor.getGender() + "</td>" +
                                "<td>" + doctor.getContactNo() +"</td>" +
                                "<td>" + doctor.getSpeciality() + "</td>"+
                                "<td>" + doctor.getExperience() + "</td>"
                );
            }
        } catch (SQLException e) {
            System.out.println("<p> Error fetching doctors </p>");;
        }

        writer.println("</body></html>");

    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("========inside the doPost()===========");

        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String contactNo = request.getParameter("contactNo");
        String speciality = request.getParameter("speciality");
        int experience = Integer.parseInt(request.getParameter("experience"));

        Doctor doctor = new Doctor(doctorId,firstName,lastName,age,gender,contactNo,speciality,experience);

        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        try {
            if (doctorService.insertDoctor(doctor)) {
                writer.println("<h2>doctor inserted successfully!</h2>");
            } else {
                writer.println("<h2>Failed to insert doctor.</h2>");
            }
        } catch (SQLException e) {
            writer.println("<p>Error: " + e.getMessage() + "</p>");
        }


    }

    public void service(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("========inside the service()===========");

        if (request.getMethod().equals("POST")) {
            this.doPost(request, response);
        } else {
            this.doGet(request, response);
        }
    }

    public void destroy(HttpServletRequest request,HttpServletResponse response){
        System.out.println("========inside the destroy()===========");

    }
}

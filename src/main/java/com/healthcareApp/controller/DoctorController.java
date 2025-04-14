package com.healthcareApp.controller;

import com.healthcareApp.model.Doctor;
import com.healthcareApp.model.Person;
import com.healthcareApp.service.DoctorService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class DoctorController extends HttpServlet {

    DoctorService doctorService = new DoctorService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("=======inside the doGet() method");


//        try {
//            List<Doctor> doctorList = doctorService.displayDoctor();
//
//            System.out.println("---------set the attribute-------");
//            System.out.println("--------redirecting servlet request to dispatcher-----");
//            request.setAttribute("doctorList",doctorList);
//
//            request.getRequestDispatcher("DisplayDoctor.jsp").forward(request,response);
//
//        } catch (SQLException  | ServletException e) {
//
//            throw new RuntimeException(e);
//        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body><h1>Doctor List</h1><table border='1'>");
        out.println("<tr>" +
                "<th>DoctorId</th>" +
                "<th>FirstName</th>" +
                "<th>LastName</th>" +
                "<th>Age</th>" +
                "<th>Gender</th>" +
                "<th>ContactNo</th>" +
                "<th>Speciality</th>" +
                "<th>Experience</th>" +
                "</tr>");

        try {
            List<Doctor> doctorList = doctorService.displayDoctor();
            for (Doctor doctor : doctorList) {
                out.println("<tr>" +
                        "<td>" + doctor.getDoctorId() + "</td>" +
                        "<td>" + doctor.getFirstName() + "</td>" +
                        "<td>" + doctor.getLastName() + "</td>" +
                        "<td>" + doctor.getAge() + "</td>" +
                        "<td>" + doctor.getGender() + "</td>" +
                        "<td>" + doctor.getContactNo() + "</td>" +
                        "<td>" + doctor.getSpeciality() + "</td>" +
                        "<td>" + doctor.getExperience() + "</td>" +
                        "</tr>");
            }
        } catch (SQLException e) {
            out.println("<p>Error fetching doctors.</p>");
        }

        out.println("</table></body></html>");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int  doctorId = Integer.parseInt(request.getParameter("doctorId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int  age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String  contactNo = request.getParameter("contactNo");
        String  speciality = request.getParameter("Speciality");
        int experience = Integer.parseInt(request.getParameter("experience"));

        Doctor doctor = new Doctor(doctorId,firstName,lastName,age,gender,contactNo,speciality,experience);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            if (doctorService.insertDoctor(doctor)) {
                out.println("<h2>doctor inserted successfully!</h2>");
            } else {
                out.println("<h2>Failed to insert doctor.</h2>");
            }
        } catch (SQLException e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("========inside the service() method=====");

        if (request.getMethod().equals("POST")) {
            this.doPost(request, response);
        } else if (request.getMethod().equals("DELETE")) {
            this.destroy(request,response);
        } else {
            this.doGet(request, response);
        }
    }
    public void destroy(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("========inside the destroy() method=====");

        String idStr = request.getParameter("personId");  // This must not be null

        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "personId is required.");
            return;
        }

        int doctorId = Integer.parseInt(idStr);  // This line throws error if idStr is null

        boolean deleted = false; // Assuming service accepts id
        try {
            deleted = doctorService.deleteDoctor(doctorId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if (deleted) {
            out.println("<h1>Doctor deleted successfully</h1>");
        } else {
            out.println("<h1>Doctor not found</h1>");
        }
        out.println("</body></html>");
    }

}

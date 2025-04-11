package com.healthcareApp.controller;

import com.healthcareApp.model.Hospital;
import com.healthcareApp.service.HospitalService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HospitalController extends HttpServlet {

    private static final HospitalService hospitalService = new HospitalService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("========inside the doGet()=========");

        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        writer.println("<html><body>");
        writer.println("<h1>Hospital List</h1><table border='1'>");
        writer.println("<tr>"+
                 "<th>hospitalId</th>"+
                 "<th>hospitalName</th>"+
                 "<th>address</th>"+
                 "<th>emailId</th>"+
                 "<th>contactNo</th>"+
                "</tr>"
        );

        try{
            List<Hospital> hospitalList = hospitalService.displayHospital();
            for(Hospital hospital : hospitalList){
                writer.println(
                        "<tr>" +
                                "<td>"+ hospital.getHospitalId()+"</td>"+
                                "<td>"+hospital.getHospitalName()+"</td>"+
                                "<td>"+hospital.getAddress()+"</td>"+
                                "<td>"+hospital.getEmailId()+"</td>"+
                                "<td>"+hospital.getContactNo()+"</td>"+
                         "</tr>"

                        );
            }
        }catch (SQLException e){
            System.err.println("<p> Error fetching hospital </p>");
        }

        writer.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("========inside the doPost()=========");

        int hospitalId = Integer.parseInt(request.getParameter("hospitalId"));
        String hospitalName = request.getParameter("hospitalName");
        String address = request.getParameter("address");
        String emailId = request.getParameter("emailId");
        String contactNo = request.getParameter("contactNo");

        Hospital hospital = new Hospital(hospitalId,hospitalName,address,emailId,contactNo);

        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        try{
            if(hospitalService.insertHospital(hospital)){
                writer.println("<h2> hospital Inserted Successfully !!");
            }else{
                writer.println("<h2> Failed to Insert Hospital");
            }
        } catch (SQLException e) {
            System.err.println("<p> Error: "+e.getMessage()+"</P>");
        }

    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("========inside the service()=========");

        if(request.getMethod().equals("POST")){
            this.doPost(request,response);
        }else{
            this.doGet(request,response);
        }

    }

    public void destroy(HttpServletRequest request, HttpServletResponse response){
        System.out.println("========inside the destroy()=========");

    }
}

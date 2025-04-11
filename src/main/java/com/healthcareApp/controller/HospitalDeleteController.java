package com.healthcareApp.controller;

import com.healthcareApp.service.DoctorService;
import com.healthcareApp.service.HospitalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class HospitalDeleteController extends HttpServlet {

    private static final HospitalService hospitalService = new HospitalService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("hospitalId");  // This must not be null

        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "hospitalId is required.");
            return;
        }

        int hospitalId = Integer.parseInt(idStr);  // This line throws error if idStr is null

        boolean deleted = false; // Assuming service accepts id
        try {
            deleted = hospitalService.deleteHospital(hospitalId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if (deleted) {
            out.println("<h1>Hospital deleted successfully</h1>");
        } else {
            out.println("<h1>Hospital not found</h1>");
        }
        out.println("</body></html>");
    }
}

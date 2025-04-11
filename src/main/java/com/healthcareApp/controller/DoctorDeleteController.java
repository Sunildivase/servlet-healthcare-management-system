package com.healthcareApp.controller;

import com.healthcareApp.service.DoctorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class DoctorDeleteController extends HttpServlet {

    private static final DoctorService doctorService = new DoctorService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("doctorId");  // This must not be null

        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "doctorId is required.");
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
            out.println("<h1>doctor deleted successfully</h1>");
        } else {
            out.println("<h1>doctor not found</h1>");
        }
        out.println("</body></html>");
    }
}

package com.healthcareApp.controller;

import com.healthcareApp.service.PersonService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class PersonDeleteController {

    private final PersonService personService = new PersonService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("personId");  // This must not be null

        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "personId is required.");
            return;
        }

        int personId = Integer.parseInt(idStr);  // This line throws error if idStr is null

        boolean deleted = false; // Assuming service accepts id
        try {
            deleted = personService.deletePerson(personId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if (deleted) {
            out.println("<h1>person deleted successfully</h1>");
        } else {
            out.println("<h1>person not found</h1>");
        }
        out.println("</body></html>");
    }
}

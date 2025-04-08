package com.healthcareApp.controller;

import com.healthcareApp.model.Person;
import com.healthcareApp.service.PersonService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class PersonController extends HttpServlet {
    PersonService personService = new PersonService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("=======inside the doGet() method");


//        try {
//            List<Person> personList = personService.displayPerson();
//
//            System.out.println("---------set the attribute-------");
//            System.out.println("--------redirecting servlet request to dispatcher-----");
//            request.setAttribute("personList",personList);
//
//            request.getRequestDispatcher("/DisplayPerson.jsp").forward(request,response);
//
//        } catch (SQLException | ServletException e) {
//
//            throw new RuntimeException(e);
//        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body><h1>Person List</h1><table border='1'>");
        out.println("<tr>" +
                "<th>PersonId</th>" +
                "<th>Type</th>" +
                "<th>FirstName</th>" +
                "<th>LastName</th>" +
                "<th>Age</th>" +
                "<th>Gender</th>" +
                "<th>ContactNo</th>" +
                "<th>AlternateMobile</th>" +
                "<th>Address</th>" +
                "</tr>");

        try {
            List<Person> personList = personService.displayPerson();
            for (Person person : personList) {
                out.println("<tr>" +
                        "<td>" + person.getPersonId() + "</td>" +
                        "<td>" + person.getType() + "</td>" +
                        "<td>" + person.getFirstName() + "</td>" +
                        "<td>" + person.getLastName() + "</td>" +
                        "<td>" + person.getAge() + "</td>" +
                        "<td>" + person.getGender() + "</td>" +
                        "<td>" + person.getContactNo() + "</td>" +
                        "<td>" + person.getAlternateMobile() + "</td>" +
                        "<td>" + person.getAddress() + "</td>" +
                        "</tr>");
            }
        } catch (SQLException e) {
            out.println("<p>Error fetching persons.</p>");
        }

        out.println("</table></body></html>");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String  personId = request.getParameter("personId");
        String type = request.getParameter("type");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String  age = request.getParameter("age");
        String gender = request.getParameter("gender");
        String  contactNo = request.getParameter("contactNo");
        String  alternateMobile = request.getParameter("alternateMobile");
        String address = request.getParameter("address");

        Person person = new Person(personId,type,firstName,lastName,age,gender,contactNo,alternateMobile,address);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            if (personService.insertPerson(person)) {
                out.println("<h2>person inserted successfully!</h2>");
            } else {
                out.println("<h2>Failed to insert person.</h2>");
            }
        } catch (SQLException e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }

    public void service(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("========inside the service() method=====");

        if (request.getMethod().equals("POST")) {
            this.doPost(request, response);
        } else {
            this.doGet(request, response);
        }
    }
    public void destroy(HttpServletRequest request,HttpServletResponse response){
        System.out.println("========inside the destroy() method=====");

    }
}

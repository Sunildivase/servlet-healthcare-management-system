<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="style.css">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Department Form</title>
</head>
<link rel="stylesheet" href="style.css">
<body>
<div class="form-container">
    <h1>Department Information Form</h1>

    <form action="/servlet-healthcare-management-system/department-controller" method="POST">

        <label for="deptId">DeptId:</label>
        <input type="number" id="deptId" name="deptId" placeholder="deptId" required><br>

        <label for="deptName">DeptName:</label>
        <input type="text" id="deptName" name="deptName" placeholder="deptName" required><br>

        <label for="doctorId">DoctorId:</label>
        <input type="number" id="doctorId" name="doctorId" placeholder="doctorId" required> <br>

        <label for="hospitalId">HospitalId:</label>
        <input type="number" id="hospitalId" name="hospitalId" placeholder="hospitalId" required> <br>

        <button type="submit">Submit</button>
        <br>
        <button type="reset">Reset</button>
    </form>
</div>
</body>

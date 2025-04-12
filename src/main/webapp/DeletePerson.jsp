<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="style.css">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete Person</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="form-container">
    <h1>Delete Person</h1>
    <form action="/servlet-healthcare-management-system/DeletePerson.jsp" method="DELETE">
        <div class="form-group">
            <label for="personId">PersonId:</label>
            <input type="number" id="personId" name="personId" placeholder="personId" required>
        </div>

        <button type="submit">Submit</button>
        <br>
        <button type="reset">Reset</button>

    </form>
</div>
</body>
</html>
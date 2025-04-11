package com.healthcareApp.repository;

import com.healthcareApp.model.Doctor;
import com.healthcareApp.service.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DoctorRepository {

    public boolean createDoctor(Doctor doctor) throws SQLException {

        Connection connection = new ConnectionService().getConnection(); // GET connection here

        String query = "insert INTO doctor(doctorId,firstName,lastName,age,gender,contactNo,speciality,experience) " +
                "values (?,?,?,?,?,?,?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,doctor.getDoctorId());
            preparedStatement.setString(2,doctor.getFirstName());
            preparedStatement.setString(3, doctor.getLastName());
            preparedStatement.setInt(4,doctor.getAge());
            preparedStatement.setString(5,doctor.getGender());
            preparedStatement.setString(6,doctor.getContactNo());
            preparedStatement.setString(7,doctor.getSpeciality());
            preparedStatement.setInt(8,doctor.getExperience());

            int rowInserted = preparedStatement.executeUpdate(query);

            return rowInserted > 0;
        }

    }

    public List<Doctor> displayDoctor(){
        return displayDoctor();
    }

    public boolean deleteDoctor(int doctorId){
        return false;
    }

    public boolean updateDoctor(int doctorId,String firstName){
        return false;
    }
}

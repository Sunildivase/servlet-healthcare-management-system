package com.healthcareApp.repository;

import com.healthcareApp.model.Department;
import com.healthcareApp.service.ConnectionService;
import com.healthcareApp.service.DepartmentService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DepartmentRepository {

    public boolean createDepartment(Department department) throws SQLException {

        Connection connection = new ConnectionService().getConnection();

        String query = "INSERT INTO department(deptId,deptName,doctorId,hospitalId) (?, ? ,? ,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,department.getDeptId());
            preparedStatement.setString(2,department.getDeptName());
            preparedStatement.setInt(3,department.getDoctorId());
            preparedStatement.setInt(4,department.getHospitalId());

            int rowInserted = preparedStatement.executeUpdate();

            return rowInserted > 0;
            
        }catch (SQLException e){
            throw new RuntimeException("error inserting Department "+e.getMessage());
        }

    }

    public List<Department> displayDepartment(){
        return displayDepartment();
    }

    public boolean deleteDepartment(int deptId){
        return false;
    }

    public boolean updateDepartment(int deptId, String deptName){
        return false;
    }
}

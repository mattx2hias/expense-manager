package dev.matthias.data;

import dev.matthias.entities.Employee;
import dev.matthias.utilities.ConnectionUtil;
import dev.matthias.utilities.LogLevel;
import dev.matthias.utilities.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDAOPostgres implements EmployeeDAO{
    @Override
    public Employee createEmployee(Employee employee) {
        try {
            String query = "insert into employee values (?, ?, ?)";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, employee.getId());
            ps.setString(2, employee.getFirstName());
            ps.setString(3, employee.getLastName());
            ps.execute();

            Logger.log("Employee created.", LogLevel.INFO);
            return employee;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee readEmployee(int id) {
        try {
            String query = "select * from employee where employee_id = ?";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Employee(rs.getInt(1), rs.getString(2), rs.getString(3));
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee updateEmployee(int id) {
        return null;
    }

    @Override
    public Employee deleteEmployee(int id) {
        return null;
    }

    @Override
    public List<Employee> readAllEmployees() {
        return null;
    }
}

package dev.matthias.data;

import dev.matthias.entities.Employee;
import dev.matthias.utilities.ConnectionUtil;
import dev.matthias.utilities.EmployeeNotFoundException;
import dev.matthias.utilities.LogLevel;
import dev.matthias.utilities.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOPostgres implements EmployeeDAO{
    @Override
    public Employee createEmployee(Employee employee) {
        try {
            if(employee.getId() < 1000 || employee.getId() > 9999) return null;
            String query = "insert into employee values (?, ?, ?)";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, employee.getId());
            ps.setString(2, employee.getFirstName());
            ps.setString(3, employee.getLastName());
            ps.execute();

            Logger.log("Created employee: " + employee.getId(), LogLevel.INFO);
            return employee;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee readEmployee(int id) throws EmployeeNotFoundException{
        try {
            String query = "select * from employee where employee_id = ?";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Employee(rs.getInt(1), rs.getString(2), rs.getString(3));
            } else throw new EmployeeNotFoundException();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException {
        try {
            String query = "update employee set first_name = ?, last_name = ? where employee_id = ?";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setInt(3, employee.getId());

            if(ps.executeUpdate() == 1) {
                Logger.log("Updated employee: " + employee.getId(), LogLevel.INFO);
                return employee;
            } else throw new EmployeeNotFoundException();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteEmployee(int id) throws EmployeeNotFoundException {
        try {
            String query = "delete from employee where employee_id = ?";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            if(ps.executeUpdate() == 1) {
                Logger.log("Deleted employee: " + id, LogLevel.INFO);
                return true;
            } else throw new EmployeeNotFoundException();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Employee> readAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            String query = "select * from employee";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Employee employeeRecord = new Employee();
                employeeRecord.setId(rs.getInt("employee_id"));
                employeeRecord.setFirstName(rs.getString("first_name"));
                employeeRecord.setLastName(rs.getString("last_name"));
                employees.add(employeeRecord);
            }
            return employees;
        } catch (SQLException e) {
            e.printStackTrace();
            return employees;
        }
    }
}

package dev.matthias.service;

import dev.matthias.data.EmployeeDAO;
import dev.matthias.data.EmployeeDAOPostgres;
import dev.matthias.entities.Employee;

import java.util.Random;

public class EmployeeServiceImpl implements EmployeeService{

    EmployeeDAO employeeDAO = new EmployeeDAOPostgres();

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeDAO.createEmployee(employee);
    }

    @Override
    public Employee readEmployee(int id) {
        return null;
    }

    @Override
    public Employee updateEmployee(int id) {
        return null;
    }

    @Override
    public Employee deleteEmployee(int id) {
        return null;
    }

}

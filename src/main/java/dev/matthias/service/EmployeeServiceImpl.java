package dev.matthias.service;

import dev.matthias.data.EmployeeDAO;
import dev.matthias.data.EmployeeDAOPostgres;
import dev.matthias.entities.Employee;

import java.util.Random;

public class EmployeeServiceImpl implements EmployeeService{

    EmployeeDAO employeeDAO = new EmployeeDAOPostgres();

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setId(this.generateId());
        return employeeDAO.createEmployee(employee);
    }

    @Override
    public Employee readEmployee(int id) {
        return employeeDAO.readEmployee(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeDAO.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployee(int id) {
        return employeeDAO.deleteEmployee(id);
    }

    public int generateId() {
        int id = 0;
        Random rand = new Random();
        id = rand.nextInt(9999-1000) + 1000;
        return id;
    }

}

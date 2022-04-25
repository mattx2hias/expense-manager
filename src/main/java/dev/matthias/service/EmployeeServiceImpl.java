package dev.matthias.service;

import dev.matthias.data.EmployeeDAO;
import dev.matthias.data.EmployeeDAOPostgres;
import dev.matthias.entities.Employee;
import dev.matthias.exceptions.EmployeeNotFoundException;

import java.util.Random;

public class EmployeeServiceImpl implements EmployeeService{

    EmployeeDAO employeeDAO = new EmployeeDAOPostgres();
    private static final Random rand = new Random();

    @Override
    public Employee createEmployee(Employee employee) {
        if(employee.getId() == 0) {
            employee.setId(this.generateId());
        }
        return employeeDAO.createEmployee(employee);
    }

    @Override
    public Employee readEmployee(int id) {
        try {
            return employeeDAO.readEmployee(id);
        } catch (EmployeeNotFoundException e) {
            return null;
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        try {
            return employeeDAO.updateEmployee(employee);
        } catch (EmployeeNotFoundException e) {
            return null;
        }
    }

    @Override
    public boolean deleteEmployee(int id) {
        try {
            return employeeDAO.deleteEmployee(id);
        } catch (EmployeeNotFoundException e) {
            return false;
        }
    }

    public int generateId() {
        return rand.nextInt(9999-1000) + 1000;
    }

}

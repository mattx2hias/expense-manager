package dev.matthias.data;

import dev.matthias.entities.Employee;
import dev.matthias.exceptions.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeDAO {

    Employee createEmployee(Employee employee);

    Employee readEmployee(int id) throws EmployeeNotFoundException;

    Employee updateEmployee(Employee employee) throws EmployeeNotFoundException;

    boolean deleteEmployee(int id) throws EmployeeNotFoundException;

    List<Employee> readAllEmployees();
}

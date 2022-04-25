package dev.matthias.service;

import dev.matthias.entities.Employee;
import dev.matthias.exceptions.EmployeeNotFoundException;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    Employee readEmployee(int id) throws EmployeeNotFoundException;

    Employee updateEmployee(Employee employee);

    boolean deleteEmployee(int id);

}

package dev.matthias.service;

import dev.matthias.entities.Employee;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    Employee readEmployee(int id);

    Employee updateEmployee(int id);

    Employee deleteEmployee(int id);
}

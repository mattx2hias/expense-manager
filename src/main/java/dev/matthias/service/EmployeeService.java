package dev.matthias.service;

import dev.matthias.entities.Employee;

public interface EmployeeService {

    byte createEmployee();

    Employee readEmployee(int id);

    byte updateEmployee(int id);

    byte deleteEmployee(int id);
}

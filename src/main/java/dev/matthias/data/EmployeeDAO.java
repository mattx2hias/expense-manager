package dev.matthias.data;

import dev.matthias.entities.Employee;

import java.util.List;

public interface EmployeeDAO {

    Employee createEmployee(Employee employee);

    Employee readEmployee(int id);

    Employee updateEmployee(Employee employee);

    boolean deleteEmployee(int id);

    List<Employee> readAllEmployees();
}

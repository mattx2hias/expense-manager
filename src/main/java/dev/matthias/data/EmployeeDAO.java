package dev.matthias.data;

import dev.matthias.entities.Employee;
import dev.matthias.utilities.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeDAO {

    Employee createEmployee(Employee employee);

    Employee readEmployee(int id) throws EmployeeNotFoundException;

    Employee updateEmployee(Employee employee);

    boolean deleteEmployee(int id);

    List<Employee> readAllEmployees();
}

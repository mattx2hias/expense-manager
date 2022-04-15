package dev.matthias.data;

import dev.matthias.entities.Employee;

public interface EmployeeDAO {

    boolean createEmployee();

    Employee readEmployee(int id);

    boolean updateEmployee(int id);

    boolean deleteEmployee(int id);

    int[] readAllEmployeeIds();
}

package dev.matthias.daotests;

import dev.matthias.data.EmployeeDAO;
import dev.matthias.data.EmployeeDAOPostgres;
import dev.matthias.entities.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class EmployeeDAOTests {

    static EmployeeDAO employeeDAO = new EmployeeDAOPostgres();
    static Employee employee = null;

    @Test
    @Order(1)
    void createEmployee() {
        Employee caesar = new Employee("Julius", "Caesar");
        Employee savedEmployee = employeeDAO.createEmployee(caesar);
        EmployeeDAOTests.employee = caesar;
        Assertions.assertNotNull(savedEmployee);
    }

    @Test
    @Order(2)
    void readEmployee() {
        Employee retrievedEmployee = employeeDAO.readEmployee(employee.getId());
        Assertions.assertEquals("Julius", retrievedEmployee.getFirstName());
    }

    @Test
    void deleteEmployee() {

    }
}

package dev.matthias.servicetests;

import dev.matthias.entities.Employee;
import dev.matthias.service.EmployeeService;
import dev.matthias.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeServiceTests {

    EmployeeService employeeService = new EmployeeServiceImpl();
    EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

    @Test
    void createEmployee() {
        Employee employee = new Employee("Bob", "Barker");
        Employee savedEmployee = employeeService.createEmployee(employee);
        Assertions.assertNotNull(savedEmployee);
    }

    @Test
    void readEmployee() {

    }

    @Test
    void updateEmployee() {

    }

    @Test
    void deleteEmployee() {
        //Assertions.assertTrue(employeeService.deleteEmployee());
    }

    @Test
    void generateId() {
        Assertions.assertNotEquals(employeeServiceImpl.generateId(), employeeServiceImpl.generateId());
    }
}

package dev.matthias.daotests;

import dev.matthias.data.EmployeeDAO;
import dev.matthias.data.EmployeeDAOPostgres;
import dev.matthias.entities.Employee;
import org.junit.jupiter.api.*;

public class EmployeeDAOTests {

    EmployeeDAO employeeDAO;
    Employee testEmployee;

    @BeforeAll
    static void setUpAll() {
        System.out.println("Employee DAO Tests.");
    }

    @BeforeEach
    public void setUp() {
        employeeDAO = new EmployeeDAOPostgres();
        testEmployee = new Employee(1000, "Bob", "Barker");
    }

    @AfterEach
    public void tearDown() {
        employeeDAO = null;
        testEmployee = null;
    }

    @Test
    @Order(1)
    @DisplayName("Create Employee")
    void createEmployee() {
        Employee savedEmployee = employeeDAO.createEmployee(testEmployee);
        Assertions.assertNotNull(savedEmployee);
    }

    @Test
    @DisplayName("Create Employee With No ID")
    void createEmployeeWithNoID() {
        Assertions.assertNull(employeeDAO.createEmployee(new Employee("Bill", "Bob")));
    }

    @Test
    @Order(2)
    @DisplayName("Read Employee Based On ID")
    void readEmployee() {
        Employee retrievedEmployee = employeeDAO.readEmployee(testEmployee.getId());
        Assertions.assertEquals("Bob", retrievedEmployee.getFirstName());
    }

    @Test
    @Order(3)
    @DisplayName("Update Employee")
    void updateEmployee() {
        Employee updatedEmployee = employeeDAO.readEmployee(testEmployee.getId());
        updatedEmployee.setFirstName("Billy");
        updatedEmployee = employeeDAO.updateEmployee(updatedEmployee);
        Assertions.assertEquals("Billy", updatedEmployee.getFirstName());
    }

    @Test
    @DisplayName("Update Nonexistent Employee")
    void updateNonexistentEmployee() {
        Employee employee = new Employee(1111, "Hank", "Hill");
        Assertions.assertNull(employeeDAO.updateEmployee(employee));
    }

    @Test
    @Order(4)
    @DisplayName("Delete Employee")
    void deleteEmployee() {
        Assertions.assertTrue(employeeDAO.deleteEmployee(testEmployee.getId()));
    }

    @Test
    @DisplayName("Delete Nonexistent Employee")
    void deleteNonexistentEmployee() {
        Assertions.assertFalse(employeeDAO.deleteEmployee(5));
    }


}

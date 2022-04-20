package dev.matthias.servicetests;

import dev.matthias.entities.Employee;
import dev.matthias.service.EmployeeService;
import dev.matthias.service.EmployeeServiceImpl;
import dev.matthias.utilities.EmployeeNotFoundException;
import org.junit.jupiter.api.*;

public class EmployeeServiceTests {

    EmployeeService employeeService;
    Employee testEmployee;

    @BeforeAll
    static void setUpAll() {
        System.out.println("Employee Service Tests.");
    }

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl();
        testEmployee = new Employee(1000, "Bob", "Barker");
    }

    @AfterEach
    public void tearDown() {
        employeeService = null;
        testEmployee = null;
    }

    @Test
    @Order(1)
    @DisplayName("Should create new employee record")
    void shouldCreateNewEmployeeRecord() {
        Employee savedEmployee = employeeService.createEmployee(testEmployee);
        Assertions.assertNotNull(savedEmployee);
    }

    @Test
    @Order(2)
    @DisplayName("Should get employee record")
    void shouldGetEmployeeRecord() throws EmployeeNotFoundException {
        Employee retrievedEmployee = employeeService.readEmployee(testEmployee.getId());
        Assertions.assertEquals("Bob", retrievedEmployee.getFirstName());
    }

    @Test
    @Order(3)
    @DisplayName("Should update employee record")
    void shouldUpdateEmployeeRecord() {
        testEmployee.setFirstName("Bobby");
        Employee updatedEmployee = employeeService.updateEmployee(testEmployee);
        Assertions.assertEquals("Bobby", updatedEmployee.getFirstName());
    }

    @Test
    @Order(4)
    @DisplayName("Should delete employee record")
    void shouldDeleteEmployeeRecord() {
        Assertions.assertTrue(employeeService.deleteEmployee(testEmployee.getId()));
    }


    @Test
    @DisplayName("Should generate unique random 4 digit number")
    void shouldGenerateUniqueRandom4DigitNumber() {
        EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
        Assertions.assertNotEquals(employeeServiceImpl.generateId(), employeeServiceImpl.generateId());
    }

}

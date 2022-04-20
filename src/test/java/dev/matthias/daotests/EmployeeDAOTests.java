package dev.matthias.daotests;

import dev.matthias.data.EmployeeDAO;
import dev.matthias.data.EmployeeDAOPostgres;
import dev.matthias.entities.Employee;
import dev.matthias.utilities.EmployeeNotFoundException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
    @DisplayName("Should create new employee")
    void shouldCreateNewEmployee() {
        Employee savedEmployee = employeeDAO.createEmployee(testEmployee);
        Assertions.assertNotNull(savedEmployee);
    }

    @Test
    @DisplayName("Should not create employee with no ID")
    void shouldNotCreateEmployeeNoID() {
        Assertions.assertNull(employeeDAO.createEmployee(new Employee("Bill", "Bob")));
    }

    @Test
    @DisplayName("Should not create employee with invalid ID range")
    void shouldNotCreateEmployeeWithInvalidIdRange() {
        Assertions.assertNull(employeeDAO.createEmployee(new Employee( 999,"Bill", "Bob")));
        Assertions.assertNull(employeeDAO.createEmployee(new Employee( 10000,"Bill", "Bob")));
    }
    
    @Test
    @Order(2)
    @DisplayName("Should get employee record")
    void shouldGetEmployeeRecord() throws EmployeeNotFoundException {
        Employee retrievedEmployee = employeeDAO.readEmployee(testEmployee.getId());
        Assertions.assertEquals("Bob", retrievedEmployee.getFirstName());
    }

    @Test
    @DisplayName("Should not get nonexistent employee")
    void shouldNotGetNonexistentEmployee() {
        Throwable exception = assertThrows(EmployeeNotFoundException.class,
                () -> { employeeDAO.readEmployee(0); });

        Assertions.assertEquals("\nEmployee does not exist.", exception.getMessage());
    }
    
    @Test
    @Order(3)
    @DisplayName("Should update employee record")
    void shouldUpdateEmployeeRecord() throws EmployeeNotFoundException {
        Employee updatedEmployee = employeeDAO.readEmployee(testEmployee.getId());
        updatedEmployee.setFirstName("Billy");
        updatedEmployee = employeeDAO.updateEmployee(updatedEmployee);
        Assertions.assertEquals("Billy", updatedEmployee.getFirstName());
    }
    
    @Test
    @DisplayName("Should not update nonexistent employee")
    void shouldNotUpdateNonexistentEmployee() {
        Employee employee = new Employee(1111, "Hank", "Hill");
        Assertions.assertNull(employeeDAO.updateEmployee(employee));
    }
    
    @Test
    @Order(4)
    @DisplayName("Should delete employee record")
    void shouldDeleteEmployeeRecord() {
        Assertions.assertTrue(employeeDAO.deleteEmployee(testEmployee.getId()));
    }
    
    @Test
    @DisplayName("Should not delete nonexistent employee")
    void shouldNotDeleteNonexistentEmployee() {
        Assertions.assertFalse(employeeDAO.deleteEmployee(5));
    }
}

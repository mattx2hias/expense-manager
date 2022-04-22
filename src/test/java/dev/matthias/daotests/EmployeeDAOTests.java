package dev.matthias.daotests;

import dev.matthias.data.EmployeeDAO;
import dev.matthias.data.EmployeeDAOPostgres;
import dev.matthias.entities.Employee;
import dev.matthias.utilities.EmployeeNotFoundException;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeDAOTests {

    static EmployeeDAO employeeDAO = new EmployeeDAOPostgres();
    static Employee testEmployee = new Employee(1000, "Bob", "Barker");
    
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
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> employeeDAO.readEmployee(0));
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
    void shouldNotUpdateNonexistentEmployee() throws EmployeeNotFoundException {
        Employee employee = new Employee(100, "Hank", "Hill");
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> employeeDAO.updateEmployee(employee));
    }
    
    @Test
    @Order(4)
    @DisplayName("Should delete employee record")
    void shouldDeleteEmployeeRecord() throws EmployeeNotFoundException {
        Assertions.assertTrue(employeeDAO.deleteEmployee(testEmployee.getId()));
    }
    
    @Test
    @DisplayName("Should not delete nonexistent employee")
    void shouldNotDeleteNonexistentEmployee() throws EmployeeNotFoundException {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> employeeDAO.deleteEmployee(5));
    }
}

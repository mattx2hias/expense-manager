package dev.matthias.servicetests;

import dev.matthias.entities.Employee;
import dev.matthias.entities.Expense;
import dev.matthias.service.EmployeeService;
import dev.matthias.service.EmployeeServiceImpl;
import dev.matthias.service.ExpenseService;
import dev.matthias.service.ExpenseServiceImpl;
import dev.matthias.utilities.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

class ExpenseServiceTests {

    EmployeeService employeeService;
    Employee testEmployee;
    ExpenseService expenseService;
    Expense testExpense;

    @BeforeAll
    static void setUpAll() {
        System.out.println("Expense Service Tests.");
    }

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl();
        testEmployee = new Employee(1000, "Bob", "Barker");
        expenseService = new ExpenseServiceImpl();
        testExpense = new Expense(1000, "Crate Of Bananas", Status.PENDING, 1, testEmployee.getId());
    }

    @AfterEach
    public void tearDown() {
        employeeService = null;
        testEmployee = null;
        testExpense = null;
        expenseService = null;
    }
}

package dev.matthias.servicetests;

import dev.matthias.entities.Expense;
import dev.matthias.exceptions.ExpenseCannotBeNegativeException;
import dev.matthias.exceptions.ExpenseNotFoundException;
import dev.matthias.service.ExpenseService;
import dev.matthias.service.ExpenseServiceImpl;
import dev.matthias.exceptions.EmployeeNotFoundException;
import dev.matthias.exceptions.ExpenseAlreadyApprovedOrDeniedException;
import dev.matthias.utilities.Status;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ExpenseServiceTests {

    static ExpenseService expenseService = new ExpenseServiceImpl();
    Expense testExpense;

    @BeforeEach
    void setup() {
        testExpense = new Expense(2222, "Crate Of Bananas", Status.PENDING, 100.25, 1);
    }

    @AfterEach
    void teardown() {
        testExpense = null;
    }

    @Test
    @Order(1)
    @DisplayName("Should create new expense")
    void shouldCreateNewExpense() throws EmployeeNotFoundException, ExpenseCannotBeNegativeException {
        Expense savedExpense = expenseService.createExpense(testExpense);
        testExpense.setId(savedExpense.getId());
        Assertions.assertEquals("Crate Of Bananas", savedExpense.getName());
        Assertions.assertEquals(100.25, savedExpense.getCost());
        Assertions.assertEquals(1, savedExpense.getIssuerId());
    }

    @Test
    @DisplayName("Should not create expense with no valid employee")
    void shouldNotCreateExpenseWithNoValidEmployee() {
        testExpense.setIssuerId(0);
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> expenseService.createExpense(testExpense));
        testExpense.setIssuerId(testExpense.getIssuerId());
    }

    @Test
    @DisplayName("Should not create expense with negative cost")
    void shouldNotCreateExpenseWithNegativeCost() {
        testExpense.setCost(-1);
        Assertions.assertThrows(ExpenseCannotBeNegativeException.class,
                () -> expenseService.createExpense(testExpense));
    }


    @Test
    @Order(2)
    @DisplayName("Should read expense")
    void shouldReadExpense() {
        Expense retrievedExpense = expenseService.readExpense(testExpense.getId());
        Assertions.assertEquals("Crate Of Bananas", retrievedExpense.getName());
    }

    @Test
    @Order(3)
    @DisplayName("Should update expense")
    void shouldUpdateExpense() throws ExpenseAlreadyApprovedOrDeniedException, ExpenseNotFoundException, ExpenseCannotBeNegativeException {
        testExpense.setName("Box Of Bananas");
        Expense updatedExpense = expenseService.updateExpense(testExpense);
        Assertions.assertEquals("Box Of Bananas", updatedExpense.getName());
    }

    @Test
    @Order(4)
    @DisplayName("Should not update approved expense")
    void shouldNotUpdateApprovedExpense() throws ExpenseNotFoundException, ExpenseAlreadyApprovedOrDeniedException, ExpenseCannotBeNegativeException {
        testExpense.setStatus(Status.APPROVED);
        Expense expense = expenseService.updateExpense(testExpense);
        Assertions.assertThrows(ExpenseAlreadyApprovedOrDeniedException.class,
                () -> expenseService.updateExpense(expense));
    }

    @Test
    @DisplayName("Should not update expense cost to negative")
    void shouldNotUpdateExpenseCostToNegative() {
        testExpense.setCost(-1);
        Assertions.assertThrows(ExpenseCannotBeNegativeException.class,
                () -> expenseService.updateExpense(testExpense));
    }


    @Test
    @Order(5)
    @DisplayName("Should delete expense")
    void shouldDeleteExpense() {
        Assertions.assertTrue(expenseService.deleteExpense(testExpense.getId()));
    }
}

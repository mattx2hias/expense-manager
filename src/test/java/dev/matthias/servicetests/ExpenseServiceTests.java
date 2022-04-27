package dev.matthias.servicetests;

import dev.matthias.entities.Expense;
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
    static Expense testExpense = new Expense("Crate Of Bananas", 100.25, 1);

    @Test
    @Order(1)
    @DisplayName("Should create new expense")
    void shouldCreateNewExpense() throws EmployeeNotFoundException {
        Expense savedExpense = expenseService.createExpense(testExpense);
        testExpense.setId(savedExpense.getId());
        Assertions.assertEquals("Crate Of Bananas", savedExpense.getName());
        Assertions.assertEquals(100.25, savedExpense.getCost());
        Assertions.assertEquals(1, savedExpense.getIssuerId());
    }

    @Test
    @DisplayName("Should not create expense with no valid employee")
    void shouldNotCreateExpenseWithNoValidEmployee() {
        Expense expense = testExpense;
        expense.setIssuerId(0);
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> expenseService.createExpense(expense));
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
    void shouldUpdateExpense() throws ExpenseAlreadyApprovedOrDeniedException, ExpenseNotFoundException {
        testExpense.setName("Box Of Bananas");
        Expense updatedExpense = expenseService.updateExpense(testExpense);
        Assertions.assertEquals("Box Of Bananas", updatedExpense.getName());
    }
    
    @Test
    @Order(4)
    @DisplayName("Should not update approved expense")
    void shouldNotUpdateApprovedExpense() throws ExpenseNotFoundException, ExpenseAlreadyApprovedOrDeniedException {
        testExpense.setStatus(Status.APPROVED);
        Expense expense = expenseService.updateExpense(testExpense);
        Assertions.assertThrows(ExpenseAlreadyApprovedOrDeniedException.class,
                () -> expenseService.updateExpense(expense));
    }

    @Test
    @Order(5)
    @DisplayName("Should delete expense")
    void shouldDeleteExpense() {
        Assertions.assertTrue(expenseService.deleteExpense(testExpense.getId()));
    }
}

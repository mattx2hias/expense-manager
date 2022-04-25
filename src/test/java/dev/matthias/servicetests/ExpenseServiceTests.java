package dev.matthias.servicetests;

import dev.matthias.entities.Expense;
import dev.matthias.service.ExpenseService;
import dev.matthias.service.ExpenseServiceImpl;
import dev.matthias.exceptions.EmployeeNotFoundException;
import dev.matthias.exceptions.ExpenseAlreadyApprovedOrDeniedException;
import dev.matthias.utilities.Status;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ExpenseServiceTests {

    static ExpenseService expenseService = new ExpenseServiceImpl();
    static Expense testExpense = new Expense(999, "Crate Of Bananas", 100.25, 1);

    @Test
    @Order(1)
    @DisplayName("Should create new expense")
    void shouldCreateNewExpense() throws EmployeeNotFoundException {
        Expense savedExpense = expenseService.createExpense(testExpense);
        Assertions.assertEquals(999, savedExpense.getId());
    }

    @Test
    @Order(2)
    @DisplayName("Should read expense")
    void shouldReadExpense() {
        Expense retrievedExpense = expenseService.readExpense(testExpense.getId());
        Assertions.assertEquals(999, retrievedExpense.getId());
    }

    @Test
    @Order(3)
    @DisplayName("Should update expense")
    void shouldUpdateExpense() throws ExpenseAlreadyApprovedOrDeniedException {
        testExpense.setName("Box Of Bananas");
        Expense updatedExpense = expenseService.updateExpense(testExpense);
        Assertions.assertEquals("Box Of Bananas", updatedExpense.getName());
    }
    
    @Test
    @DisplayName("Should not update approved expense")
    void shouldNotUpdateApprovedExpense() {
        testExpense.setStatus(Status.APPROVED);
        Assertions.assertThrows(ExpenseAlreadyApprovedOrDeniedException.class,
                () -> expenseService.updateExpense(testExpense));
    }

    @Test
    @DisplayName("Should not update denied expense")
    void shouldNotUpdateDeniedExpense() {
        testExpense.setStatus(Status.DENIED);
        Assertions.assertThrows(ExpenseAlreadyApprovedOrDeniedException.class,
                () -> expenseService.updateExpense(testExpense));
    }

    @Test
    @Order(4)
    @DisplayName("Should delete expense")
    void shouldDeleteExpense() {
        Assertions.assertTrue(expenseService.deleteExpense(testExpense.getId()));
    }
}

package dev.matthias.daotests;

import dev.matthias.data.ExpenseDAO;
import dev.matthias.data.ExpenseDAOPostgres;
import dev.matthias.entities.Expense;
import dev.matthias.exceptions.ExpenseAlreadyApprovedOrDeniedException;
import dev.matthias.exceptions.ExpenseNotFoundException;
import dev.matthias.utilities.Status;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ExpenseDAOTests {

    static ExpenseDAO expenseDAO = new ExpenseDAOPostgres();
    static Expense testExpense = new Expense(1000, "Crate Of Bananas", Status.PENDING, 100.25, 1);

    @Test
    @Order(1)
    @DisplayName("Should create new expense")
    void shouldCreateNewExpense() {
        Expense savedExpense = expenseDAO.createExpense(testExpense);
        Assertions.assertNotNull(savedExpense);
    }

    @Test
    @Order(2)
    @DisplayName("Should get expense record")
    void shouldGetExpenseRecord() throws ExpenseNotFoundException {
        Expense retrievedExpense = expenseDAO.readExpense(testExpense.getId());
        Assertions.assertEquals("Crate Of Bananas", retrievedExpense.getName());
    }

    @Test
    @DisplayName("Should not get nonexistent expense record")
    void shouldNotGetNonexistentExpenseRecord() {
        Assertions.assertThrows(ExpenseNotFoundException.class, () -> expenseDAO.readExpense(0));
    }

    @Test
    @DisplayName("Should get not null expense list")
    void shouldGetNotNullExpenseList() {
        Assertions.assertNotNull(expenseDAO.readAllExpenses());
    }

    @Test
    @DisplayName("Should not store duplicate expenses")
    void shouldNotStoreDuplicateExpenses() {
        List<Expense> expenseList = expenseDAO.readAllExpenses();
        Assertions.assertNotEquals(expenseList.get(0), expenseList.get(1));
    }

    @Test
    @Order(3)
    @DisplayName("Should update expense record")
    void shouldUpdateExpenseRecord() throws ExpenseAlreadyApprovedOrDeniedException {
        testExpense.setStatus(Status.APPROVED);
        Expense updatedExpense = expenseDAO.updateExpense(testExpense);
        Assertions.assertEquals(Status.APPROVED, updatedExpense.getStatus());
    }

    @Test
    @Order(4)
    @DisplayName("Should delete expense record")
    void shouldDeleteExpenseRecord() throws ExpenseNotFoundException {
        Assertions.assertTrue(expenseDAO.deleteExpense(testExpense.getId()));
    }

    @Test
    @DisplayName("Should not delete nonexistent record")
    void shouldNotDeleteNonexistentRecord() {
        Assertions.assertThrows(ExpenseNotFoundException.class, () -> expenseDAO.deleteExpense(0));
    }

}

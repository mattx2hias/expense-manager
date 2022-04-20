package dev.matthias.daotests;

import dev.matthias.data.EmployeeDAO;
import dev.matthias.data.EmployeeDAOPostgres;
import dev.matthias.data.ExpenseDAO;
import dev.matthias.data.ExpenseDAOPostgres;
import dev.matthias.entities.Employee;
import dev.matthias.entities.Expense;
import dev.matthias.utilities.EmployeeNotFoundException;
import dev.matthias.utilities.Status;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpenseDAOTests {

    ExpenseDAO expenseDAO;
    Expense testExpense;

    @Test
    @DisplayName("Should create new expense")
    void shouldCreateNewExpense() {
        expenseDAO = new ExpenseDAOPostgres();
        testExpense = new Expense(1000, "Crate Of Bananas", Status.PENDING, 1.00, 1000);
        Expense newExpense = expenseDAO.createExpense(testExpense);
        Assertions.assertEquals("Crate Of Bananas", newExpense.getName());
    }

    @Test
    @DisplayName("Should not create expense if issuer ID is nonexistent")
    void shouldNotCreateExpenseIfIssuerIdIsNonexistent() {
        testExpense.setIssuerId(0);
        Throwable exception = assertThrows(EmployeeNotFoundException.class, () -> {
            expenseDAO.createExpense(testExpense);
        });
        Assertions.assertEquals("\nEmployee does not exist.", exception.getMessage());
    }


}

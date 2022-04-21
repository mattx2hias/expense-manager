package dev.matthias.daotests;

import dev.matthias.data.ExpenseDAO;
import dev.matthias.data.ExpenseDAOPostgres;
import dev.matthias.entities.Expense;
import dev.matthias.utilities.ExpenseAlreadyApprovedOrDeniedException;
import dev.matthias.utilities.ExpenseNotFoundException;
import dev.matthias.utilities.Status;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertThrows;


class ExpenseDAOTests {

    ExpenseDAO expenseDAO;
    Expense testExpense;

    @BeforeAll
    static void setUpAll() {
        System.out.println("Expense DAO Tests.");
    }

    @BeforeEach
    public void setUp() {
        expenseDAO = new ExpenseDAOPostgres();
        testExpense = new Expense(1000, "Crate Of Bananas", Status.PENDING, 100.25, 1);
    }

    @AfterEach
    public void tearDown() {
        expenseDAO = null;
        testExpense = null;
    }

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
        Throwable exception = assertThrows(ExpenseNotFoundException.class,
                () -> { expenseDAO.readExpense(0); });
        Assertions.assertEquals("Expense does not exist.", exception.getMessage());
    }


    @Test
    @Order(3)
    @DisplayName("Should update expense record")
    void shouldUpdateExpenseRecord() throws ExpenseAlreadyApprovedOrDeniedException {
        testExpense.setName("Box Of Bananas");
        Expense updatedExpense = expenseDAO.updateExpense(testExpense);
        Assertions.assertEquals("Box Of Bananas", updatedExpense.getName());
    }

    @Test
    @DisplayName("Should not update approved expense")
    void shouldNotUpdateApprovedExpense() {
        testExpense.setStatus(Status.APPROVED);
        testExpense.setId(0);
        Expense approvedExpense = expenseDAO.createExpense(testExpense);
        Throwable exception = assertThrows(ExpenseAlreadyApprovedOrDeniedException.class,
                () -> { expenseDAO.updateExpense(approvedExpense); });
        Assertions.assertEquals("\nExpense already approved.", exception.getMessage());
        expenseDAO.deleteExpense(0);
    }

    @Test
    @DisplayName("Should not update denied expense")
    void shouldNotUpdateDeniedExpense() {
        testExpense.setStatus(Status.DENIED);
        testExpense.setId(0);
        Expense deniedExpense = expenseDAO.createExpense(testExpense);
        Throwable exception = assertThrows(ExpenseAlreadyApprovedOrDeniedException.class,
                () -> { expenseDAO.updateExpense(deniedExpense); });
        Assertions.assertEquals("\nExpense already denied.", exception.getMessage());
        expenseDAO.deleteExpense(0);
    }


    @Test
    @Order(4)
    @DisplayName("Should delete expense record")
    void shouldDeleteExpenseRecord() {
        Assertions.assertTrue(expenseDAO.deleteExpense(testExpense.getId()));
    }
}

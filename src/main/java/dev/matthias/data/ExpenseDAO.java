package dev.matthias.data;

import dev.matthias.entities.Expense;
import dev.matthias.utilities.ExpenseAlreadyApprovedOrDeniedException;

import java.util.List;

public interface ExpenseDAO {

    Expense createExpense(Expense expense);

    Expense readExpense(int id);

    Expense updateExpense(Expense expense) throws ExpenseAlreadyApprovedOrDeniedException;

    Expense deleteExpense(int id);

    List<Expense> readAllExpenses();
}

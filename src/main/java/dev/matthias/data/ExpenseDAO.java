package dev.matthias.data;

import dev.matthias.entities.Expense;
import dev.matthias.exceptions.ExpenseAlreadyApprovedOrDeniedException;
import dev.matthias.exceptions.ExpenseNotFoundException;

import java.util.List;

public interface ExpenseDAO {

    Expense createExpense(Expense expense);

    Expense readExpense(int id) throws ExpenseNotFoundException;

    Expense updateExpense(Expense expense) throws ExpenseAlreadyApprovedOrDeniedException;

    boolean deleteExpense(int id) throws ExpenseNotFoundException;

    List<Expense> readAllExpenses();
}

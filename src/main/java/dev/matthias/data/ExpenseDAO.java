package dev.matthias.data;

import dev.matthias.entities.Expense;

import java.util.List;

public interface ExpenseDAO {

    Expense createExpense(Expense expense);

    Expense readExpense(int id);

    Expense updateExpense(Expense expense);

    Expense deleteExpense(int id);

    List<Expense> readAllExpenses();
}

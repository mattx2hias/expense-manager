package dev.matthias.data;

import dev.matthias.entities.Expense;

import java.util.List;

public interface ExpenseDAO {

    Expense createExpense(Expense expense);

    Expense readExpense();

    Expense updateExpense(int id);

    Expense deleteExpense(int id);

    List<Expense> readAllExpenses();
}

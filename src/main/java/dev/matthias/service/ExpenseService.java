package dev.matthias.service;

import dev.matthias.entities.Expense;

public interface ExpenseService {

    Expense createExpense(Expense expense);

    Expense readExpense(int id);

    Expense updateExpense(int id);

    Expense deleteExpense(int id);
}

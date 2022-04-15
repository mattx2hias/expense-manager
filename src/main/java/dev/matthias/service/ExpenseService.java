package dev.matthias.service;

import dev.matthias.entities.Expense;

public interface ExpenseService {

    byte createExpense();

    Expense readExpense(int id);

    byte updateExpense(int id);

    byte deleteExpense(int id);
}

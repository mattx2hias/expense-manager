package dev.matthias.service;

import dev.matthias.entities.Expense;
import dev.matthias.utilities.EmployeeNotFoundException;

public interface ExpenseService {

    Expense createExpense(Expense expense) throws EmployeeNotFoundException;

    Expense readExpense(int id);

    Expense updateExpense(Expense expense);

    Expense deleteExpense(int id);
}

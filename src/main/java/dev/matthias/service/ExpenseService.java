package dev.matthias.service;

import dev.matthias.entities.Expense;
import dev.matthias.utilities.EmployeeNotFoundException;
import dev.matthias.utilities.ExpenseAlreadyApprovedOrDeniedException;

public interface ExpenseService {

    Expense createExpense(Expense expense) throws EmployeeNotFoundException;

    Expense readExpense(int id);

    Expense updateExpense(Expense expense) throws ExpenseAlreadyApprovedOrDeniedException;

    boolean deleteExpense(int id);
}

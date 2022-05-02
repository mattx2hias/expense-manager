package dev.matthias.service;

import dev.matthias.entities.Expense;
import dev.matthias.exceptions.EmployeeNotFoundException;
import dev.matthias.exceptions.ExpenseAlreadyApprovedOrDeniedException;
import dev.matthias.exceptions.ExpenseCannotBeNegativeException;
import dev.matthias.exceptions.ExpenseNotFoundException;

public interface ExpenseService {

    Expense createExpense(Expense expense) throws EmployeeNotFoundException, ExpenseCannotBeNegativeException;

    Expense readExpense(int id);

    Expense updateExpense(Expense expense) throws ExpenseAlreadyApprovedOrDeniedException, ExpenseNotFoundException, ExpenseCannotBeNegativeException;

    boolean deleteExpense(int id);
}

package dev.matthias.data;

import dev.matthias.entities.Expense;

public interface ExpenseDAO {

    boolean createExpense();

    Expense readExpense();

    boolean updateExpense(int id);

    boolean deleteExpense(int id);

    int[] readAllExpenseIds();
}

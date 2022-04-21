package dev.matthias.utilities;

import dev.matthias.entities.Expense;

public class ExpenseNotFoundException extends Exception{
    public ExpenseNotFoundException() {
        super("Expense does not exist.");
    }
}

package dev.matthias.exceptions;

public class ExpenseNotFoundException extends Exception{
    public ExpenseNotFoundException() {
        super("Expense does not exist.");
    }
}

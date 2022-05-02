package dev.matthias.exceptions;

public class ExpenseCannotBeNegativeException extends Exception{
    public ExpenseCannotBeNegativeException() {super("Expense cannot be less than 0.");}
}

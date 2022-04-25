package dev.matthias.exceptions;

public class ExpenseAlreadyApprovedOrDeniedException extends Exception{
    public ExpenseAlreadyApprovedOrDeniedException(String msg) {
        super(msg);
    }
}

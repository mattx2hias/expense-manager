package dev.matthias.utilities;

public class ExpenseAlreadyApprovedOrDeniedException extends Exception{
    public ExpenseAlreadyApprovedOrDeniedException(String msg) {
        super(msg);
    }
}

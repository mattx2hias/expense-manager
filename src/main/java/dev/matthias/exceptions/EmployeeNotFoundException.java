package dev.matthias.exceptions;

public class EmployeeNotFoundException extends Exception{
    public EmployeeNotFoundException() {
        super("Employee does not exist.");
    }
}

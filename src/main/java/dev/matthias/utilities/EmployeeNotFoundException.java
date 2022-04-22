package dev.matthias.utilities;

public class EmployeeNotFoundException extends Exception{
    public EmployeeNotFoundException() {
        super("Employee does not exist.");
    }
}

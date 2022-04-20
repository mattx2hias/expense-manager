package dev.matthias.utilities;

public class EmployeeNotFoundException extends Exception{
    public EmployeeNotFoundException() {
        super("\nEmployee does not exist.");
    }
}

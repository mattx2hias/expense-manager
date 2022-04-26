package dev.matthias.api;

import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(3000);

        app.post("/employees", EmployeeAPI.createEmployee());
        app.get("/employees", EmployeeAPI.readAllEmployees());
        app.get("/employees/{id}", EmployeeAPI.readEmployee());
        app.put("/employees/{id}", EmployeeAPI.updateEmployee());
        app.delete("employees/{id}", EmployeeAPI.deleteEmployee());

        app.post("/expenses", ExpenseAPI.createExpense());
        app.get("/expenses", ExpenseAPI.readExpenses());
        app.get("/expenses/{id}", ExpenseAPI.readExpense());
        app.put("/expenses/{id}", ExpenseAPI.updateExpense());
        app.patch("/expenses/{id}/{status}", ExpenseAPI.approveOrDenyExpense());
        app.delete("/expenses/{id}", ExpenseAPI.deleteExpense());
    }
}

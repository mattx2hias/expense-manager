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

    }
}

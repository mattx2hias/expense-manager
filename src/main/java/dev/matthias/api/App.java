package dev.matthias.api;

import com.google.gson.Gson;
import dev.matthias.entities.Employee;
import dev.matthias.service.EmployeeService;
import dev.matthias.service.EmployeeServiceImpl;
import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {

        EmployeeService employeeService = new EmployeeServiceImpl();
        Gson gson = new Gson();
        Javalin app = Javalin.create().start(3000);

        app.post("/employees", context -> {
            String body = context.body();
            Employee newEmployee = employeeService.createEmployee(gson.fromJson(body, Employee.class));
            if(newEmployee != null) {
                context.status(201);
            } else {
                context.status(424);
            }
        });

    }
}

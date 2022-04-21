package dev.matthias.api;

import com.google.gson.Gson;
import dev.matthias.data.EmployeeDAO;
import dev.matthias.data.EmployeeDAOPostgres;
import dev.matthias.entities.Employee;
import dev.matthias.service.EmployeeService;
import dev.matthias.service.EmployeeServiceImpl;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        EmployeeDAO employeeDAO = new EmployeeDAOPostgres();
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

        app.get("/employees", context -> {
            StringBuilder display = new StringBuilder();
            List<Employee> employees = employeeDAO.readAllEmployees();
            if(employees.isEmpty()) {
                context.status(404);
                context.result("No employees found.");
            } else {
                for(Employee e : employees) display.append(e.toString()).append("\n");
                context.status(200);
                context.result(String.valueOf(display));
            }

        });

        app.get("/employees/{id}", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            Employee employee = employeeService.readEmployee(id);
            if(employee == null) {
                context.status(404);
                context.result("Employee: " + id + " not found.");
            } else {
                context.result(employee.toString());
                context.status(200);
            }
        });

        app.put("/employees/{id}", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            Employee employee = employeeService.readEmployee(id);
            String body = context.body();

            if(null == employee) {
                context.status(404);
            } else {
                Employee updatedEmployee = gson.fromJson(body, Employee.class);
                employee.setFirstName(updatedEmployee.getFirstName());
                employee.setLastName(updatedEmployee.getLastName());
                employee = employeeService.updateEmployee(employee);
                context.status(200);
                context.result("Updated: " + employee.getId());
            }
        });

        app.delete("employees/{id}", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            if(employeeService.deleteEmployee(id)) {
                context.status(200);
                context.result("Deleted: " + id);
            } else if(null == employeeService.readEmployee(id)) {
                context.status(404);
                context.result("Employee: " + id + "not found.");
            } else {
                context.status(400);
            }
        });

    }
}

package dev.matthias.api;

import com.google.gson.Gson;
import dev.matthias.data.EmployeeDAO;
import dev.matthias.data.EmployeeDAOPostgres;
import dev.matthias.entities.Employee;
import dev.matthias.service.EmployeeService;
import dev.matthias.service.EmployeeServiceImpl;
import io.javalin.http.Handler;

import java.util.List;

public class EmployeeAPI {

    static Gson gson = new Gson();
    static EmployeeDAO employeeDAO = new EmployeeDAOPostgres();
    static EmployeeService employeeService = new EmployeeServiceImpl();

    private EmployeeAPI(){}

    static Handler createEmployee() {
        return context -> {
            String body = context.body();
            Employee newEmployee = employeeService.createEmployee(gson.fromJson(body, Employee.class));
            if(newEmployee !=null) {
                context.status(201);
            } else {
                context.status(424);
            }
        };
    }

    static Handler readAllEmployees() {
        return context -> {
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
        };
    }

    static Handler readEmployee() {
        return context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            Employee employee = employeeService.readEmployee(id);
            if(employee == null) {
                context.status(404);
                context.result("Employee: " + id + " not found.");
            } else {
                context.result(employee.toString());
                context.status(200);
            }
        };
    }

    static Handler updateEmployee() {
        return context -> {
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
        };
    }

    static Handler deleteEmployee() {
        return context -> {
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
        };
    }
}

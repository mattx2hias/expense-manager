package dev.matthias.api;

import com.google.gson.Gson;
import dev.matthias.data.ExpenseDAO;
import dev.matthias.data.ExpenseDAOPostgres;
import dev.matthias.entities.Expense;
import dev.matthias.service.ExpenseService;
import dev.matthias.service.ExpenseServiceImpl;
import io.javalin.http.Handler;

import java.util.List;

public class EmployeeExpenseAPI {

    static Gson gson = new Gson();
    static ExpenseDAO expenseDAO = new ExpenseDAOPostgres();
    static ExpenseService expenseService = new ExpenseServiceImpl();

    private EmployeeExpenseAPI(){}

    static Handler readEmployeeExpenses() {
        return context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            List<Expense> expenses = expenseDAO.readAllExpenses();
            if(expenses.isEmpty()) {
                context.status(404);
                context.result("No expenses found.");
            }
            StringBuilder display = new StringBuilder();
            expenses.stream().filter(e -> e.getIssuerId() == id)
                    .forEach(e -> display.append(e).append("\n"));
            if(display.length() < 1) {
                context.status(404);
                context.result("No expenses found for employee: " + id);
            }else {
                context.status(200);
                context.result(String.valueOf(display));
            }

        };
    }

    static Handler createEmployeeExpense() {
        return context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            Expense expense = gson.fromJson(context.body(), Expense.class);
            expense.setIssuerId(id);
            expense = expenseService.createExpense(expense);
            if(expense != null) {
                context.status(201);
                context.result("Created Expense: " + expense.getId());
            } else {
                context.status(400);
            }
        };
    }
}

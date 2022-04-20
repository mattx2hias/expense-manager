package dev.matthias.service;

import dev.matthias.data.EmployeeDAO;
import dev.matthias.data.EmployeeDAOPostgres;
import dev.matthias.data.ExpenseDAO;
import dev.matthias.data.ExpenseDAOPostgres;
import dev.matthias.entities.Employee;
import dev.matthias.entities.Expense;
import dev.matthias.utilities.EmployeeNotFoundException;

public class ExpenseServiceImpl implements ExpenseService{

    EmployeeDAO employeeDAO = new EmployeeDAOPostgres();
    ExpenseDAO expenseDAO = new ExpenseDAOPostgres();

    @Override
    public Expense createExpense(Expense expense) throws EmployeeNotFoundException {
        if(employeeDAO.readAllEmployees().stream().noneMatch(e -> e.getId() == expense.getIssuerId()))
            throw new EmployeeNotFoundException();
        else return expenseDAO.createExpense(expense);
    }

    @Override
    public Expense readExpense(int id) {
        return null;
    }

    @Override
    public Expense updateExpense(Expense expense) {
        return null;
    }

    @Override
    public Expense deleteExpense(int id) {
        return null;
    }
}

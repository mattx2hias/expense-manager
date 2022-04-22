package dev.matthias.service;

import dev.matthias.data.EmployeeDAO;
import dev.matthias.data.EmployeeDAOPostgres;
import dev.matthias.data.ExpenseDAO;
import dev.matthias.data.ExpenseDAOPostgres;
import dev.matthias.entities.Expense;
import dev.matthias.utilities.EmployeeNotFoundException;
import dev.matthias.utilities.ExpenseAlreadyApprovedOrDeniedException;
import dev.matthias.utilities.ExpenseNotFoundException;
import dev.matthias.utilities.Status;

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
        try {
            return expenseDAO.readExpense(id);
        } catch (ExpenseNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense updateExpense(Expense expense) throws ExpenseAlreadyApprovedOrDeniedException {
        if(expense.getStatus().equals(Status.APPROVED))
            throw new ExpenseAlreadyApprovedOrDeniedException("Expense already approved.");
        if(expense.getStatus().equals(Status.DENIED))
            throw new ExpenseAlreadyApprovedOrDeniedException("Expense already denied.");
        return null;
    }

    @Override
    public boolean deleteExpense(int id) {
        try {
            return expenseDAO.deleteExpense(id);
        } catch (ExpenseNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}

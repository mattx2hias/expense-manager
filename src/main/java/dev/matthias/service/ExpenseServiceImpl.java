package dev.matthias.service;

import dev.matthias.data.EmployeeDAO;
import dev.matthias.data.EmployeeDAOPostgres;
import dev.matthias.data.ExpenseDAO;
import dev.matthias.data.ExpenseDAOPostgres;
import dev.matthias.entities.Expense;
import dev.matthias.exceptions.EmployeeNotFoundException;
import dev.matthias.exceptions.ExpenseAlreadyApprovedOrDeniedException;
import dev.matthias.exceptions.ExpenseCannotBeNegativeException;
import dev.matthias.exceptions.ExpenseNotFoundException;
import dev.matthias.utilities.GenerateID;
import dev.matthias.utilities.Status;

public class ExpenseServiceImpl implements ExpenseService{

    EmployeeDAO employeeDAO = new EmployeeDAOPostgres();
    ExpenseDAO expenseDAO = new ExpenseDAOPostgres();

    @Override
    public Expense createExpense(Expense expense) throws EmployeeNotFoundException, ExpenseCannotBeNegativeException {
        if(expense.getCost() < 0)
            throw new ExpenseCannotBeNegativeException();
        if(expense.getId() == 0)
            expense.setId(GenerateID.generateRandomID());
        expense.setStatus(Status.PENDING);
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
    public Expense updateExpense(Expense expense) throws ExpenseAlreadyApprovedOrDeniedException, ExpenseNotFoundException, ExpenseCannotBeNegativeException {
        if(expense.getCost() < 0)
            throw new ExpenseCannotBeNegativeException();
        Expense expenseToUpdate = expenseDAO.readExpense(expense.getId());
        if(expenseToUpdate.getStatus().equals(Status.APPROVED))
            throw new ExpenseAlreadyApprovedOrDeniedException("Expense already approved.");
        if(expenseToUpdate.getStatus().equals(Status.DENIED))
            throw new ExpenseAlreadyApprovedOrDeniedException("Expense already denied.");
        return expenseDAO.updateExpense(expense);
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

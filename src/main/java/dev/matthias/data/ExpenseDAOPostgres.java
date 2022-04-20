package dev.matthias.data;

import dev.matthias.entities.Expense;
import dev.matthias.utilities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ExpenseDAOPostgres implements ExpenseDAO{

    @Override
    public Expense createExpense(Expense expense) {
        try {
            String query = "insert into expense values (?, ?, ?, ?, ?)";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, expense.getId());
            ps.setString(2, expense.getName());
            ps.setString(3, expense.getStatus().toString());
            ps.setDouble(4, expense.getCost());
            ps.setInt(5, expense.getIssuerId());
            if(ps.executeUpdate() == 1) {
                Logger.log("Created expense: " + expense.getId(), LogLevel.INFO);
                return expense;
            } else {
                Logger.log("Failed to create expense: " + expense.getId(), LogLevel.WARNING);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense readExpense(int id) {
        return null;
    }

    @Override
    public Expense updateExpense(Expense expense) throws ExpenseAlreadyApprovedOrDeniedException {
        try {
            if(expense.getStatus().equals(Status.APPROVED))
                throw new ExpenseAlreadyApprovedOrDeniedException("\nExpense already approved.");
                else if(expense.getStatus().equals(Status.DENIED))
                    throw new ExpenseAlreadyApprovedOrDeniedException("\nExpense already denied.");

            String query = "update expense set name = ?, status = ?, cost = ? where expense_id = ?";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, expense.getName());
            ps.setString(2, expense.getStatus().toString());
            ps.setDouble(3, expense.getCost());

            if(ps.executeUpdate() == 1) {
                Logger.log("Updated expense: " + expense.getId(), LogLevel.INFO);
                return expense;
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense deleteExpense(int id) {
        return null;
    }

    @Override
    public List<Expense> readAllExpenses() {
        return null;
    }
}

package dev.matthias.data;

import dev.matthias.entities.Expense;
import dev.matthias.utilities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public Expense readExpense(int id) throws ExpenseNotFoundException {
        try {
            String query = "select * from expense where expense_id = ?";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Status s;
                switch(rs.getString(3)) {
                    case "APPROVED": s = Status.APPROVED; break;
                    case "DENIED": s = Status.DENIED; break;
                    default: s = Status.PENDING; break;
                }
                return new Expense(rs.getInt(1), rs.getString(2), s, rs.getDouble(4), rs.getInt(5));
            } else throw new ExpenseNotFoundException();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense updateExpense(Expense expense) throws ExpenseAlreadyApprovedOrDeniedException {
        try {
            if(expense.getStatus().equals(Status.APPROVED))
                throw new ExpenseAlreadyApprovedOrDeniedException("\nExpense already approved.");
            if(expense.getStatus().equals(Status.DENIED))
                throw new ExpenseAlreadyApprovedOrDeniedException("\nExpense already denied.");

            String query = "update expense set name = ?, status = ?, cost = ? where expense_id = ?";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, expense.getName());
            ps.setString(2, expense.getStatus().toString());
            ps.setDouble(3, expense.getCost());
            ps.setInt(4, expense.getId());

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
    public boolean deleteExpense(int id) {
        try {
            String query = "delete from expense where expense_id = ?";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            if(ps.executeUpdate() == 1) {
                Logger.log("Deleted employee: " + id, LogLevel.INFO);
                return true;
            } else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Expense> readAllExpenses() {
        List<Expense> expenseList = new ArrayList<>();
        try {
            String query = "select * from expense";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            Expense e = new Expense();
            while(rs.next()) {
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                Status s;
                switch(rs.getString(3)) {
                    case "APPROVED": s = Status.APPROVED; break;
                    case "DENIED": s = Status.DENIED; break;
                    default: s = Status.PENDING; break;
                }
                e.setStatus(s);
                e.setCost(rs.getDouble(4));
                e.setIssuerId(rs.getInt(5));
                expenseList.add(e);
            }
            return expenseList;

        } catch (SQLException e) {
            e.printStackTrace();
            return expenseList;
        }
    }
}

package com.expensetracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ExpenseDAOImpl implements ExpenseDAO{

    @Override
    public void addExpense(Expense expense) {
        String sql = """
                insert into expenses(title, category, amount, expense_date) values(?, ?, ?, ?)""";
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, expense.getTitle());
            preparedStatement.setString(2, expense.getCategory());
            preparedStatement.setBigDecimal(3, expense.getAmount());
            preparedStatement.setDate(4, java.sql.Date.valueOf(expense.getExpenseDate()));
            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " successfully inserted");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Expense> getAllExpense() {
        return null;
    }

    @Override
    public Expense getExpenseById(int id) {
        return null;
    }

    @Override
    public void updateExpense(Expense expense) {

    }

    @Override
    public void deleteExpense(int id) {

    }
}

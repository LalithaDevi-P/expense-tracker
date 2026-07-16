package com.expensetracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            System.out.println(rows + " row successfully inserted");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Expense> getAllExpenses() {
        String sql = "select * from expenses";
        List<Expense> expenses = new ArrayList<>();
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Expense expense = new Expense();
                expense.setId(rs.getInt("id"));
                expense.setTitle(rs.getString("title"));
                expense.setCategory(rs.getString("category"));
                expense.setAmount(rs.getBigDecimal("amount"));
                expense.setExpenseDate(rs.getDate("expense_date").toLocalDate());
                expenses.add(expense);
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return expenses;
    }

    @Override
    public Expense getExpenseById(int id) {
        String sql = "select * from expenses where id = ?";
//        Expense expense = new Expense();
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                Expense expense = new Expense();
                expense.setId(rs.getInt("id"));
                expense.setTitle(rs.getString("title"));
                expense.setCategory(rs.getString("category"));
                expense.setAmount(rs.getBigDecimal("amount"));
                expense.setExpenseDate(rs.getDate("expense_date").toLocalDate());
                return expense;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void updateExpense(Expense expense) {
         String sql = """
                 update expenses set title = ?,
                 category = ?,
                 amount = ?
                 expense_date = ?""";
         try{
             Connection con = DBConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql);
             preparedStatement.setString(1, expense.getTitle());
             preparedStatement.setString(2, expense.getCategory());
             preparedStatement.setBigDecimal(3, expense.getAmount());
             preparedStatement.setDate(4, java.sql.Date.valueOf(expense.getExpenseDate()));
             int rows = preparedStatement.executeUpdate();
             System.out.println(rows + " row updated");
         }catch (Exception e){
             System.out.println(e);
         }
    }

    @Override
    public void deleteExpense(int id) {
        String sql = "delete from expenses where id = ?";
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " deleted successfully");
        }catch (Exception e){
            System.out.println(e);
        }

    }
}

package com.expensetracker;

import java.util.List;
public interface ExpenseDAO {
    void addExpense(Expense expense);
    List<Expense> getAllExpense();
    Expense getExpenseById(int id);
    void updateExpense(Expense expense);
    void deleteExpense(int id);


}

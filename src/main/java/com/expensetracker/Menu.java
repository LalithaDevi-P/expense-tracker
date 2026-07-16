package com.expensetracker;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);

    private final ExpenseDAO expenseDAO = new ExpenseDAOImpl();

    public void start() {

        while (true) {

            System.out.println("\n========== Expense Tracker ==========");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Exit");
            System.out.print("Enter Choice : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Category: ");
                    String category = scanner.nextLine();

                    System.out.print("Amount: ");
                    BigDecimal amt = scanner.nextBigDecimal();
                    scanner.nextLine();

                    System.out.print("Date (yy-mm-dd): ");
                    LocalDate expenseDate = LocalDate.parse(scanner.nextLine());

                    Expense expense = new Expense(title, category, amt, expenseDate);

                    expenseDAO.addExpense(expense);

                case 2:
                    List<Expense> expenses = expenseDAO.getAllExpenses();
                    if(expenses.isEmpty()){
                        System.out.println("Expenses not found");
                    }else{
                        System.out.println("==================");
                        for (Expense exp : expenses){
                            System.out.println(exp);
                        }
                    }

                    break;

                case 3:
                    System.out.println("Thank You!");
                    return;

                default:
                    System.out.println("Invalid Choice!");

            }

        }

    }

    }
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
            System.out.println("3. View Expense By Id");
            System.out.println("4. Update Expense");
            System.out.println("5. Delete Expense");
            System.out.println("6. Exit");
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
                    break;

                case 2:
                    List<Expense> expenses = expenseDAO.getAllExpenses();
                    if (expenses.isEmpty()) {
                        System.out.println("Expenses not found");
                    } else {
                        System.out.println("==================");
                        for (Expense exp : expenses) {
                            System.out.println(exp);
                        }
                    }

                    break;

                case 3:
                System.out.print("Enter id to search");
                int id = scanner.nextInt();
                scanner.nextLine();
                Expense expense1 = expenseDAO.getExpenseById(id);
                if(expense1 != null) {
                    System.out.println(expense1);
                }else{
                    System.out.println("Expense not found");
                }
                break;

                case 4:
                    System.out.print("Enter Expense ID : ");
                    int updateId=scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("New Title : ");
                    String newTitle=scanner.nextLine();

                    System.out.print("New Category : ");
                    String newCategory=scanner.nextLine();

                    System.out.print("New Amount : ");
                    BigDecimal newAmount=scanner.nextBigDecimal();
                    scanner.nextLine();

                    System.out.print("New Date (yyyy-mm-dd): ");
                    LocalDate newDate=LocalDate.parse(scanner.nextLine());

                    Expense updateExpense = new Expense(newTitle, newCategory, newAmount, newDate);
                    updateExpense.setId(updateId);

                    expenseDAO.updateExpense(updateExpense);
                    break;

                case 5:
                    System.out.println("Enter the id to delete");
                    int delId = scanner.nextInt();
                    scanner.nextLine();
                    expenseDAO.deleteExpense(delId);
                    break;

                case 6:
                    System.out.println("Thank You!");
                    return;

                default:
                    System.out.println("Invalid Choice!");

            }

        }

    }

    }
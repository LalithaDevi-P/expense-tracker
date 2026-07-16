package com.expensetracker;
import java.sql.*;

public class DBConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/expense-tracker";
    private static final String uname = "postgres";
    private static final String password = "Maname@2004";
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, uname, password);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}

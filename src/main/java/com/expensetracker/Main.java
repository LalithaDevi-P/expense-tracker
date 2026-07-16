package com.expensetracker;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
       Connection con = DBConnection.getConnection();
       if(con!=null){
           System.out.println("Connection established successfully");
       }else{
           System.out.println("Connection failed");
       }

      Menu menu = new Menu();
       menu.start();
    }
}
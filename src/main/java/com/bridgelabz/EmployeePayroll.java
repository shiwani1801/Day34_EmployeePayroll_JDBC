package com.bridgelabz;

import java.sql.*;
import java.util.Enumeration;
public class EmployeePayroll {
    static Connection con = null;
    public static void main(String[] args) throws EmployeeCustomException, SQLException {
        con = connected();
        reteriveData(con);
        updateData(con);
        reteriveDataByName(con);
    }
    public static Connection connected() throws EmployeeCustomException {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false"; // declare JdbcURL
        String UserName = "root";
        String Password = "root";
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // inbuilt method for Class.forName for loading driver
            System.out.println("Driver loaded");

        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // for tracing the exception

        }
        listDrivers(); // static method calling
        try {
            System.out.println("Connecting to Database...:" + jdbcURL); // for loading the drive for connect
            connection = DriverManager.getConnection(jdbcURL, UserName, Password);
            System.out.println("coneection successfull" + connection);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return connection;

    }
    public static void reteriveData(Connection connection) throws EmployeeCustomException, SQLException {
        PreparedStatement ps = connection.prepareStatement("Select * from Employee_Payroll");
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            System.out.print(result.getInt(1));
            System.out.print(" | ");
            System.out.print(result.getString(2));
            System.out.print(" | ");
            System.out.print(result.getString(3));
            System.out.print(" | ");
            System.out.println();
        }
    }
    public static void updateData(Connection connection) throws EmployeeCustomException, SQLException {
        PreparedStatement ps = con.prepareStatement("update employee_payroll set salary = ? where id = ?;");
        ps.setDouble(1, 3000000);
        ps.setInt(2, 1);

        ps.executeUpdate();
        System.out.println("Update Successfully");
    }
        public static void reteriveDataByName(Connection connection) throws SQLException {
            String salary = null;
            String query = "select * from  employee_payroll where name =?";
            PreparedStatement ps = con.prepareStatement(query);
           ps.setString(1, "Terisa");
            ResultSet result = ps.executeQuery();
            System.out.println("retrive data...........");
            while (result.next()) {
                String name = result.getString(2);
                salary = (result.getString(7));

                System.out.println(
                        " Name:- " + name + " salary:- " + salary);
            }
        }

    public static void listDrivers() {
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()) { // static method for iteration.
            Driver driverClass = (Driver) driverList.nextElement();
            System.out.println(" " + driverClass.getClass().getName());

        }

    }
}
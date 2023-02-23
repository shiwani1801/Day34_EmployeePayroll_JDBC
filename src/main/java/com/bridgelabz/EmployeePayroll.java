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
        particularDateRange(con);
        operationOnGroupFemale(con);
        operationOnGroupMale(con);
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
        System.out.println("retrive data...........");
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
    public static void particularDateRange(Connection connection) throws SQLException {
        String s = "select * from employee_payroll";
        PreparedStatement ps = con.prepareStatement(s);
        ResultSet result = ps.executeQuery();
        System.out.println("particular data range...........");
        while (result.next()) {
            int id = result.getInt(1);
            String name = result.getString(2);
            String gender = result.getString(6);
            String salary = result.getString(7);
            String date = result.getString(13);
            System.out.println(
                    "id:- " + id + " | "+" Name:- " + name + " | "+" Gender:- " + gender+" | "+" Salary:- " + salary + " | " + " Date:- " + date);
        }
    }
    public static void operationOnGroupFemale(Connection connection) throws SQLException {
        String salary = null;
        String query = "select sum(salary) as salary from employee_payroll where gender = 'Female' group by gender";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            salary = (resultSet.getString("salary"));
            System.out.println("Sum of salary by gender(Female) is: "+salary);

        }
        String salary1 = null;
        String query1 = "select  avg(salary) as salary1 from employee_payroll where gender = 'FeMale' group by gender";
        PreparedStatement preparedStatement1 = con.prepareStatement(query1);
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        while (resultSet1.next()) {
            salary1 = (resultSet1.getString("salary1"));
            System.out.println("avg of salary by gender(Female) is: "+salary1);

        }
        String salary2 = null;
        String query2 = "select  min(salary) as salary2 from employee_payroll where gender = 'FeMale' group by gender";
        PreparedStatement preparedStatement2 = con.prepareStatement(query2);
        ResultSet resultSet2 = preparedStatement2.executeQuery();
        while (resultSet2.next()) {
            salary2 = (resultSet2.getString("salary2"));
            System.out.println("min of salary by gender(Female) is: "+salary2);

        }
        String salary3 = null;
        String query3 = "select  max(salary) as salary3 from employee_payroll where gender = 'FeMale' group by gender";
        PreparedStatement preparedStatement3 = con.prepareStatement(query3);
        ResultSet resultSet3 = preparedStatement3.executeQuery();
        while (resultSet3.next()) {
            salary3 = (resultSet3.getString("salary3"));
            System.out.println("max of salary by gender(Female) is: "+salary3);

        }
        String salary4 = null;
        String query4 = "select  count(salary) as salary4 from employee_payroll where gender = 'FeMale' group by gender";
        PreparedStatement preparedStatement4 = con.prepareStatement(query4);
        ResultSet resultSet4 = preparedStatement4.executeQuery();
        while (resultSet4.next()) {
            salary4 = (resultSet4.getString("salary4"));
            System.out.println("count of salary by gender(Female) is: "+salary4);

        }

    }
    public static void operationOnGroupMale(Connection connection) throws SQLException {
        String salary = null;
        String query = "select sum(salary) as salary from employee_payroll where gender = 'male' group by gender";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            salary = (resultSet.getString("salary"));
            System.out.println("Sum of salary by gender(male) is: "+salary);

        }
        String salary1 = null;
        String query1 = "select  avg(salary) as salary1 from employee_payroll where gender = 'Male' group by gender";
        PreparedStatement preparedStatement1 = con.prepareStatement(query1);
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        while (resultSet1.next()) {
            salary1 = (resultSet1.getString("salary1"));
            System.out.println("avg of salary by gender(male) is: "+salary1);

        }
        String salary2 = null;
        String query2 = "select  min(salary) as salary2 from employee_payroll where gender = 'Male' group by gender";
        PreparedStatement preparedStatement2 = con.prepareStatement(query2);
        ResultSet resultSet2 = preparedStatement2.executeQuery();
        while (resultSet2.next()) {
            salary2 = (resultSet2.getString("salary2"));
            System.out.println("min of salary by gender(male) is: "+salary2);

        }
        String salary3 = null;
        String query3 = "select  max(salary) as salary3 from employee_payroll where gender = 'Male' group by gender";
        PreparedStatement preparedStatement3 = con.prepareStatement(query3);
        ResultSet resultSet3 = preparedStatement3.executeQuery();
        while (resultSet3.next()) {
            salary3 = (resultSet3.getString("salary3"));
            System.out.println("max of salary by gender(male) is: "+salary3);

        }
        String salary4 = null;
        String query4 = "select  count(salary) as salary4 from employee_payroll where gender = 'Male' group by gender";
        PreparedStatement preparedStatement4 = con.prepareStatement(query4);
        ResultSet resultSet4 = preparedStatement4.executeQuery();
        while (resultSet4.next()) {
            salary4 = (resultSet4.getString("salary4"));
            System.out.println("count of salary by gender(male) is: "+salary4);

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
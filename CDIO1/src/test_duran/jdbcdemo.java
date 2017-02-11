package test_duran;
import java.sql.*;

public class jdbcdemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			// 1: Get a connection to database
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?autoReconnect=true&useSSL=false", "root", "");
			// 2: create a statement
			Statement myStmt = myConn.createStatement();
			// 3: execute a sql query
			ResultSet myRs = myStmt.executeQuery("select * from employees");
			// 4: process the result set
			while (myRs.next()) {
				System.out.println(myRs.getInt("id") + ", " + myRs.getString("last_name") + ", " + myRs.getString("first_name"));
			}
		} catch (Exception e){
			e.printStackTrace();
		}

	}

}

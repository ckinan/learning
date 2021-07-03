package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcIntroduction {

	public static void main(String[] args) throws SQLException {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver"); // Register the jdbc driver
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/example_db", "root", "");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from employee");
			
			System.out.println("id,name,age,salary");
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				double salary = rs.getDouble("salary");
				
				System.out.println(id + "," + name + "," + age + "," + salary);
			}
			
			System.out.println("... Operation completed ...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null && !rs.isClosed()) {
				rs.close();
				System.out.println("ResultSet closed");
			}
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
				System.out.println("Statement closed");
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
				System.out.println("Connection closed");
			}
		}

	}

}

package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class JdbcStatements {

	private static Connection conn = null;

	public static void main(String[] args) throws SQLException {
		try {
			initialize();
			executeStatement(); // Executing SELECT
			executePreparedStatement(); // Executing INSERT
			executeCallableStatement(); // Execute Stored Procedure
			transactionWithRollback();
			batchProcessStatement();
			batchProcessPreparedStatement();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	private static void batchProcessPreparedStatement() throws SQLException {
		PreparedStatement pstmt = null;

		conn.setAutoCommit(false);

		try {
			pstmt = conn.prepareStatement("insert into employee (id, name, age, salary) values (null, ?, ?, ?)");

			for (int i = 0; i < 10; i++) {
				pstmt.setString(1, "Batch Prepared Statement " + i);
				pstmt.setInt(2, 20);
				pstmt.setDouble(3, 2000);
				pstmt.addBatch();
			}
			
			int[] numberRows = pstmt.executeBatch();
			System.out.println("Rows affected: " + Arrays.toString(numberRows));
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			closeStatement(pstmt);
		}

		conn.setAutoCommit(false);
	}

	private static void batchProcessStatement() throws SQLException {
		Statement stmt = null;

		conn.setAutoCommit(false);
		try {
			stmt = conn.createStatement();

			for (int i = 0; i < 10; i++) {
				stmt.addBatch(
						"insert into employee (id, name, age, salary) values (null, 'Batch Statement " + i + "', 18, 1500.0)");
			}

			int[] numberRows = stmt.executeBatch();
			System.out.println("Rows affected: " + Arrays.toString(numberRows));
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			closeStatement(stmt);
		}
		conn.setAutoCommit(true);
	}

	private static void transactionWithRollback() throws SQLException {
		String sql = "insert into employee (id, name, age, salary) values (null, 'Rollback Test', 18, 1500.0)";
		String sqlWithError = "xxinsert into employee (id, name, age, salary) values (null, 'Rollback Test', 18, 1500.0)";
		Statement stmt = null;

		conn.setAutoCommit(false);
		try {
			System.out.println("Executing the sql: " + sql);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Statement 1 executed!");
			stmt.executeUpdate(sqlWithError);
			System.out.println("Statement 2 executed!");
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println("Rollback executed");
		} finally {
			closeStatement(stmt);
		}
		conn.setAutoCommit(true);
	}

	private static void closeConnection() throws SQLException {
		if (conn != null && !conn.isClosed()) {
			conn.close();
			System.out.println("Connection closed");
		}
	}

	private static void closeStatement(Statement stmt) throws SQLException {
		if (stmt != null && !stmt.isClosed()) {
			stmt.close();
			System.out.println("Statement closed");
		}
	}

	private static void closeResultSet(ResultSet rs) throws SQLException {
		if (rs != null && !rs.isClosed()) {
			rs.close();
			System.out.println("Statement closed");
		}
	}

	private static void initialize() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver"); // Register the jdbc driver
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/example_db", "root", "");
	}

	private static void executeCallableStatement() throws SQLException {
		String sql = "{call sp_get_employees(?)}";
		CallableStatement cstmt = null;
		ResultSet rs = null;

		try {
			System.out.println("Executing the sql: " + sql);
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, "Juan");
			rs = cstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				double salary = rs.getDouble("salary");

				System.out.println(id + "," + name + "," + age + "," + salary);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResultSet(rs);
			closeStatement(cstmt);
		}
	}

	private static void executePreparedStatement() throws SQLException {
		String sql = "insert into employee (id, name, age, salary) values (null, ?, ?, ?)";
		PreparedStatement pstmt = null;

		try {
			System.out.println("Executing the sql: " + sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "autoPreparedStatement");
			pstmt.setInt(2, 20);
			pstmt.setDouble(3, 2000);
			int numberRows = pstmt.executeUpdate();
			System.out.println("PreparedStatement executed! Number of rows affected: " + numberRows);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStatement(pstmt);
		}
	}

	private static void executeStatement() throws SQLException {
		String sql = "select * from employee";
		String sql2 = "insert into employee (id, name, age, salary) values (null, 'auto', 18, 1500.0)";
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			System.out.println("stmt.executeQuery(sql): " + sql);
			System.out.println("id,name,age,salary");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				double salary = rs.getDouble("salary");

				System.out.println(id + "," + name + "," + age + "," + salary);
			}

			boolean b = stmt.execute(sql);
			System.out.println("stmt.execute(sql): " + sql);
			System.out.println("result (b): " + b);

			int numberRows = stmt.executeUpdate(sql2);
			System.out.println("stmt.executeUpdate(sql2): " + sql2);
			System.out.println("result (numberRows): " + numberRows);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

}

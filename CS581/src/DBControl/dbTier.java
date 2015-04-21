package DBControl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class dbTier {

	protected Connection conn;

	protected String connString;

	public dbTier() {
		conn = this.connect();
	}

	public boolean Close() {
		boolean result = false;
		try {
			conn.close();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	//
	// ExecuteActionQuery:
	// executes an Insert, Update or Delete query, and returns
	// the number of records modified.
	//
	public int ExecuteActionQuery(String sql) {
		int result = 0;
		try {
			if (conn.isClosed()) {
				this.connect();
			}
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	//
	// ExecuteNonScalarQuery:
	// executes a Select query that generates a temporary table,
	// returning this table in the form of a ResultSet.
	//
	public ResultSet ExecuteNonScalarQuery(String sql) {
		ResultSet rs = null;
		try {
			if (conn.isClosed()) {
				this.connect();
			}
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	//
	// GetMaxRowNumber:
	// executes a select query and return the record count.
	//
	public int GetMaxRowNumber(String sql) {
		ResultSet rs = null;
		int count = 0;
		try {
			if (conn.isClosed()) {
				this.connect();
			}
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.last();
			count = rs.getRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	// Transaction commit to ensure that all the update be successful at one
	// time
	public boolean TransCommit(ArrayList<String> sqlArrayList) {
		boolean result = false;

		try {
			if (conn.isClosed()) {
				this.connect();
			}
			// Set auto commit as false.
			conn.setAutoCommit(false);

			Statement stmt = conn.createStatement();

			for (String sql : sqlArrayList) {
				if (!sql.isEmpty()) {
					stmt.executeUpdate(sql);
				}
			}
			// If there is no error.
			conn.commit();
			result = true;
		} catch (SQLException se) {
			// If there is any error.
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	// DB Connection
	private Connection connect() {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "581_project";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "password";
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager
					.getConnection(url + dbName, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;

	}

}

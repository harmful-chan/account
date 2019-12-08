package org.account.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtil {
	public static final String sqlDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String sqlString = "jdbc:sqlserver://localhost:1433; DatabaseName=account";
	public static final String sqlUser = "sa";
	public static final String sqlPass = "123456";
	
	private static Connection conn;
	private static Statement stmt;
	private static int count = 0;
	private static List<Connection> connections = new ArrayList<Connection>();
	private static List<Statement> statements = new ArrayList<Statement>();
	public static void init() {
		try {
			Class.forName(sqlDriver);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public static  Statement getStatement() {
		try {	
			if(conn == null) {
				conn = (Connection) DriverManager.getConnection(sqlString, sqlUser, sqlPass);
			}
			Statement s = conn.createStatement();
			statements.add(s);
			count++;
	        return s;
		}catch(Exception e) {
			return null;
		}
	}
	
	public static void close(){
		try {
			while(count-- > 0) {
				statements.get(count).close();
				statements.remove(count);
			}			
			if(conn != null) {
				conn.close();
				conn = null;
			}
			
		}catch(Exception e) {

		}
	}
}

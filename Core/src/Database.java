package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	public static void main(String[] args) {
		Database mySQL = new Database();
		Connection conn = mySQL.connection();
	}
	
	
	public static Connection connection() {
		try {
				
				String url = "jdbc:mysql://mysql.stud.ntnu.no/";
				String dbName = "alekh_prosjekt1";
				String driver = "com.mysql.jdbc.Driver";
				String userName = "alekh_IT1901";
				String password = "abcd1234";
				
				Class.forName(driver).newInstance();
				Connection conn = DriverManager.getConnection(url+dbName,userName,password);
				
				conn.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
		}
	}

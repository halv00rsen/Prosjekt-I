package src;

import java.sql.*;

public class Database {
	
	Connection conn;
	
	public Database() {
		this.conn = connection();
	}
	
	private Connection connection() {
		try {		
			String url = "jdbc:mysql://mysql.stud.ntnu.no/";
			String dbName = "alekh_prosjekt1";
			String driver = "com.mysql.jdbc.Driver";
			String userName = "alekh_IT1901";
			String password = "abcd1234";
				
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url+dbName,userName,password);
			
			//conn.close();
			return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void insert(String query) {
		try {
			Statement st = conn.createStatement();
			int res = st.executeUpdate(query);
			if (res == 1) {
				System.out.println("insert vellykket!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
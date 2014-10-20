package src;

import java.sql.*;

public class Database {
	public static void main(String[] args) {
		Database mySQL = new Database();
	}
	
	Connection conn;
	
	public Database() {
		this.conn = connection();
	}
	
	public Connection connection() {
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
	
	public void insert(String column, String value) {
		try {
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM event");
			while (res.next()) {
				//String 
				
			//IKKE FERDIG...
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
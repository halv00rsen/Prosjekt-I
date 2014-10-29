package src;

import java.io.FileReader;
import java.sql.*;
import java.util.Scanner;


public class Database {
	
	//konstruktør som lager koie tabell og reservasjonstabell i databasen
	//og fyller koietabellen med data fra initialiseringAvKoier.txt fila
	public Database(String datapath) {
		try {
			//lager koie tabellen.
			makeQuery("CREATE TABLE Koier" +
					  "(id VARCHAR(255), " +
					  "navn VARCHAR(255), " +
					  "numBeds VARCHAR(255), " +
					  "numSeats VARCHAR(255), " +
					  "year VARCHAR(255), " +
					  "coordinate VARCHAR(255), " +
					  "PRIMARY KEY ( id ))");
			
			//lager reservasjonskalenderen
			// mangler kode...
			
			//evt andre tabeller
			//...
			
			Scanner in = new Scanner(new FileReader(datapath));
			in.nextLine(); //hopper over første linje som beskriver data
			//lager en insert query from hver linje i initialisertinAvKoier.txt
			while (in.hasNextLine()) {
				String[] rader = in.nextLine().split(" ");	
				String query = "INSERT INTO Koier VALUES (";
				for (int i=0;i<rader.length-1; i++) {
					query += "'"+rader[i]+"', ";
				}
				query += "'"+rader[rader.length-1]+"')";
				//System.out.println(query);
				makeQuery(query);
			}
			in.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("initialisering vellykket!");
		}
	}

	//metode som åpner en connection mot databasen
	private Connection connection() {
		try {		
			String url = "jdbc:mysql://mysql.stud.ntnu.no/";
			String dbName = "alekh_prosjekt1";
			String driver = "com.mysql.jdbc.Driver";
			String userName = "alekh_IT1901";
			String password = "abcd1234";
				
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url+dbName,userName,password);
			return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//metode for å utføre query mot databasen
	public void makeQuery(String query) {
		try {
			Connection conn = connection();
			Statement st = conn.createStatement();
			int res = st.executeUpdate(query);
			if (res == 1) {
				System.out.println("query utført");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//metode for å oppdatere databasen med info fra koie objektet sendt som aargument til metoden
	public void toDatabase(Koie koie) {
		String query = "";
		
	}
}
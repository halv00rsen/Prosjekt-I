package src;

import java.io.FileReader;

//import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

import java.util.List;
import java.util.Scanner;

public class Database {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://mysql.stud.ntnu.no/";
	private static String dbName = "alekh_prosjekt1";
	private static String userName = "alekh_IT1901";
	private static String password = "abcd1234";
	
	// Metode som lager koie tabell og reservasjonstabell i databasen
	//og fyller koietabellen med data fra initialiseringAvKoier.txt fila
	public void initializeDatabase(String datapath) {
		try {
			// Oppretter tabellene koie, bruker, item, vedrapport og reservasjon
			makeQuery("CREATE TABLE koie" +
					  "(id SMALLINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
					  "name VARCHAR(255) NOT NULL, " +
					  "vedkapasitet FLOAT NOT NULL, " +
					  "num_beds SMALLINT NOT NULL, " +
					  "num_seats SMALLINT, " +
					  "year SMALLINT, " +
					  "coordinates VARCHAR(255))");
			
			makeQuery("CREATE TABLE bruker" +
					  "(id VARCHAR(255) NOT NULL PRIMARY KEY, " +
					  "password_hash VARCHAR(255) NOT NULL, " +
					  "bruker_status VARCHAR(255) NOT NULL)");
			
			makeQuery("CREATE TABLE item" +
					  "(id SMALLINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
					  "koie_id SMALLINT NOT NULL REFERENCES koie(id), " +
					  "item_navn VARCHAR(255) NOT NULL, " +
					  "status VARCHAR(255) NOT NULL, " +
					  "bruker_id VARCHAR(255) NOT NULL REFERENCES bruker(id))");
			
			makeQuery("CREATE TABLE vedrapport" +
					  "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
					  "koie_id SMALLINT NOT NULL REFERENCES koie(id), " +
					  "vedmengde FLOAT NOT NULL, " +
					  "dato DATE NOT NULL, " +
					  "bruker_id VARCHAR(255) NOT NULL REFERENCES bruker(id))");
			
			makeQuery("CREATE TABLE reservasjon" +
					  "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
					  "koie_id SMALLINT NOT NULL REFERENCES koie(id), " +
					  "dato DATE NOT NULL, " +
					  "bruker_id VARCHAR(255) NOT NULL REFERENCES bruker(id)))");
			
			
			// Fyller inn koie-tabellen fra fil
			Scanner in = new Scanner(new FileReader(datapath));
			in.nextLine(); //hopper over f�rste linje som beskriver data
			//lager en insert query from hver linje i initialisertinAvKoier.txt
			while (in.hasNextLine()) {
				String[] rader = in.nextLine().split(" ");	
				String query = "INSERT INTO koie VALUES (";
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

	//metode som �pner en connection mot databasen
	private Connection connection() {
		try {		
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url+dbName,userName,password);
			return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//metode for � utf�re query mot databasen
	public void makeQuery(String query) {
		try {
			Connection conn = connection();
			Statement st = conn.createStatement();
			int res = st.executeUpdate(query);
			if (res == 1) {
				System.out.println("query utf�rt");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//metode for � oppdatere databasen med info fra koie objektet sendt som argument til metoden
	public void toDatabase(Koie koie) {
		
		//oppdaterer datoene koien er reservert for.
		Calendar calendar = koie.getCalendar();
		List<BookingDate> datesBooked = calendar.getDatesBooked();
		
		//m� kanskje gj�re et query for � slette allerede reserverte datoer f�rst
		//har ikke helt tenkt gjennom dette enda... vet ikke hvordan det blir seende ut i databasen.
		
		for (BookingDate date : datesBooked) {
			String bookedFrom = "" + date.dateFrom.day + "." + date.dateFrom.month + "." + date.dateFrom.year;
			String bookedTo = "" + date.dateTo.day + "." + date.dateTo.month + "." + date.dateTo.year;
			
			String query = "INSERT INTO ReservasjonsKalender VALUES (" +
						   "'" + koie.getId() + "', " + 
						   "'" + date.person + "', " +
						   "'" + bookedFrom + "', " +
						   "'" + bookedTo + "')";
			makeQuery(query);
			
			
		//m� ogs� oppdatere inventory osv...
		//mangler kode
						   
		}
	}
}
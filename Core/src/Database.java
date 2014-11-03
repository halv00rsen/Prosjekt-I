package src;

import java.io.FileReader;
import java.sql.*;
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
			//lager koie tabellen.
			makeQuery("CREATE TABLE Koier" +
					  "(koieID VARCHAR(255), " +
					  "name VARCHAR(255), " +
					  "numBeds VARCHAR(255), " +
					  "numSeats VARCHAR(255), " +
					  "year VARCHAR(255), " +
					  "coordinate VARCHAR(255), " +
					  "PRIMARY KEY ( koieID ))");
			
			//lager reservasjons
			makeQuery("CREATE TABLE ReservasjonsKalender" +
					  "(koieID VARCHAR(255), " +
					  "person VARCHAR(255), " +
					  "dateFrom VARCHAR(255), " + 
					  "dateTo VARCHAR(255), " +
					  "PRIMARY KEY ( koieID ))");
			
			//evt andre tabeller
			//...
			
			Scanner in = new Scanner(new FileReader(datapath));
			in.nextLine(); //hopper over f�rste linje som beskriver data
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
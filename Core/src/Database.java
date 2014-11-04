package src;

import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

//import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database {
	private String url = "jdbc:mysql://mysql.stud.ntnu.no/";
	private String dbName = "alekh_prosjekt1";
	private String driver = "com.mysql.jdbc.Driver";
	private String userName = "alekh_IT1901";
	private String password = "abcd1234";
	
	public Koie getKoie(int koie_id) {
		try {
			int id = 0;
			String name = "";
			float vedkapasitet = 0;
			float vedmengde = 0;
			int numBeds = 0;
			String koie_query = "SELECT id, name, vedkapasitet, num_beds " + 
								"FROM koie " + 
					            "WHERE id =" + String.valueOf(koie_id);
			ResultSet koie_res = makeQuery(koie_query);
			if (koie_res.next()) {
				id = koie_res.getInt("id");
				name = koie_res.getString("name");
				vedkapasitet = koie_res.getFloat("vedkapasitet");
				numBeds = koie_res.getInt("num_beds");
			}
	
			String ved_query = "SELECT id, mengde, dato, koie_id " +
			                   "FROM ved " +
					           "WHERE koie_id =" + String.valueOf(koie_id) + " " +
					           "ORDER BY dato DESC";
			ResultSet ved_res = makeQuery(ved_query);
			if (ved_res.next()) {
				vedmengde = ved_res.getFloat("mengde");
			}
	
			String item_query = "SELECT id, name, status " +
			                   "FROM item " +
					           "WHERE koie_id =" + String.valueOf(koie_id);
			ResultSet item_res = makeQuery(item_query);
			Inventory inventory = new Inventory();
			while (item_res.next()) {
				int itemId = item_res.getInt("id");
				String itemName = item_res.getString("name");
				String itemStatusString = item_res.getString("status");
				Item.Status itemStatus = Item.getItemStatus(itemStatusString);
				Item item = new Item(itemId, itemName, itemStatus);
				inventory.addItem(item);
			}
			if (!(id == 0 || name.equals("") || vedkapasitet == 0 || vedmengde == 0 || numBeds == 0 || inventory == null)) {
				Koie koie = new Koie(id, name, vedkapasitet, vedmengde, numBeds, inventory);
				return koie;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Metode som lager koie tabell og reservasjonstabell i databasen
	//og fyller koietabellen med data fra initialiseringAvKoier.txt fila
	public void initializeDatabase(String datapath) {
		try {
			// Oppretter tabellene koie, bruker, item, vedrapport og reservasjon
			
			makeStatement("CREATE TABLE koie" +
					  "(id SMALLINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
					  "name VARCHAR(255) NOT NULL, " +
					  "vedkapasitet FLOAT NOT NULL, " +
					  "num_beds SMALLINT NOT NULL, " +
					  "num_seats SMALLINT, " +
					  "year SMALLINT, " +
					  "coordinates VARCHAR(255))");

			makeStatement("CREATE TABLE bruker" +
					  "(id VARCHAR(255) NOT NULL PRIMARY KEY, " +
					  "password_hash VARCHAR(255) NOT NULL, " +
					  "bruker_status VARCHAR(255) NOT NULL)");
			
			makeStatement("CREATE TABLE item" +
					  "(id SMALLINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
					  "name VARCHAR(255) NOT NULL, " +
					  "status VARCHAR(255) NOT NULL, " +
					  "koie_id SMALLINT NOT NULL REFERENCES koie(id), " +
					  "bruker_id VARCHAR(255) NOT NULL REFERENCES bruker(id))");
			
			makeStatement("CREATE TABLE vedrapport" +
					  "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
					  "mengde FLOAT NOT NULL, " +
					  "dato DATE NOT NULL, " +
					  "koie_id SMALLINT NOT NULL REFERENCES koie(id), " +
					  "bruker_id VARCHAR(255) NOT NULL REFERENCES bruker(id))");
			
			makeStatement("CREATE TABLE reservasjon" +
					  "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
					  "dato DATE NOT NULL, " +
					  "koie_id SMALLINT NOT NULL REFERENCES koie(id), " +
					  "bruker_id VARCHAR(255) NOT NULL REFERENCES bruker(id)))");
			
			// Fyller inn koie-tabellen fra fil
			Scanner in = new Scanner(new FileReader(datapath));
			in.nextLine(); //hopper over f�rste linje som beskriver data
			//lager en insert query from hver linje i initialisertinAvKoier.txt
			while (in.hasNextLine()) {
				String[] rader = in.nextLine().split(" ");	
				String statement = "INSERT INTO koie VALUES (";
				for (int i=0;i<rader.length-1; i++) {
					statement += "'"+rader[i]+"', ";
				}
				statement += "'"+rader[rader.length-1]+"')";
				//System.out.println(statement);
				makeStatement(statement);
			}
			in.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("initialisering vellykket!");
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
			
			String statement = "INSERT INTO ReservasjonsKalender VALUES (" +
						   "'" + koie.getId() + "', " + 
						   "'" + date.person + "', " +
						   "'" + bookedFrom + "', " +
						   "'" + bookedTo + "')";
			makeStatement(statement);
			
			
		//m� ogs� oppdatere inventory osv...
		//mangler kode
						   
		}
	}

	private ResultSet makeQuery(String query) {
		ResultSet res = null;
		try {
			Connection conn = getConnection();
			Statement st = conn.createStatement();
			res = st.executeQuery(query);
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	//metode for � utf�re statement mot databasen
	private void makeStatement(String statement) {
		try {
			Connection conn = getConnection();
			Statement st = conn.createStatement();
			int res = st.executeUpdate(statement);
			if (res == 1) {
				System.out.println("statement utf�rt");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//metode som �pner en connection mot databasen
	private Connection getConnection() {
		try {		
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url+dbName,userName,password);
			return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
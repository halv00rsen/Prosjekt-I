package src;

import java.io.FileReader;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;


//import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database {
	private static String url = "jdbc:mysql://mysql.stud.ntnu.no/";
	private static String dbName = "alekh_prosjekt1";
	private static String driver = "com.mysql.jdbc.Driver";
	private static String userName = "alekh_IT1901";
	private static String password = "abcd1234";
	
	

	// Metode som lager koie tabell og reservasjonstabell i databasen
	//og fyller koietabellen med data fra initialiseringAvKoier.txt fila
	public static void initializeDatabase(String datapath) {
		try {
			// Oppretter tabellene koie, bruker, item, vedrapport og reservasjon
			
			//sletter alle tidligere tabeller
			makeStatement("DROP TABLE koie");
			makeStatement("DROP TABLE bruker");
			makeStatement("DROP TABLE item");
			makeStatement("DROP TABLE vedrapport");
			makeStatement("DROP TABLE reservasjon");
			
			makeStatement("CREATE TABLE koie"
						+ "(id SMALLINT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
						+ "name VARCHAR(255) NOT NULL, "
						+ "num_beds SMALLINT NOT NULL, "
						+ "num_seats SMALLINT, "
						+ "year SMALLINT, "
						+ "coordinates VARCHAR(255), "
						+ "terreng VARCHAR(255), "
						+ "sykkel VARCHAR(255), " 
						+ "topptur VARCHAR(255), "
						+ "jaktOgFiske VARCHAR(255), "
						+ "spesialiteter VARCHAR(255))");

			makeStatement("CREATE TABLE bruker"
						+ "(id VARCHAR(255) NOT NULL PRIMARY KEY, "
						+ "password_hash VARCHAR(255) NOT NULL, "
						+ "person VARCHAR(255) NOT NULL)");
			
			makeStatement("CREATE TABLE item"
						+ "(id SMALLINT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
						+ "item VARCHAR(255) NOT NULL, "
						+ "status VARCHAR(255) NOT NULL, "
						+ "koie_id SMALLINT NOT NULL REFERENCES koie(id), "
						+ "bruker_id VARCHAR(255) NOT NULL REFERENCES bruker(id))");
			
			makeStatement("CREATE TABLE vedrapport"
						+ "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
						+ "mengde FLOAT NOT NULL, "
						+ "dato VARCHAR(255) NOT NULL, "
						+ "koie_id SMALLINT NOT NULL REFERENCES koie(id), "
						+ "bruker_id VARCHAR(255) NOT NULL REFERENCES bruker(id))");
			
			makeStatement("CREATE TABLE reservasjon"
						+ "(koie_id SMALLINT NOT NULL PRIMARY KEY, "
						+ "fromDate VARCHAR(255) NOT NULL, "
						+ "toDate VARCHAR(255) NOT NULL, "
						+ "bruker_id VARCHAR(255) NOT NULL REFERENCES bruker(id))");
			
			// Fyller inn koie-tabellen fra fil
			Scanner in = new Scanner(new FileReader(datapath));
			in.nextLine(); //hopper over fï¿½rste linje som beskriver data
			//lager en insert query from hver linje i initialisertinAvKoier.txt
			while (in.hasNextLine()) {
				String[] rader = in.nextLine().split("#");	
				String statement = "INSERT INTO koie VALUES (";
				for (int i=0;i<rader.length-1; i++) {
					statement += "'"+rader[i]+"', ";
				}
				statement += "'"+rader[rader.length-1]+"')";
				System.out.println(statement);
				makeStatement(statement);
			}
			in.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("initialisering vellykket!");
		}
	}

	//metode for ï¿½ oppdatere databasen med info fra koie objektet sendt som argument til metoden
	public static void toDatabase(Koie koie) {
		
		//oppdaterer datoene koien er reservert for.
		Calendar calendar = koie.getCalendar();
		List<BookingDate> datesBooked = calendar.getDatesBooked();
		
		//mï¿½ kanskje gjï¿½re et query for ï¿½ slette allerede reserverte datoer fï¿½rst
		//har ikke helt tenkt gjennom dette enda... vet ikke hvordan det blir seende ut i databasen.
		
		for (BookingDate date : datesBooked) {
			String bookedFrom = "" + date.dateFrom.year + "-" + date.dateFrom.month + "-" + date.dateFrom.day; 
			String bookedTo = "" + date.dateTo.year + "-" + date.dateTo.month + "-" + date.dateTo.day;
			
			String statement = "INSERT INTO reservasjon VALUES (" +
							   "'" + koie.getId() + "', " + 
							   "'" + bookedFrom + "', " +
							   "'" + bookedTo + "', " +
							   "'" + date.person + "')";
			
			makeStatement(statement);
			
			
		//mï¿½ ogsï¿½ oppdatere inventory osv...
		//mangler kode
						   
		}
	}

	private static ResultSet makeQuery(String query) {
		ResultSet res = null;
		try {
			Connection conn = getConnection();
			Statement st = conn.createStatement();
			res = st.executeQuery(query);
			//conn.close(); må kommenteres ut for at getIdNameMap skal fungere...
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	//metode for ï¿½ utfï¿½re statement mot databasen
	private static void makeStatement(String statement) {
		try {
			Connection conn = getConnection();
			Statement st = conn.createStatement();
			int res = st.executeUpdate(statement);
			if (res == 1) {
				System.out.println("statement utfï¿½rt");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//metode som ï¿½pner en connection mot databasen
	private static Connection getConnection() {
		try {		
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url+dbName,userName,password);
			return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static HashMap<Integer,String> getIdNameMap() {
		try {
			ResultSet res = makeQuery("SELECT id, name FROM koie");
			HashMap<Integer, String> idNameMap = new HashMap<Integer, String>();
			while (res.next()){
				idNameMap.put(res.getInt("id"), res.getString("name"));
			}
			return idNameMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * Returnerer et Koie-objekt fra databasen med en Koie-id.
	 * @param koie_id
	 * @return Et Koie-objekt
	 */
	public static Koie getKoie(int koie_id) {
		try {
			String name = "";
			String coordinate = "";
			int year = 0;
			int numBeds = 0;
			int numSeats = 0;
			String terreng = "";
			String sykkel = "";
			String topptur = "";
			String jaktOgFiske = "";
			String spesialiteter = "";
			

			String koie_query = "SELECT name, num_beds, num_seats, year, coordinates, terreng, sykkel, "
							  + "topptur, jaktOgfiske, spesialiteter "
			                  +	"FROM koie "
			                  + "WHERE id =" + koie_id;
			ResultSet koie_res = makeQuery(koie_query);
			if (koie_res.next()) {
				name = koie_res.getString("name");
				coordinate = koie_res.getString("coordinates");
				year = koie_res.getInt("year");
				numBeds = koie_res.getInt("num_beds");
				numSeats = koie_res.getInt("num_seats");
				terreng = koie_res.getString("terreng");
				sykkel = koie_res.getString("sykkel");
				topptur = koie_res.getString("topptur");
				jaktOgFiske = koie_res.getString("jaktOgFiske");
				spesialiteter = koie_res.getString("spesialiteter");
			}
	
	
			String item_query = "SELECT id, item, status "
							  + "FROM item "
							  + "WHERE koie_id =" + String.valueOf(koie_id);
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
			
			//lager koie objekt:
			Koie koie = new Koie(koie_id, name, coordinate, year);
			koie.setNumBeds(numBeds);
			koie.setNumSeats(numSeats);
			koie.setTerreng(terreng);
			koie.setSykkel(sykkel);
			koie.setTopptur(topptur);
			koie.setJaktOgFiske(jaktOgFiske);
			koie.setSpesialiteter(spesialiteter);
			
			//fyller koieobjektet med reservasjonene:
			ResultSet reservasjoner = makeQuery("SELECT * FROM reservasjon WHERE koie_id =" + koie_id);
			Calendar cabinRented = koie.getCalendar();
			while (reservasjoner.next()) {
				String person = reservasjoner.getString("bruker_id");
				
				String fromDate = reservasjoner.getString("fromDate");
				String toDate = reservasjoner.getString("toDate");			
				String[] fromParts = fromDate.split("-");
				String[] toParts = toDate.split("-");
				
				Date from = new Date(Integer.valueOf(fromParts[2]), Integer.valueOf(fromParts[1]));
				Date to = new Date(Integer.valueOf(toParts[2]), Integer.valueOf(toParts[1]));
				
				cabinRented.reservePeriod(from, to, person);
			}
			
			return koie;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}














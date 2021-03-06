package src;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/** Håndterer kommunikasjon mellom programmet og MySQL-databasen */
public class Database {
	public static boolean DEBUG = false;
	private static String url = "jdbc:mysql://mysql.stud.ntnu.no/";
	private static String dbName = "alekh_prosjekt1";
	private static String driver = "com.mysql.jdbc.Driver";
	private static String userName = "alekh_IT1901";
	private static String password = "abcd1234";
	
	private static String initKoie = "Core/src/initialiseringAvKoier.txt";
	private static String initItem = "Core/src/dbinit_item.txt";
	private static String initVed = "Core/src/initialiseringAvVedstatus.txt";
	private static String initBruker = "Core/src/dbinit_bruker.txt";

	/**
	 *  Metode som initialiserer/resetter databasen med informasjon fra initialiseringsfiler
	 *  @return boolean - returnerer om databasen ble tilbakestilt
	 */
	public static boolean initializeDatabase() {
		try {
			// Sletter alle tidligere tabeller
			makeStatement("DROP TABLE koie");
			makeStatement("DROP TABLE bruker");
			makeStatement("DROP TABLE item");
			makeStatement("DROP TABLE vedstatus");
			makeStatement("DROP TABLE reservasjon");
			makeStatement("DROP TABLE rapport");
			
			// Oppretter tabellene koie, bruker, item, vedstatus, reservasjon og rapport
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
						+ "jakt_og_fiske VARCHAR(255), "
						+ "spesialiteter VARCHAR(255))");

			makeStatement("CREATE TABLE bruker"
						+ "(id VARCHAR(255) NOT NULL PRIMARY KEY, "
						+ "password_hash VARCHAR(255) NOT NULL, "
						+ "is_admin BOOL NOT NULL)");

			makeStatement("CREATE TABLE item"
						+ "(id SMALLINT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
						+ "name VARCHAR(255) NOT NULL, "
						+ "status VARCHAR(255) NOT NULL, "
						+ "koie_id SMALLINT NOT NULL)");
			
			makeStatement("CREATE TABLE vedstatus"
						+ "(koie_id INT NOT NULL PRIMARY KEY, "
						+ "mengde DOUBLE NOT NULL)");
			
			makeStatement("CREATE TABLE reservasjon"
						+ "(id int NOT NULL AUTO_INCREMENT PRIMARY KEY, "
						+ "start_date VARCHAR(255) NOT NULL, "
						+ "end_date VARCHAR(255) NOT NULL, "
						+ "koie_id SMALLINT NOT NULL, "
						+ "bruker_id VARCHAR(255) NOT NULL)");
			
			makeStatement("CREATE TABLE rapport"
						+ "(reservasjon_id int NOT NULL PRIMARY KEY, "
						+ "koie_id SMALLINT NOT NULL, "
						+ "bruker_id VARCHAR(255), "
						+ "kommentar VARCHAR(255))");
			
			
			// Fyller koie-tabellen med data fra fil
			Scanner in = new Scanner(new FileInputStream(initKoie), "UTF-8");
			in.nextLine(); // Hopper over første linje som beskriver data
			// Lager en INSERT-statement from hver linje i initialiseringsfila
			while (in.hasNextLine()) {
				String[] kolonner = in.nextLine().split("#");	
				String statement = "INSERT INTO koie VALUES (";
				for (int i=0;i<kolonner.length-1; i++) {
					statement += "'"+kolonner[i]+"', ";
				}
				statement += "'"+kolonner[kolonner.length-1]+"')";
				makeStatement(statement);
			}
			in.close();
			
			// Fyller item-tabellen med data fra fil
			in = new Scanner(new FileInputStream(initItem), "UTF-8");
			in.nextLine();
			while (in.hasNextLine()) {
				String[] fields = in.nextLine().split(", ");	
				String koieId = fields[0];
				String itemNavn = fields[1];

				String statement = "INSERT INTO item (name, status, koie_id) " 
								 + "VALUES ('"+itemNavn+"', 'IN_ORDER', '"+koieId+"')";

				makeStatement(statement);
			}
			in.close();
			
			//Fyller vedstatus-tabellen med data fra fil
			in = new Scanner(new FileInputStream(initVed), "UTF-8");
			in.nextLine();
			while (in.hasNextLine()) {
				String[] felt = in.nextLine().split(" ");
				makeStatement("INSERT INTO vedstatus VALUES ('" + felt[0] + "', '" + felt[1] + "')");
			}
			in.close();
			
			// Fyller bruker-tabellen med data fra fil
			in = new Scanner(new FileInputStream(initBruker), "UTF-8");
			in.nextLine();
			while (in.hasNextLine()) {
				String[] fields = in.nextLine().split(", ");	
				String person = fields[0];
				String password = fields[1];
				String isAdmin = fields[2];

				String statement = "INSERT INTO bruker (id, password_hash, is_admin) "
								 + "VALUES('"+person+"', '"+Bruker.hashPassword(password)+"', '"+isAdmin+"');";
 
				makeStatement(statement);
			}
			in.close();
			return true;

		} catch (Exception e) {
			if (DEBUG)
				e.printStackTrace();
			return false;
		}
	}

	/**
	 * Oppdaterer databasen med info fra et Koie-objekt
	 * @param koie Et Koie-objekt
	 * @return boolean - om koia ble lagret i databasen
	 */
	public static boolean toDatabase(Koie koie) {
		// Legger til vedmengden fra koia til databasen
		double vedmengde = koie.getVedmengde();		
		makeStatement("UPDATE vedstatus SET mengde = '" + vedmengde +"' WHERE koie_id=" + koie.getId());
		
		// Oppdaterer reservasjoner
		Calendar calendar = koie.getCalendar();
		List<BookingDate> datesBooked = calendar.getDatesBooked();
		for (BookingDate date : datesBooked) {
			if (date.isFromDatabase)
				continue;
			String bookedFrom = "" + date.dateFrom.year + "-" + date.dateFrom.month + "-" + date.dateFrom.day; 
			String bookedTo = "" + date.dateTo.year + "-" + date.dateTo.month + "-" + date.dateTo.day;
			
			String statement = "INSERT INTO reservasjon (koie_id, start_date, end_date, bruker_id) "
							 + "VALUES ('"+ koie.getId() + "','" + bookedFrom + "','" + bookedTo + "','" + date.person +"')";
			makeStatement(statement);
		}
		
		// Oppdaterer inventory
		Inventory inventory = koie.getInventory();
		List<Item> newItems = inventory.getNewItems();
		List<Item> oldItems = inventory.getOldItems();
		
		for (Item item : newItems) {
			addItem(item, koie.getId());
		}
		
		for (Item item : oldItems) {
			updateItem(item);
		}
		return true;
	}
	
	/**
	 * Sletter en reservasjon med gitt ID
	 * @param resID Reservasjons-ID
	 * @return returnerer om reservasjonen ble slettet
	 */
	public static boolean slettReservasjon(int resID) {
		return makeStatement("DELETE FROM reservasjon WHERE id =" + resID);
	}

	/**
	 * Gjør en spørring mot databasen
	 * @param query En query-streng
	 * @return Et resultat-sett
	 */
	private static ResultSet makeQuery(String query) {
		ResultSet res = null;
		try {
			Connection conn = getConnection();
			Statement st = conn.createStatement();
			res = st.executeQuery(query);
			//conn.close(); // Må kommenteres ut for at getIdNameMap skal fungere...
		} catch (Exception e) {
			if (DEBUG)
				e.printStackTrace();
			return null;
		}
		return res;
	}

	/**
	 * Utfører statements mot databasen
	 * @param statement Statement som skal utføres
	 * @return returnerer om statementen ble fullført
	 */
	private static boolean makeStatement(String statement) {
		try {
			Connection conn = getConnection();
			Statement st = conn.createStatement();
			int res = st.executeUpdate(statement);
			conn.close();
			return true;
		} catch (Exception e) {
			if (DEBUG)
				e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Returnerer en kobling til databasen
	 * @return Et Connection-objekt
	 */
	private static Connection getConnection() {
		try {		
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url+dbName,userName,password);
			return conn;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Returnerer en HashMap med ID og navn til en koie
	 * @return hashmap key:koie_id, value: koie_navn
	 */
	public static HashMap<Integer,String> getIdNameMap() {
		try {
			ResultSet res = makeQuery("SELECT id, name FROM koie");
			HashMap<Integer, String> idNameMap = new HashMap<Integer, String>();
			while (res.next()){
				idNameMap.put(res.getInt("id"), res.getString("name"));
			}
			return idNameMap;
		} catch (Exception e) {
			if (DEBUG)
				e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Returnerer et Koie-objekt fra databasen med en Koie-ID.
	 * @param koie_id Koias unike ID i databasen
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
							  + "topptur, jakt_og_fiske, spesialiteter "
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
				jaktOgFiske = koie_res.getString("jakt_og_fiske");
				spesialiteter = koie_res.getString("spesialiteter");
			}
			
			// Vedmengde
			ResultSet vedmengde_res = makeQuery("SELECT mengde FROM vedstatus WHERE koie_id='"+koie_id+"'");
			double mengde = 0.0;
			if (vedmengde_res.next()) {
				mengde = vedmengde_res.getDouble("mengde");
			}			
			
			// Lager Koie-objekt
			Koie koie = new Koie(koie_id, name, coordinate, year);
			koie.setNumBeds(numBeds);
			koie.setNumSeats(numSeats);
			koie.setTerreng(terreng);
			koie.setSykkel(sykkel);
			koie.setTopptur(topptur);
			koie.setJaktOgFiske(jaktOgFiske);
			koie.setSpesialiteter(spesialiteter);
			koie.setVedmengde(mengde);
			
			// Fyller Koie-objektet med reservasjonene
			ResultSet reservasjoner = makeQuery("SELECT id, bruker_id, start_date, end_date "
									+ "FROM reservasjon WHERE koie_id =" + koie_id);
			Calendar cabinRented = koie.getCalendar();
			while (reservasjoner.next()) {
				String person = reservasjoner.getString("bruker_id");
				int resID = reservasjoner.getInt("id");
				String fromDate = reservasjoner.getString("start_date");
				String toDate = reservasjoner.getString("end_date");			
				String[] fromParts = fromDate.split("-");
				String[] toParts = toDate.split("-");
				
				Date from = new Date(Integer.valueOf(fromParts[2]), Integer.valueOf(fromParts[1]));
				Date to = new Date(Integer.valueOf(toParts[2]), Integer.valueOf(toParts[1]));
				
				cabinRented.reservePeriod(from, to, person, resID, true);
			}

			String item_query = "SELECT id, name, status "
							  + "FROM item "
							  + "WHERE koie_id =" + String.valueOf(koie_id);
			ResultSet item_res = makeQuery(item_query);
			Inventory inventory = koie.getInventory();
			while (item_res.next()) {
				int itemId = item_res.getInt("id");
				String itemName = item_res.getString("name");
				String itemStatusString = item_res.getString("status");
				Item.Status itemStatus = Item.getItemStatus(itemStatusString);
				Item item = new Item(itemId, itemName, itemStatus);
				inventory.addItem(item);
			}

			return koie;
			
		} catch (Exception e) {
			if (DEBUG)
				e.printStackTrace();
			return null;
		}
	}
		
	/**
	 * Henter et Bruker-objekt fra databasen
	 * @param person Bruker-ID
	 * @return Et Bruker-objekt
	 */
	public static Bruker getBruker(String person) {
		try {
			String bruker_query = "SELECT password_hash, is_admin "
							 	+ "FROM bruker "
							 	+ "WHERE id = '" + person +"'";
			ResultSet bruker_res = makeQuery(bruker_query);
			if (bruker_res.next()) {
				String passwordHash = bruker_res.getString("password_hash");
				boolean isAdmin = bruker_res.getBoolean("is_admin");
				return new Bruker(person, passwordHash, isAdmin);
			} else {
				return null;
			}
		} catch (Exception e) {
			if (DEBUG)
				e.printStackTrace();
			return null;
		}
	}
		
	/**
	 * Returnerer alle brukere i databasen som Bruker-objekter
	 * @return Liste med Bruker-objekter
	 */
	public static List<Bruker> getAllBrukers() {
		try {
			String query = "SELECT id, password_hash, is_admin "
							 	+ "FROM bruker";
			ResultSet res = makeQuery(query);
			
			List<Bruker> allBrukers = new ArrayList<Bruker>();
			while (res.next()) {
				String person = res.getString("id");
				String passwordHash = res.getString("password_hash");
				boolean isAdmin = res.getBoolean("is_admin");
				allBrukers.add(new Bruker(person, passwordHash, isAdmin));
			}	

			return allBrukers;

		} catch (Exception e) {
			if (DEBUG)
				e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Legger en ny bruker til databasen
	 * @param id Bruker-ID
	 * @param password Passord som blir hashet 
	 * @param isAdmin Om brukeren skal være admninistrator eller ikke
	 * @return returnerer om en bruker ble lagt til i databasen
	 */
	public static boolean addBruker(String id, String password, boolean isAdmin) {
		try {
			String statement = "INSERT INTO bruker (id, password_hash, is_admin) "
							 + "VALUES('"+id+"', '"+Bruker.hashPassword(password)+"', '"+(isAdmin?1:0)+"')";
			return makeStatement(statement);
		} catch (Exception e) {
			if (DEBUG)
				e.printStackTrace();
			return false;
		}
	}

	/**
	 * Legger et Item-objekt inn i databasen
	 * @param item Et Item-objekt
	 * @param koie_id ID-en til koia som Item-objektet hører til
	 * @return returnerer om utstyret ble lagt til i databasen
	 */
	public static boolean addItem(Item item, int koie_id) {
		try {
			String statement = "INSERT INTO item (name, status, koie_id) "
							 + "VALUES('"+item.getName()+"', '"+item.getStatus()+"', '"+koie_id+"')";
			return makeStatement(statement);
		} catch (Exception e) {
			if (DEBUG)
				e.printStackTrace();
			return false;
		}
	}

	/**
	 * Oppdaterer statusen til et Item i databasen
	 * @param item Et Item-objekt
	 * @return returnerer om utstyret ble oppdatert
	 */
	public static boolean updateItem(Item item) {
		try {
			String statement = "UPDATE item SET status = '"+item.getStatus()+"' WHERE id = "+item.getId();
			return makeStatement(statement);
		} catch (Exception e) {
			if (DEBUG)
				e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Fjernet et Item fra databasen
	 * @param item Et Item-objekt som skal fjernes
	 * @return returnerer om utstyret ble slettet fra databasen
	 */
	public static boolean removeItem(Item item) {
		try {
			String statement = "DELETE FROM item WHERE id = "+item.getId();
			return makeStatement(statement);
		} catch (Exception e) {
			if (DEBUG)
				e.printStackTrace();
			return true;
		}
	}

	/**
	 * Returnerer alle reservasjonene til en bruker
	 * @param person Bruker-ID-en
	 * @return Liste med UserDatesBooked objekter hvor reservasjonene samnt reservasjons id ligger
	 */
	public static ArrayList<UserDatesBooked> getReservasjonBruker(String person) {
		ArrayList<UserDatesBooked> dates = new ArrayList<UserDatesBooked>();
		try {
			String query = "SELECT koie_id, start_date, end_date, id FROM reservasjon WHERE bruker_id =" + "'"+person+"'";
			ResultSet res = makeQuery(query);
			while (res.next()) {
				String fromDate = res.getString("start_date");
				String toDate = res.getString("end_date");			
				String[] fromParts = fromDate.split("-");
				String[] toParts = toDate.split("-");
				Date from = new Date(Integer.valueOf(fromParts[2]), Integer.valueOf(fromParts[1]));
				Date to = new Date(Integer.valueOf(toParts[2]), Integer.valueOf(toParts[1]));
				int koie_id = res.getInt("koie_id");
				int ID = res.getInt("id");
				
				dates.add(new UserDatesBooked(koie_id, from, to, ID));
			}
		} catch (Exception e) {
			if (DEBUG)
				e.printStackTrace();
		}
		
		return dates;
	}
	
	/**
	 * Sender rapporten/kommentaren til databasen
	 * @param koie_id Unik koie-ID
	 * @param person Unik bruker-ID
	 * @param kommentar Kommentar til rapporten
	 * @param resID Reservasjons-ID
	 * @return returnerer om rapporten ble lagret
	 */
	public static boolean rapporter(int koie_id, String person, String kommentar, int resID) {
		String statement = "INSERT INTO rapport (koie_id, bruker_id, kommentar, reservasjon_id) VALUES ('"
						 + koie_id +"', '" + person + "', '" + kommentar + "', '" + resID + "')";
		return makeStatement(statement);
	}
	
	/**
	 * Returnerer alle rapportene til en koie
	 * @param koie_id Unik koie-ID
	 * @return Liste med en streng på formen "personen-som-har-rapportert: rapporten" 
	 */
	public static ArrayList<String> getRapport(int koie_id) {
		ArrayList<String> rapport = new ArrayList<String>();
		try {
			String query = "SELECT kommentar, bruker_id FROM rapport WHERE koie_id =" + koie_id;
			ResultSet res = makeQuery(query);
			while (res.next()) {
				String kommentar = res.getString("bruker_id") + ": ";
				kommentar += res.getString("kommentar");
				rapport.add(kommentar);
			}
		} catch (Exception e) {
			if (DEBUG)
				e.printStackTrace();
		}
		return rapport;
	}
	
	/**
	 * Sjekker om det finnes en rapport med en gitt reservasjons-ID
	 * @param resID Reservasjons-ID
	 * @return true hvis rapporten finnes, false ellers
	 */
	public static boolean isRapportert(int resID) {
		try {
			ResultSet res = makeQuery("SELECT * FROM rapport WHERE reservasjon_id =" + resID);
			return res.next();
		} catch (Exception e) {
			if (DEBUG)
				e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Returnerer en HashMap med vedstatus for alle koiene
	 * @return HashMap med key: koie_id, value: vedmengde
	 */
	public static HashMap<Integer, Double> getVedstatusForAlleKoier() {
		HashMap<Integer, Double> vedstatus = new HashMap<Integer, Double>();
		try {
			ResultSet res = makeQuery("SELECT koie_id, mengde FROM vedstatus");
			while (res.next()) {
				vedstatus.put(res.getInt("koie_id"), res.getDouble("mengde"));
			}
		} catch (Exception e) {
			if (DEBUG)
				e.printStackTrace();
		}
		return vedstatus;
	}
}

package src;

import java.io.FileReader;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

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
	
	private static String initKoie = "Core/src/initialiseringAvKoier.txt";
	private static String initItem = "Core/src/dbinit_item.txt";
	private static String initVed = "Core/src/initialiseringAvVedstatus.txt";
	private static String initBruker = "Core/src/dbinit_bruker.txt";

	/**
	 *  Metode som initialiserer/resetter databasen med informasjon fra initialiseringsfiler
	 */
	public static void initializeDatabase() {
		try {
			//sletter alle tidligere tabeller
			makeStatement("DROP TABLE koie");
			makeStatement("DROP TABLE bruker");
			makeStatement("DROP TABLE inventory");
			makeStatement("DROP TABLE vedstatus");
			makeStatement("DROP TABLE reservasjon");
			makeStatement("DROP TABLE rapport");
			
			// Oppretter tabellene koie, bruker, inventory, vedstatus, reservasjon og rapport
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
						+ "(person VARCHAR(255) NOT NULL PRIMARY KEY, "
						+ "password_hash VARCHAR(255) NOT NULL, "
						+ "is_admin BOOL NOT NULL)");

			makeStatement("CREATE TABLE inventory"
						+ "(ID SMALLINT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
						+ "item VARCHAR(255) NOT NULL, "
						+ "status VARCHAR(255) NOT NULL, "
						+ "koie_id SMALLINT NOT NULL)");
			
			makeStatement("CREATE TABLE vedstatus"
						+ "(koie_id INT NOT NULL PRIMARY KEY, "
						+ "mengde DOUBLE NOT NULL)");
			
			makeStatement("CREATE TABLE reservasjon"
						+ "(ID int NOT NULL AUTO_INCREMENT PRIMARY KEY, "
						+ "koie_id SMALLINT NOT NULL, "
						+ "fromDate VARCHAR(255) NOT NULL, "
						+ "toDate VARCHAR(255) NOT NULL, "
						+ "bruker_id VARCHAR(255) NOT NULL)");
			
			makeStatement("CREATE TABLE rapport"
						+ "(resID int NOT NULL PRIMARY KEY, "
						+ "koie_id SMALLINT NOT NULL, "
						+ "person VARCHAR(255), "
						+ "kommentar VARCHAR(255))");
			
			// Fyller inn koie-tabellen fra fil
			Scanner in = new Scanner(new FileReader(initKoie));
			in.nextLine(); //hopper over f�rste linje som beskriver data
			//lager en insert query from hver linje i initialisertinAvKoier.txt
			while (in.hasNextLine()) {
				String[] kolonner = in.nextLine().split("#");	
				String statement = "INSERT INTO koie VALUES (";
				for (int i=0;i<kolonner.length-1; i++) {
					statement += "'"+kolonner[i]+"', ";
				}
				statement += "'"+kolonner[kolonner.length-1]+"')";
				//System.out.println(statement);
				makeStatement(statement);
			}
			in.close();
			
			// Fyller item-tabellen med data fra fil
			in = new Scanner(new FileReader(initItem));
			in.nextLine();
			while (in.hasNextLine()) {
				String[] fields = in.nextLine().split(", ");	
				String koieId = fields[0];
				String itemNavn = fields[1];

				String statement = "INSERT INTO inventory (item, status, koie_id) " 
								 + "VALUES ('"+itemNavn+"', 'IN_ORDER', '"+koieId+"')";

				makeStatement(statement);
			}
			in.close();
			
			
			//Fyller vedstatus tabellen med data fra fil
			in = new Scanner(new FileReader(initVed));
			in.nextLine();
			while (in.hasNextLine()) {
				String[] felt = in.nextLine().split(" ");
				makeStatement("INSERT INTO vedstatus VALUES ('" + felt[0] + "', '" + felt[1] + "')");
			}
			in.close();
			
			// Fyller bruker-tabellen med data fra fil
			in = new Scanner(new FileReader(initBruker));
			in.nextLine();
			while (in.hasNextLine()) {
				String[] fields = in.nextLine().split(", ");	
				String person = fields[0];
				String password = fields[1];
				String isAdmin = fields[2];

				String statement = "INSERT INTO bruker (person, password_hash, is_admin) "
								 + "VALUES('"+person+"', '"+Bruker.hashPassword(password)+"', '"+isAdmin+"');";
 
				makeStatement(statement);
			}
			in.close();
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("initialisering vellykket!");
		}
	}

	/**
	 * metode for � oppdatere databasen med info fra koie objektet sendt som argument til metoden
	 * @param koie
	 */
	public static void toDatabase(Koie koie) {
		// legger til vedmengden fra koia til databasen
		double vedmengde = koie.getVedmengde();		
		makeStatement("UPDATE vedstatus SET mengde = '" + vedmengde +"' WHERE koie_id=" + koie.getId());
		
		//sletter alle tidligere reservasjoner
		makeStatement("DELETE FROM reservasjon WHERE koie_id =" + koie.getId());
		//oppdaterer datoene koien er reservert for.
		Calendar calendar = koie.getCalendar();
		List<BookingDate> datesBooked = calendar.getDatesBooked();
		for (BookingDate date : datesBooked) {
			String bookedFrom = "" + date.dateFrom.year + "-" + date.dateFrom.month + "-" + date.dateFrom.day; 
			String bookedTo = "" + date.dateTo.year + "-" + date.dateTo.month + "-" + date.dateTo.day;
			
			String statement = "INSERT INTO reservasjon (koie_id, fromDate, toDate, bruker_id) "
							 + "VALUES ('"+ koie.getId() + "','" + bookedFrom + "','" + bookedTo + "','" + date.person +"')";
			
			makeStatement(statement);
		}
		
		//Inventory
		Inventory inventory = koie.getInventory();
		List<Item> newItems = inventory.getNewItems();
		List<Item> oldItems = inventory.getOldItems();
		
		for (Item item : newItems) {
			addItem(item, koie.getId());
		}
		
		for (Item item : oldItems) {
			updateItem(item);
		}
	}

	/**
	 * query metode mot databasen
	 * @param query
	 * @return  returnerer tilgang til database
	 */
	private static ResultSet makeQuery(String query) {
		ResultSet res = null;
		try {
			Connection conn = getConnection();
			Statement st = conn.createStatement();
			res = st.executeQuery(query);
			//conn.close(); m� kommenteres ut for at getIdNameMap skal fungere...
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Metode for � utf�re statements mot databasen
	 * @param statement
	 */
	private static void makeStatement(String statement) {
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
	
	/**
	 * Setter opp connection mot databasen
	 * @return Connection objekt
	 */
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
	
	/**
	 * Hashmap med koie id og koie navn
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
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Returnerer et Koie-objekt fra databasen med en Koie-id.
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
			
			//vedmengde
			ResultSet vedmengde_res = makeQuery("SELECT mengde FROM vedstatus");
			double mengde = 0.0;
			while (vedmengde_res.next()) {
				mengde = vedmengde_res.getDouble("mengde");
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
			koie.setVedmengde(mengde);
			
			//fyller koieobjektet med reservasjonene:
			ResultSet reservasjoner = makeQuery("SELECT ID, bruker_id, fromDate, toDate "
									+ "FROM reservasjon WHERE koie_id =" + koie_id);
			Calendar cabinRented = koie.getCalendar();
			while (reservasjoner.next()) {
				String person = reservasjoner.getString("bruker_id");
				int resID = reservasjoner.getInt("ID");
				String fromDate = reservasjoner.getString("fromDate");
				String toDate = reservasjoner.getString("toDate");			
				String[] fromParts = fromDate.split("-");
				String[] toParts = toDate.split("-");
				
				Date from = new Date(Integer.valueOf(fromParts[2]), Integer.valueOf(fromParts[1]));
				Date to = new Date(Integer.valueOf(toParts[2]), Integer.valueOf(toParts[1]));
				
				cabinRented.reservePeriod(from, to, person, resID);
			}

			String item_query = "SELECT item, status "
							  + "FROM inventory "
							  + "WHERE koie_id =" + String.valueOf(koie_id);
			ResultSet item_res = makeQuery(item_query);
			Inventory inventory = koie.getInventory();
			while (item_res.next()) {
				int itemId = item_res.getInt("ID");
				String itemName = item_res.getString("item");
				String itemStatusString = item_res.getString("status");
				Item.Status itemStatus = Item.getItemStatus(itemStatusString);
				Item item = new Item(itemId, itemName, itemStatus);
				inventory.addItem(item);
			}

			return koie;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
		
	/**
	 * Henter et Bruker-objekt fra databasen
	 * @param id Bruker-ID
	 * @return Et Bruker-objekt
	 */
	public static Bruker getBruker(String person) {
		try {
			String bruker_query = "SELECT password_hash, is_admin "
							 	+ "FROM bruker "
							 	+ "WHERE person = '" + person +"'";
			ResultSet bruker_res = makeQuery(bruker_query);
			if (bruker_res.next()) {
				String passwordHash = bruker_res.getString("password_hash");
				boolean isAdmin = bruker_res.getBoolean("is_admin");
				return new Bruker(person, passwordHash, isAdmin);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
		
	/**
	 * Legger en ny bruker til databasen
	 * @param id Bruker-ID
	 * @param password Passord som blir hashet 
	 * @param isAdmin
	 */
	public static void addBruker(String id, String password, boolean isAdmin) {
		try {
			String statement = "INSERT INTO bruker (person, password_hash, is_admin) "
							 + "VALUES('"+id+"', '"+Bruker.hashPassword(password)+"', '"+(isAdmin?1:0)+"')";
			makeStatement(statement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Legger et Item-objekt inn i databasen
	 * @param item Et Item-objekt
	 * @param koie_id ID-en til koia som Item-objektet hører til
	 */
	public static void addItem(Item item, int koie_id) {
		try {
			String statement = "INSERT INTO inventory (item, status, koie_id) "
							 + "VALUES('"+item.getName()+"', '"+item.getStatus()+"', '"+koie_id+"')";
			makeStatement(statement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Oppdaterer statusen til et Item i databasen
	 * @param item Et Item-objekt
	 */
	public static void updateItem(Item item) {
		try {
			String statement = "UPDATE inventory SET status = '"+item.getStatus()+"' WHERE ID = "+item.getId();
			makeStatement(statement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Henter alle reservasjonene en gitt bruker har reservert
	 * @param person
	 * @return Liste med UserDatesBooked objekter hvor reservasjonene samnt reservasjons id ligger
	 */
	public static ArrayList<UserDatesBooked> getReservasjonBruker(String person) {
		ArrayList<UserDatesBooked> dates = new ArrayList<UserDatesBooked>();
		try {
			String query = "SELECT koie_id, fromDate, toDate, ID FROM reservasjon WHERE bruker_id =" + "'"+person+"'";
			ResultSet res = makeQuery(query);
			while (res.next()) {
				String fromDate = res.getString("fromDate");
				String toDate = res.getString("toDate");			
				String[] fromParts = fromDate.split("-");
				String[] toParts = toDate.split("-");
				Date from = new Date(Integer.valueOf(fromParts[2]), Integer.valueOf(fromParts[1]));
				Date to = new Date(Integer.valueOf(toParts[2]), Integer.valueOf(toParts[1]));
				int koie_id = res.getInt("koie_id");
				int ID = res.getInt("ID");
				
				dates.add(new UserDatesBooked(koie_id, from, to, ID));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dates;
	}
	
	/**
	 * Sender rapporten/kommentaren til databasen
	 * @param koie_id
	 * @param person
	 * @param kommentar
	 * @param resID
	 */
	public static void rapporter(int koie_id, String person, String kommentar, int resID) {
		String statement = "INSERT INTO rapport (koie_id, person, kommentar, resID) VALUES ('"
						 + koie_id +"', '" + person + "', '" + kommentar + "', '" + resID + "')";
		makeStatement(statement);
	}
	
	/**
	 * Metode for � hente alle rapporter for en gitt koie
	 * @param koie_id
	 * @return Liste med en streng p� formen "<personen som har rapportert>: <rapporten>" 
	 */
	public static ArrayList<String> getRapport(int koie_id) {
		ArrayList<String> rapport = new ArrayList<String>();
		try {
			String query = "SELECT kommentar, person FROM rapport WHERE koie_id =" + koie_id;
			ResultSet res = makeQuery(query);
			while (res.next()) {
				String kommentar = res.getString("person") + ": ";
				kommentar += res.getString("kommentar");
				rapport.add(kommentar);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rapport;
	}
	
	/**
	 * Sjekker om det er en rapport for gitt reservasjons ID
	 * @param resID
	 * @return true hvis rapporten finnes, false ellers
	 */
	public static boolean isRapportert(int resID) {
		try {
			ResultSet res = makeQuery("SELECT * FROM rapport WHERE resID =" + resID);
			return res.next();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Henter vedstatus for alle koiene
	 * @return returnener hashmap med key: koie_id, value: vedmengde
	 */
	public static HashMap<Integer, Double> getVedstatusForAlleKoier() {
		HashMap<Integer, Double> vedstatus = new HashMap<Integer, Double>();
		try {
			ResultSet res = makeQuery("SELECT koie_id, mengde FROM vedstatus");
			while (res.next()) {
				vedstatus.put(res.getInt("koie_id"), res.getDouble("mengde"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vedstatus;
	}
}

















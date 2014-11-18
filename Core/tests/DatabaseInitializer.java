package tests;

import org.junit.Test;

import src.Database;

public class DatabaseInitializer {
	@Test
	public void initDB() {
		Database.initializeDatabase();
	}

}

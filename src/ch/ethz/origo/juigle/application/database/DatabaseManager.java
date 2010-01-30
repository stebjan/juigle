package ch.ethz.origo.juigle.application.database;

import java.io.File;
import java.sql.Connection;

import ch.ethz.origo.juigle.data.database.DbHandler;

/**
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (1/24/2010)
 * @since 0.1.0 (1/24/2010)
 * @see IDbHandler
 * @see IDbConnection
 * 
 */
public class DatabaseManager implements IDbHandler, IDbConnection {
	
	private DbHandler dbHandler;
	
	public DatabaseManager() {
		
	}
	
	public Connection getConnection() {
		return null;
	}
	
	public void createDatabaseFromXML(File file) {
		dbHandler.createDatabaseFromXML(file);
	}
	
	public void createDatabase(String command) {
		dbHandler.createDatabase(command);
	}

}
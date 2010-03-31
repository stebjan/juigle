package ch.ethz.origo.juigle.application.database;

import ch.ethz.origo.juigle.data.database.DBConnection;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.1 (3/31/2010)
 * @since 0.1.0 (1/24/2010)
 * 
 */
public interface IDBConnection {
	
	/**
	 * Return database connection from connection pool
	 * 
	 * @return database connection form connection pool
	 * @version 0.1.0 (1/24/2010)
	 * @since 0.1.0 (1/24/2010)
	 */
	public DBConnection getConnection();

	/**
	 * Connect to database given by database URL
	 * 
	 * @param databaseURL database url
	 * @param username user's nickname
	 * @param password database user's password
	 * @return current connection from connection pool
	 * @version 0.1.0 (1/24/2010)
	 * @since 0.1.0 (1/24/2010)
	 */
	public DBConnection connect(String databaseURL, String username, String password);

}

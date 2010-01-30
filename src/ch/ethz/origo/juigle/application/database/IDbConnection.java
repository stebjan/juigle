package ch.ethz.origo.juigle.application.database;

import java.sql.Connection;

/**
 * 
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (1/24/2010)
 * @since 0.1.0 (1/24/2010)
 * 
 */
public interface IDbConnection {
	
	public Connection getConnection();

}

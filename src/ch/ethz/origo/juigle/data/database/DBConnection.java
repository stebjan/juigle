package ch.ethz.origo.juigle.data.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Vaclav Souhrada
 * @version 0.1.1 (3/31/2010)
 * @since 0.1.0 (1/24/2010)
 * 
 */
public class DBConnection {
	
	private Connection conn;

	public Statement createStatement() throws SQLException {
		return conn.createStatement();
	}
	
	public void commit() throws SQLException {
		conn.commit();
	}
	
	public void rollback() throws SQLException {
		conn.rollback();
	}
	
	public void close() {
		
	}
	
}

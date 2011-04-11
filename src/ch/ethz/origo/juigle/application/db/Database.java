package ch.ethz.origo.juigle.application.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.jdesktop.swingx.auth.JDBCLoginService;

import ch.ethz.origo.juigle.application.exception.database.DatabaseException;

/**
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0.00
 * @since 1.0.x
 *
 */
public class Database {

	private Connection conn;

	private static JDBCLoginService service;

	public Database() {
	}

	public void connect(String jdbcClass, String address) {
		service = new JDBCLoginService(jdbcClass, address);
	}

	/**
	 * Close connection with database
	 * 
	 * @throws DatabaseException
	 */
	public synchronized void closeConnection() throws DatabaseException {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DatabaseException(e);
			}
		}
	}

	public JDBCLoginService getLoginServiceInstance() {
		return service;
	}

	/**
	 * Return database connection
	 * 
	 * @return actual database connection
	 */
	public Connection getConnection() {
		if (conn == null) {
			conn = service.getConnection();
		}
		return conn;
	}
}

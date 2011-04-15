package ch.ethz.origo.juigle.db_prepare;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import ch.ethz.origo.juigle.context.exceptions.DBException;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0.00 (9/24/2010)
 * @since 2.0.0 (9/24/2010)
 * @see ISQLHandler
 */
public class DefaultSQLHander implements ISQLHandler {

	private static Logger logger = Logger.getLogger(DefaultSQLHander.class);
	
	private boolean doExecuteUpdates;

	/**
	 * {@inheritDoc}
	 * 
	 * @version 0.1.0.00 (9/24/2010)
	 * @since 0.1.0.00 (9/24/2010)
	 */
	public int executeUpdate(String sql, DataSource dataSource) {
		logger.debug(sql);

		if (!doExecuteUpdates) {
			// skip update
			return 0;
		}
		Statement statement = null;
		try {
			statement = getConnection(dataSource).createStatement();
			return statement.executeUpdate(sql);

		} catch (Exception e) {
			throw new DBException("JG026:" + sql, e);
		} finally {
			closeQuietly(statement);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @version 0.1.0.00 (9/24/2010)
	 * @since 0.1.0.00 (9/24/2010)
	 */
	public int executeUpdateAndCommit(String sql, DataSource dataSource) {
		logger.debug(sql);

		if (!doExecuteUpdates) {
			// skip update
			return 0;
		}
		Statement statement = null;
		try {
			Connection connection = getConnection(dataSource);
			statement = connection.createStatement();
			int nbChanges = statement.executeUpdate(sql);
			if (!connection.getAutoCommit()) {
				connection.commit();
			}
			return nbChanges;

		} catch (Exception e) {
			throw new DBException("JG026:" + sql + "\n" + e.getMessage(), e);
		} finally {
			closeQuietly(statement);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @version 0.1.0.00 (9/24/2010)
	 * @since 0.1.0.00 (9/24/2010)
	 */
	public long getItemAsLong(String sql, DataSource dataSource) {
		logger.debug(sql);

		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = getConnection(dataSource).createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				return resultSet.getLong(1);
			}
		} catch (Exception e) {
			throw new DBException("JG027:" + sql, e);
		} finally {
			closeQuietly(null, statement, resultSet);
		}

		// in case no value was found, throw an exception
		throw new DBException("JG028:" + sql);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @version 0.1.0.00 (9/24/2010)
	 * @since 0.1.0.00 (9/24/2010)
	 */
	public String getItemAsString(String sql, DataSource dataSource) {
		logger.debug(sql);

		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = getConnection(dataSource).createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				return resultSet.getString(1);
			}
		} catch (Exception e) {
			throw new DBException("JG027:" + sql, e);
		} finally {
			closeQuietly(null, statement, resultSet);
		}

		// in case no value was found, throw an exception
		throw new DBException("JG028:" + sql);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @version 0.1.0.00 (9/24/2010)
	 * @since 0.1.0.00 (9/24/2010)
	 */
	public Set<String> getItemsAsStringSet(String sql, DataSource dataSource) {
		logger.debug(sql);

		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = getConnection(dataSource).createStatement();
			resultSet = statement.executeQuery(sql);
			Set<String> result = new HashSet<String>();
			while (resultSet.next()) {
				result.add(resultSet.getString(1));
			}
			return result;

		} catch (Exception e) {
			throw new DBException("JG027:" + sql, e);
		} finally {
			closeQuietly(null, statement, resultSet);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @version 0.1.0.00 (9/24/2010)
	 * @since 0.1.0.00 (9/24/2010)
	 */
	public boolean exists(String sql, DataSource dataSource) {
		logger.debug(sql);

		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = getConnection(dataSource).createStatement();
			resultSet = statement.executeQuery(sql);
			return resultSet.next();

		} catch (Exception e) {
			throw new DBException("JG027:" + sql, e);
		} finally {
			closeQuietly(null, statement, resultSet);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @version 0.1.0.00 (9/24/2010)
	 * @since 0.1.0.00 (9/24/2010)
	 */
	public boolean isDoExecuteUpdates() {
		return doExecuteUpdates;
	}

	/**
	 * Closes all connections that were created and cached by this SQLHandler.
	 * This method must always be invoked before disposing this object.
	 * 
	 * @version 0.1.0.00 (9/24/2010)
	 * @since 0.1.0.00 (9/24/2010)
	 */
	public void closeAllConnections() {
		for (Connection connection : cachedConnections.values()) {
			closeQuietly(connection);
		}
		cachedConnections.clear();
	}

	/**
	 * Returns a Connection to the given DataSource. The first time a Connection
	 * is requested, a new one is created using the given DataSource. All
	 * subsequenct calls with the same DataSource as parameter will return the
	 * same Connection instance.
	 * 
	 * @param dataSource
	 *          provides access to the database
	 * @return a Connection to the database for the given DataSource.
	 * 
	 * @version 0.1.0.00 (9/24/2010)
	 * @since 0.1.0.00 (9/24/2010)
	 */
	protected Connection getConnection(DataSource dataSource) {
		Connection connection = cachedConnections.get(dataSource);
		if (connection == null) {
			try {
				connection = dataSource.getConnection();
			} catch (SQLException e) {
				throw new DBException("JG029", e);
			}
			cachedConnections.put(dataSource, connection);
		}
		return connection;
	}

}

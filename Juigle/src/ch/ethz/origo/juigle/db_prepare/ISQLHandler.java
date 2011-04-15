package ch.ethz.origo.juigle.db_prepare;

import java.util.Set;

/**
 * Interface which contains basic methods for the database/application
 * operations.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.00 (9/24/2010)
 * @since 2.0.0 (9/24/2010)
 */
public interface ISQLHandler {

	/**
	 * Executes the given statement.
	 * 
	 * @param sql
	 *          The sql string for retrieving the items
	 * @param dataSource
	 * @return The nr of updates
	 * @version 0.1.00 (9/24/2010)
	 * @since 0.1.00 (9/24/2010)
	 */
	int executeUpdate(String sql, DataSource dataSource);

	/**
	 * Executes the given statement and commits the changes to the database
	 * 
	 * @param sql
	 *          The sql string for retrieving the items
	 * @param dataSource
	 * @return The number of updates
	 * @version 0.1.00 (9/24/2010)
	 * @since 0.1.00 (9/24/2010)
	 */
	int executeUpdateAndCommit(String sql, DataSource dataSource);

	/**
	 * Returns the long extracted from the result of the given query. If no value
	 * is found, a {@link DbMaintainException} is thrown.
	 * 
	 * @param sql
	 *          The sql string for retrieving the items
	 * @param dataSource
	 * @return The long item value
	 * 
	 * @version 0.1.00 (9/24/2010)
	 * @since 0.1.00 (9/24/2010)
	 */
	long getItemAsLong(String sql, DataSource dataSource);

	/**
	 * Returns the value extracted from the result of the given query. If no value
	 * is found, a {@link DbMaintainException} is thrown.
	 * 
	 * @param sql
	 *          The sql string for retrieving the items
	 * @param dataSource
	 * @return The string item value
	 * 
	 * @version 0.1.00 (9/24/2010)
	 * @since 0.1.00 (9/24/2010)
	 */
	String getItemAsString(String sql, DataSource dataSource);

	/**
	 * Returns the items extracted from the result of the given query.
	 * 
	 * @param sql
	 *          The sql string for retrieving the items
	 * @param dataSource
	 * @version 0.1.00 (9/24/2010)
	 * @since 0.1.00 (9/24/2010)
	 * 
	 * @return The items, not null
	 */
	Set<String> getItemsAsStringSet(String sql, DataSource dataSource);

	/**
	 * Returns true if the query returned a record.
	 * 
	 * @param sql
	 *          The sql string for checking the existence
	 * @param dataSource
	 * @return True if a record was returned
	 * 
	 * @version 0.1.00 (9/24/2010)
	 * @since 0.1.00 (9/24/2010)
	 */
	boolean exists(String sql, DataSource dataSource);

	/**
	 * Closes all connections that were created and cached by this SQLHandler.
	 * This method must always be invoked before disposing this object.
	 * 
	 * @version 0.1.00 (9/24/2010)
	 * @since 0.1.00 (9/24/2010)
	 */
	void closeAllConnections();
}

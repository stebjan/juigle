/*
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

/*
 *  
 *    Copyright (C) 2009 - 2010 
 *    							University of West Bohemia, 
 *                  Department of Computer Science and Engineering, 
 *                  Pilsen, Czech Republic
 */
package ch.ethz.origo.juigle.application.database;

import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import ch.ethz.origo.juigle.application.exception.database.DatabaseException;
import ch.ethz.origo.juigle.application.exception.database.SQLDDLException;
import ch.ethz.origo.juigle.data.database.DBConnection;
import ch.ethz.origo.juigle.data.database.model.DBComponent;
import ch.ethz.origo.juigle.data.database.model.Database;

/**
 * 
 * 
 * @author Vaclav Souhrada
 * @version 0.1.1 (3/31/2010)
 * @since 0.1.0 (1/24/2010)
 * @see IDBHandler
 * @see IDBConnection
 * 
 */
public class DatabaseManager implements IDBHandler, IDBConnection {

	private static Logger logger = Logger.getLogger(DatabaseManager.class);
	
  // commands
  protected final int ADD    = 0;
  protected final int CREATE = 1;
  protected final int DROP   = 2;
  protected final int MODIFY = 3;

	protected Database currentDatabase;
	protected DBConnection connection;

	public DatabaseManager() {

	}

	public DBConnection connect(String databaseURL, String username,
			String password) {
		return null;
	}

	@Override
	public DBConnection getConnection() {
		return connection;
	}

	/**
	 * Assign DLL SQL Syntax to the created component
	 * 
	 * @param component
	 * @throws Exception
	 */
	protected void assignDDLSQLSyntax(DBComponent component) throws Exception {
		
		//FIXME !!!! potreba dostat popis driveru
		
		
		//component.useDriverDescription(connection.getDriverDescription());
	}

	public void create(DBComponent component) throws DatabaseException {
		try {
			execute(CREATE, component);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
	}

	public void drop(DBComponent component) throws DatabaseException {
		try {
			execute(DROP, component);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
	}

	public void add(DBComponent component) throws DatabaseException {
		try {
			execute(ADD, component);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
	}

	public void modify(DBComponent component) throws DatabaseException {
		try {
			execute(MODIFY, component);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
	}

	/**
	 * Execute database operation
	 * 
	 * @param command
	 *          ADD/CREATE/DROP/MODIFY
	 * @param component
	 *          DbComponent
	 * @throws Exception
	 */
	protected void execute(int command, DBComponent component) throws Exception {
		// assign correct DDL SQL Syntax
		assignDDLSQLSyntax(component);

		// prepare query
		String query = null;
		switch (command) {
		case ADD:
			query = component.add();
			break;
		case CREATE:
			query = component.create();
			break;
		case DROP:
			query = component.drop();
			break;
		case MODIFY:
			query = component.modify();
			break;
		}

		// execute transaction
		transactionExecute(query);
	}

	/**
	 * Execute transaction commit Quries are separated by ;
	 * 
	 * @param queries
	 *          String
	 * @throws SQLException
	 */
	protected void transactionExecute(String queries) throws SQLException {
		DBConnection conn = getConnection();
		// FIXME - nastavit autocommit na false a pridat tu metodu do DBConnection
		Statement st = conn.createStatement();
		// separate queries
		StringTokenizer query = new StringTokenizer(queries, ";");
		// create transaction
		try {
			while (query.hasMoreTokens()) {
				String q = query.nextToken();
				logger.debug(q + " -> EXECUTE");
				st.executeUpdate(q);
			}
			conn.commit();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			conn.rollback();
			throw e;
		} finally {
			st.close();
			conn.close();
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void createDatabase(String name) throws DatabaseException {
		Database database = new Database(name);
		try {
			database.create();
			currentDatabase = database;
		} catch (SQLDDLException e) {
			throw new DatabaseException("JG020:" + name, e);
		}
	}

	@Override
	public void createDatabaseFromXML(File file) throws DatabaseException {

	}

}
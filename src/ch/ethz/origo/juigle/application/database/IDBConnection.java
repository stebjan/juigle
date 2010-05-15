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

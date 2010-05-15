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
package ch.ethz.origo.juigle.data.database.driver;

import ch.ethz.origo.juigle.data.database.model.DBComponent;

/**
 * 
 * JDBC Driver description
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (2/28/2010)
 * @since 0.1.0 (2/28/2010)
 * @see Exception
 * 
 */
public interface IDriverDescription {

	/**
	 * Driver description name
	 * 
	 * @return String description
	 */
	public String getDriverName();

	/**
	 * Get driver url (e.g. path)
	 * 
	 * @return url String
	 */
	public String[] getURL();

	/**
	 * Get full driver url (full path, not relative)
	 * 
	 * @return url String
	 */
	public String[] getFullURL();

	/**
	 * Get driver class path
	 * 
	 * @return classpath String
	 */
	public String getDriverClassName();

	/**
	 * Get connection string Using keywords: database ... database name host ...
	 * host server port ... password
	 * 
	 * @return connection String
	 */
	public String getConnectionString();

	/**
	 * Return type of database
	 * 
	 * @return type int
	 */
	public int getDataTypeOfDatabase();

	/**
	 * Assign driver features to the DbComponent
	 * 
	 * @param component
	 *          DbComponent
	 * @throws Exception
	 */
	public void assignDriverFeatures(DBComponent component) throws Exception;

	/**
	 * Return default port of database
	 * 
	 * @return port int
	 */
	public int getDefaultPort();

}
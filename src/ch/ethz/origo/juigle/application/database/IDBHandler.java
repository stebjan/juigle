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

import ch.ethz.origo.juigle.application.exception.database.DatabaseException;

/**
 * Interface for handling with database
 * 
 * @author Vaclav Souhrada
 * @version 0.2.0 (1/31/2010)
 * @since 0.1.0 (1/24/2010)
 * 
 */
public interface IDBHandler {
	
	/**
	 * This method create a new database 
	 * (if not exist) from <code>XML</code> file.
	 * 
	 * @param xmlFile file
	 */
	public void createDatabaseFromXML(File xmlFile) throws DatabaseException;
	
	/**
	 * This method create a new database by given name.
	 *  
	 * @param name of database
	 * @version 0.1.0 (3/31/2010)
	 * @since 0.1.0 (1/24/2010)
	 */
	public void createDatabase(String name) throws DatabaseException;

}

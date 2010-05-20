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
package ch.ethz.origo.juigle.data.database.model;

import java.util.ArrayList;
import java.util.List;

import ch.ethz.origo.juigle.data.database.DBUser;
import ch.ethz.origo.juigle.data.database.driver.IDriverDescription;
import ch.ethz.origo.juigle.data.database.model.ddl.DatabaseDDLSQLSyntax;

/**
 * Class representing database. 
 * Incomplete - need to finish in the future
 * 
 * @author Vaclav Souhrada
 * @version 0.1.1 (3/31/2010)
 * @since 0.1.0 (2/07/2010)
 * 
 */
public class Database extends DBComponent {
	
	private List<DBTable> listOfTables;
	
	private DBUser user;
	
	public Database(String name) {
		super(name, DBComponent.DATABASE_TYPE);
		listOfTables = new ArrayList<DBTable>();
	}
	
	public String getDatabaseName() {
		return getComponentName();
	}
	
	/**
	 * Add table to list of database tables
	 * 
	 * @param table which will be inserted to the list of all database tables
	 * @version 0.1.0 (3/31/2010)
	 * @since 0.1.1 (3/31/2010)
	 */
	public void addTable(DBTable table) {
		listOfTables.add(table);
	}
	
	/**
	 * Return list of tables
	 * 
	 * @return list of db tables
	 * @version 0.1.0 (3/31/2010)
	 * @since 0.1.1 (3/31/2010)
	 */
	public List<DBTable> getListOfTables() {
		return listOfTables;
	}

	@Override
	public Class<?> getDDLSQLSyntaxClass() {
		return DatabaseDDLSQLSyntax.class;
	}
	
	 /**
   * Get Wrapped table name
   * 
   * @return wrapped table name String
   */
  public String getWrappedDatabaseName() {
    return getWrappedCommand(getDatabaseName());
  }

	@Override
	public void useDriverDescription(IDriverDescription driver) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
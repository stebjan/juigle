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
 *    Copyright (C) 2009 - 2011 
 *    							University of West Bohemia, 
 *                  Department of Computer Science and Engineering, 
 *                  Pilsen, Czech Republic
 */
package ch.ethz.origo.juigle.database;

import ch.ethz.origo.juigle.context.exceptions.DatabaseException;
import ch.ethz.origo.juigle.context.exceptions.XMLDatabaseLoaderException;
import ch.ethz.origo.juigle.database.creator.EDBStructureCreatorType;
import ch.ethz.origo.juigle.database.creator.IDBStructureCreator;
import ch.ethz.origo.juigle.database.creator.XMLDatabaseTablesLoader;
import ch.ethz.origo.juigle.database.dbi.SQLQuery;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.1 (2/11/2011)
 * @since 1.0.0 (1/30/2011)
 */
public class DatabaseManager {

  private static DatabaseManager instance;
  private IDatabase database;

  private DatabaseManager() {
  }
  
  public static DatabaseManager getInstance() {
    if (instance == null) {
      instance = new DatabaseManager();
    }

    return instance;
  }

  public IDatabase getCurrentDatabase() {
    return database;
  }

  public void setCurrentDatabase(IDatabase currentDatabase) {
    this.database = currentDatabase;
  }
  
  public void createDatabaseStructure(String dabaseDocFilePath)
          throws DatabaseException {
    try {
      XMLDatabaseTablesLoader databaseXMLLoader = new XMLDatabaseTablesLoader(
              EDBStructureCreatorType.DATABASE);
      IDBStructureCreator dbCreator = databaseXMLLoader.parseDocument(dabaseDocFilePath);
      // now create database model in database
      // create db tables
      database.executeQuery(new SQLQuery(dbCreator.createTableStructure()));
      // create relations
      database.executeQuery(new SQLQuery(dbCreator.createRelationsStructure()));
    } catch (XMLDatabaseLoaderException e) {
      throw new DatabaseException(e);
    }
  }

}

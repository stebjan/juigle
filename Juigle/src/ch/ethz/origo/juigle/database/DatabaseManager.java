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

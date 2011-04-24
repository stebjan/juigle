package ch.ethz.origo.juigle.database.creator;

import org.apache.log4j.Logger;

import ch.ethz.origo.juigle.context.exceptions.DatabaseException;
import ch.ethz.origo.juigle.context.exceptions.SQLDDLException;
import ch.ethz.origo.juigle.database.model.DBModel;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see IDBStructureCreator
 * @version 0.2.1 (2/11/2011)
 * @since 1.0.0 (1/16/2011)
 */
public class DBStructureCreator implements IDBStructureCreator {

  private static final Logger logger = Logger.getLogger(DBStructureCreator.class);
  private DBModel model;

  /**
   * {@inheritDoc 
   */
  @Override
  public void setDatabaseModel(DBModel model) {
    this.model = model;
  }

  /**
   * {@inheritDoc
   */
  @Override
  public String createStructure() throws DatabaseException {
    try {
      return model.getAllSQLCommands();
    } catch (SQLDDLException e) {
      logger.error("Error - DBStructureCreator -> createStructure", e);
      throw new DatabaseException("Error - DBStructureCreator -> createStructure", e);
    }
  }

  @Override
  public String createTableStructure() throws DatabaseException {
    try {
      return model.getCreateTableSqlCommand();
    } catch (SQLDDLException e) {
      logger.error("Error - DBStructureCreator -> createTableStructure", e);
      throw new DatabaseException("Error - DBStructureCreator -> createTableStructure", e);
    }
  }

  @Override
  public String createRelationsStructure() throws DatabaseException {
    try {
      return model.getCreateRelationshipsSqlCommand();
    } catch (SQLDDLException e) {
      logger.error("Error - DBStructureCreator -> createRelationsStructure", e);
      throw new DatabaseException("Error - DBStructureCreator -> createRelationsStructure", e);
    }
  }

  @Override
  public String dropStructure() throws DatabaseException {
    try {
      return model.drop();
    } catch (SQLDDLException e) {
      logger.error("Error - DBStructureCreator -> dropStructure", e);
      throw new DatabaseException("Error - DBStructureCreator -> dropStructure", e);
    }
  }
}

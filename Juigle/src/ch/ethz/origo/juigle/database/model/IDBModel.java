package ch.ethz.origo.juigle.database.model;

import java.util.List;

import ch.ethz.origo.juigle.context.exceptions.SQLDDLException;
import ch.ethz.origo.juigle.database.model.constraint.DBForeignKeyConstraint;

/**
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 1.0.0 (2/11/2011)
 * @since 1.0.0 (1/30/2011)
 */
public interface IDBModel {

  public void addTable(DBTable table);

  public void addRelation(DBForeignKeyConstraint relation);

  public DBTable getTable(String name);

  public DBForeignKeyConstraint getRelation(String name);

  public List<DBTable> getListOfTables();

  public List<DBForeignKeyConstraint> getListOfRelations();

  /**
   *
   * 
   * @return
   * @throws SQLDDLException
   *
   * @version 1.0.0 (2/11/2011)
   * @since 0.1.0 (1/30/2011)
   */
  public String getCreateTableSqlCommand() throws SQLDDLException;

  /**
   *
   * @return
   * @throws SQLDDLException
   * 
   * @version 1.0.0 (2/11/2011)
   * @since 1.0.0 (2/11/2011)
   */
  public String getCreateRelationshipsSqlCommand() throws SQLDDLException;

  /**
   *
   * @return
   * @throws SQLDDLException
   *
   * @version 1.0.0 (2/11/2011)
   * @since 1.0.0 (2/11/2011)
   */
  public String getAllSQLCommands() throws SQLDDLException;

  public String drop() throws SQLDDLException;
}

package ch.ethz.origo.juigle.database.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ch.ethz.origo.juigle.context.exceptions.SQLDDLException;
import ch.ethz.origo.juigle.database.model.constraint.DBForeignKeyConstraint;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see IDBModel
 * @version 1.0.0 (2/11/2011)
 * @since 1.0.0 (1/30/2011)
 */
public class DBModel implements IDBModel {

  private Map<String, DBTable> mapOfTables;
  private Map<String, DBForeignKeyConstraint> mapOfRelations;

  public DBModel() {
    mapOfTables = new HashMap<String, DBTable>();
    mapOfRelations = new HashMap<String, DBForeignKeyConstraint>();
  }

  @Override
  public void addTable(DBTable table) {
    mapOfTables.put(table.getComponentName(), table);
  }

  @Override
  public void addRelation(DBForeignKeyConstraint relation) {
    mapOfRelations.put(relation.getComponentName(), relation);
  }

  @Override
  public DBTable getTable(String name) {
    return mapOfTables.get(name);
  }

  @Override
  public DBForeignKeyConstraint getRelation(String name) {
    return mapOfRelations.get(name);
  }

  @Override
  public List<DBTable> getListOfTables() {
    List<DBTable> listOfTables = new ArrayList<DBTable>();
    for (Entry<String, DBTable> entry : mapOfTables.entrySet()) {
      listOfTables.add(entry.getValue());
    }

    return listOfTables;
  }

  @Override
  public List<DBForeignKeyConstraint> getListOfRelations() {
    List<DBForeignKeyConstraint> listOfRelations = new ArrayList<DBForeignKeyConstraint>();
    for (Entry<String, DBForeignKeyConstraint> entry : mapOfRelations.entrySet()) {
      listOfRelations.add(entry.getValue());
    }

    return listOfRelations;
  }

  @Override
  public String drop() throws SQLDDLException {
    StringBuffer sb = new StringBuffer();
     for (DBForeignKeyConstraint fk : getListOfRelations())  {
      sb.append(fk.drop());
      sb.append(";");
    }

    for (DBTable table : getListOfTables()) {
      sb.append(table.drop());
      sb.append(";");
    }

    return sb.toString();
  }

  @Override
  public String getCreateTableSqlCommand() throws SQLDDLException {
    StringBuffer sb = new StringBuffer();
    // sql commands for creating tables
    for (DBTable table : getListOfTables()) {
      sb.append(table.create());
      sb.append(";");
    }

    return sb.toString();
  }

  @Override
  public String getCreateRelationshipsSqlCommand() throws SQLDDLException {
    StringBuffer sb = new StringBuffer();
     // sql commands for creating relationships
    for (DBForeignKeyConstraint fk : getListOfRelations())  {
      sb.append(fk.create());
      sb.append(";");
    }

    return sb.toString();
  }

  @Override
  public String getAllSQLCommands() throws SQLDDLException {
    StringBuffer sb = new StringBuffer();
    sb.append(getCreateTableSqlCommand());
    sb.append(getCreateRelationshipsSqlCommand());

    return sb.toString();
  }
}

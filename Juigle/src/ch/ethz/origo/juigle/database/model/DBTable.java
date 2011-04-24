package ch.ethz.origo.juigle.database.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import ch.ethz.origo.juigle.database.model.ddl.TableDDLSyntax;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see ADBComponent
 * @version 0.2.1 (2/06/2011)
 * @since 1.0.0 (1/15/2011)
 */
public class DBTable extends ADBComponent {

  private HashMap<String, DBColumn> mapOfDBColumns;
  private HashMap<String, DBConstraint> mapOfDBConstraint;
  private List<DBIndex> listOfIndexes;

  /**
   * Construct a new table
   *
   * @param name
   *          table name
   * @param type
   *          table type
   */
  public DBTable(String name) {
    super(name, ADBComponent.TABLE_COMPONENT_TYPE);
    mapOfDBColumns = new HashMap<String, DBColumn>();
  }

  /**
   * By this method you can add <code>DBColumn</code> to the Databse table object,
   * which represents classic database table.
   * 
   * @param column
   */
  public void addColumn(DBColumn column) {
    mapOfDBColumns.put(column.getComponentName(), column);
  }

  /**
   *
   * 
   * @param columnName
   * @version 0.1.0 (1/22/2011)
   * @since 0.1.1 (1/22/2011)
   * @return
   */
  public DBColumn getColumn(String columnName) {
    return mapOfDBColumns.get(columnName);
  }

  /**
   *
   *
   * @version 0.1.1 (2/06/2011)
   * @since 0.2.0 (1/23/2011)
   * @return
   */
  public List<DBColumn> getColumnsList() {
    List<DBColumn> listOfColumns = new ArrayList<DBColumn>();
    Set<Entry<String, DBColumn>> setOfColumns = mapOfDBColumns.entrySet();
    for (Entry<String, DBColumn> entry : setOfColumns) {
      listOfColumns.add(entry.getValue());
    }

    return listOfColumns;
  }

  /**
   * Return list of <code>DBConstraint</code> objects for current table.
   * If table hasn't contraint - then is returned empty List of Constraints. 
   * So, size of empty list is equal to 0;
   *
   * @version 0.1.1 (2/06/2011)
   * @since 0.2.0 (1/23/2011)
   * @return
   */
  public List<DBConstraint> getConstraintsList() {
    List<DBConstraint> listOfCons = new ArrayList<DBConstraint>();
    if (hasConstraints()) {
      Set<Entry<String, DBConstraint>> setOfCons = mapOfDBConstraint.entrySet();
      for (Entry<String, DBConstraint> entry : setOfCons) {
        listOfCons.add(entry.getValue());
      }
    }

    return listOfCons;
  }

  /**
   * Return <code>true</code> - if actual table has any constraints. Else false
   * is returned.
   * @version 0.1.0 (2/06/2011)
   * @since 0.2.1 (2/06/2011)
   * @return <code>true</code> - if actual table has any constraints. Else false
   *         is returned.
   */
  public boolean hasConstraints() {
    return mapOfDBConstraint != null && (mapOfDBConstraint.size() > 0);
  }

  /**
   *
   * 
   * @version 0.1.0 (1/22/2011)
   * @since 0.1.1 (1/22/2011)
   * @param pk
   */
  public void addConstraint(DBConstraint pk) {
    if (mapOfDBConstraint == null) {
      mapOfDBConstraint = new HashMap<String, DBConstraint>();
    }
    mapOfDBConstraint.put(pk.getComponentName(), pk);
  }

  /**
   *
   * 
   * @version 0.1.1 (1/23/2011)
   * @since 0.1.1 (1/22/2011)
   * @param constraintName
   * @return
   */
  public DBConstraint getConstraintByName(String constraintName) {
    if (mapOfDBConstraint == null) {
      return null;
    }

    return mapOfDBConstraint.get(constraintName);
  }

  /**
   *
   * 
   * @version 0.1.0 (1/23/2011)
   * @version 0.2.0 (1/23/2011)
   * @param index
   */
  public void addIndex(DBIndex index) {
    if (listOfIndexes == null) {
      listOfIndexes = new ArrayList<DBIndex>();
    }
    listOfIndexes.add(index);
  }

  /**
   *
   * 
   * @version 0.1.0 (1/23/2011)
   * @since 0.2.0 (1/23/2011)
   * @param indexName
   * @return
   */
  public DBIndex getIndex(String indexName) {
    if (listOfIndexes != null && listOfIndexes.size() > 0) {
      for (DBIndex index : listOfIndexes) {
        if (index.getComponentName().equals(indexName)) {
          return index;
        }
      }
    }

    return null;
  }

  @Override
  public Class<?> getDDLSQLSyntaxClass() {
    return TableDDLSyntax.class;
  }

  /**
   *
   *
   * @version 0.1.0 (1/23/2011)
   * @since 0.2.0 (1/23/2011)
   * @return
   */
  public String getWrappedTableName() {
    return getWrappedCommand(name);
  }
}

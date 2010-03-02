package ch.ethz.origo.juigle.data.database.model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import ch.ethz.origo.juigle.data.database.driver.DriverDescription;
import ch.ethz.origo.juigle.data.database.model.ddl.IndexDDLSQLSyntax;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/02/2010)
 * @since 0.1.0 (3/02/2010)
 * @see DBComponent
 *
 */
public class DBIndex extends DBComponent {

  private static int seed = 0;

  /** list of columns **/
  protected List<DBColumn> columns;

  /** order ASC/DESC **/
  protected List<Boolean> order;

  /** Owner table **/
  protected DBTable ownerTable;


  /**
   * CONSTRUCTOR: Create index with specific name and owner table.
   * Each index has to have a owner.
   *
   * @param indexName String
   * @param ownerTable DbTable
   */
  public DBIndex(String indexName, DBTable ownerTable) {
    super(indexName, DBComponent.INDEX_COMPONENT_TYPE);
    columns = new ArrayList<DBColumn>();
    order = new ArrayList<Boolean>();
    this.ownerTable = ownerTable;
  }

  /**
   * CONSTRUCTOR: Create index with owner table.
   * Each index has to have a owner.
   * The index name will be auto-generated.
   *
   * @param ownerTable DbTable
   */
  public DBIndex(DBTable ownerTable) {
    this(autoNameGenerator("I", ownerTable.getComponentName()), ownerTable);
  }


  public Class<?> getDDLSQLSyntaxClass() {
    return IndexDDLSQLSyntax.class;
  }

  public void useDriverDescription(DriverDescription driver) throws Exception {
    driver.assignDriverFeatures(this);
  }

  public String getIndexName() {
    return name;
  }

  public String getWrappedIndexName() {
    return getWrappedCommand(name);
  }

  public DBTable getOwnerTable() {
    return ownerTable;
  }

  /**
   * Add column to the index
   * ASC oder is default
   *
   * @param column name
   */
  public void addColumn(DBColumn column) {
    addColumn(column, true); // default
  }

  /**
   * Add column and specify order ASC/DESC
   *
   * @param column DbColumn
   * @param asc boolean
   */
  public void addColumn(DBColumn column, boolean asc) {
    columns.add(column);
    order.add(new Boolean(asc));
  }

  /**
   * Add columns (array of columns) to index
   * ASC order is default
   * (for another order, use addColumn() method)
   *
   * @param columns DbColumn[]
   */
  public void addColumns(DBColumn[] columns) {
    for (int i = 0; i < columns.length; i++) {
      addColumn(columns[i]);
    }
  }

  /**
   * Return columns as array
   *
   * @return dbColumns DbColumn[]
   */
  public DBColumn[] getColumns() {
   return (DBColumn[])columns.toArray();
  }
  
  /**
   * Return columns as ArrayList
   *
   * @return columns ArrayList
   */
  public List<DBColumn> getColumnsAsList() {
    return columns;
  }

  /**
   * Get column by approriate colid
   *
   * @param colid integer
   * @return column DbColumn
   */
  public DBColumn getColumn(int colid) {
    return columns.get(colid);
  }

  /**
   * Get column by column name.
   * Return null if column doesn't exist.
   *
   * @param name of column
   * @return DbColumn
   */
  public DBColumn getColumn(String name) {
    for(int i = 0; i < columns.size(); i++) {
      if ((columns.get(i)).getComponentName().equals(name)) {
        return columns.get(i);
      }
    }
    return null;
  }

  /**
   * Remove all columns from index
   */
  public void clearColumnsList() {
    columns.clear();
  }

  /**
   * Return order ASC/DESC as list
   *
   * @return ArrayList
   */
  public List<Boolean> getOrderAsList() {
    return order;
  }

  /**
   * Return order ASC/DESC for columns
   *
   * @return orders boolean[]
   */
  public boolean[] getOrder() {
    int count = order.size();
    boolean[] orders = new boolean[count];
    for (int i = 0; i < count; i++) {
      orders[i] = ((Boolean)order.get(i)).booleanValue();
    }
    return orders;
  }

  /**
   * Auto-generation of index name
   *
   * @param index String
   * @param tableName String
   * @return name String
   */
  protected static String autoNameGenerator(String index, String tableName) {
    NumberFormat nf = NumberFormat.getInstance();
    nf.setMinimumIntegerDigits(3);
    return index + "_" + tableName + "_" + nf.format(seed++);
  }

  public String toString() {
    // prepare list of columns
    StringBuffer cols = new StringBuffer();
    for(int i = 0; i < columns.size(); i++) {
      cols.append((columns.get(i)).getComponentName());
      if (i < columns.size()-1) {
        cols.append(", ");
      }
    }
    return DBComponent.INDEX_COMPONENT_TYPE + ": " + getIndexName() + " Columns: " + cols.toString();
  }

}
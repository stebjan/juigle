package ch.ethz.origo.juigle.database.model;

import ch.ethz.origo.juigle.database.model.ddl.ColumnDDLSyntax;
import ch.ethz.origo.juigle.database.model.types.ASQLDataType;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see ADBComponent
 * @version 0.2.1 (1/23/2011)
 * @since 1.0.0 (1/16/2011)
 */
public class DBColumn extends ADBComponent {

  /** Is it part of PK */
  protected final boolean primaryKey;
  /** Is it not null column */
  protected boolean notNull;
  private ASQLDataType sqlType;
  private String tableName;

  public DBColumn(String name, ASQLDataType sqlType, boolean primaryKey,
          boolean notNull) {
    super(name, ADBComponent.COLUMN_COMPONENT_TYPE);
    this.sqlType = sqlType;
    this.primaryKey = primaryKey;
    this.notNull = notNull;
  }

  public DBColumn(String name, ASQLDataType sqlType, boolean notNull) {
    this(name, sqlType, false, notNull);
  }

  public DBColumn(String name, ASQLDataType sqlType) {
    this(name, sqlType, false, true);
  }

  public DBColumn(String name) {
    this(name, null);
  }

  /**
   * 
   * {@inheritDoc}
   * @version 0.1.0 (1/19/2011)
   * @since 0.2.0 (1/19/2011)
   */
  @Override
  public void setDatabaseType(int databaseType) {
    super.setDatabaseType(databaseType);
    sqlType.setDatabaseType(databaseType);
  }

  /**
   *
   * 
   * @version 0.1.0 (1/19/2011)
   * @since 0.2.0 (1/19/2011)
   * @return
   */
  public ASQLDataType getSQLType() {
    return sqlType;
  }

  /**
   * Set owner database name of table
   * @version 0.1.0 (1/19/2011)
   * @since 0.2.0 (1/19/2011)
   * @param table name
   */
  public void setOwnerTableName(String tableName) {
    this.tableName = tableName;
  }

  /**
   * Get owner database name of table
   * @version 0.1.0 (1/19/2011)
   * @since 0.2.0 (1/19/2011)
   * @return name of owner table
   */
  public String getOwnerTableName() {
    return tableName;
  }

  /**
   * {@inheritDoc }
   */
  @Override
  public Class<?> getDDLSQLSyntaxClass() {
    return ColumnDDLSyntax.class;
  }

  /**
   * Get info if is a primary key
   * 
   * @version 0.1.0 (1/19/2011)
   * @since 0.2.0 (1/19/2011)
   * @return true - if is primary key, else return false
   */
  public boolean isPrimaryKey() {
    return primaryKey;
  }

  /**
   * 
   * Return null information
   *
   * @version 0.1.0 (1/19/2011)
   * @since 0.2.0 (1/19/2011)
   * @return true - if is null, else return false
   */
  public boolean isNotNull() {
    return notNull;
  }

  /**
   * @version 0.1.0 (1/23/2011)
   * @since 0.2.1 (1/23/2011)
   * @return
   */
  public String getWrappedColumnName() {
    return getWrappedCommand(name);
  }

  /**
   * @version 0.1.0 (1/23/2011)
   * @since 0.2.1 (1/23/2011)
   * @return
   */
  public String getWrappedOwnerTableName() {
    return getWrappedCommand(tableName);
  }

  /**
   * Return null information
   *
   * @version 0.1.0 (1/23/2011)
   * @since 0.2.1 (1/23/2011)
   * @return null as String
   */
  public String getNullInfo() {
    return isNotNull() ? " NOT NULL" : "";
  }

  @Override
  public String toString() {
    return getComponentName() + ":  SQL-Type: " + sqlType.getSyntax()
            + ", PK: " + isPrimaryKey() + ", isNotNull: " + isNotNull();
  }
}

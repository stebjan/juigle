package ch.ethz.origo.juigle.data.database.model;

import ch.ethz.origo.juigle.data.database.driver.DriverDescription;
import ch.ethz.origo.juigle.data.database.model.constraint.ForeignKeyConstraint;
import ch.ethz.origo.juigle.data.database.model.ddl.ColumnDDLSQLSyntax;
import ch.ethz.origo.juigle.data.database.model.types.AbstractSQLDataType;

/**
 * 
 * @author Vaclav Souhrada
 * @version 0.1.2 (3/03/2010)
 * @since 0.1.0 (1/24/2010)
 * @see DBComponent
 * 
 */
public class DBColumn extends DBComponent {
	
	/** Owner table */
  protected DBTable table = null;

  protected final String name;
  protected final AbstractSQLDataType sqlType;

  /** is it part of PK */
  protected final boolean primaryKey;

  /** is it not null column */
  protected final boolean notNull;

  /** edit masks */
  protected Object editMask;
  protected Object displayMask;

  /** caption -- used in tables */
  protected Object caption;

  /** long caption -- used in labels */
  protected Object longCaption;

  protected Object charset;

  protected Object collate;

	
	public DBColumn(String name, AbstractSQLDataType sqlType, boolean primaryKey, boolean notNull, String editMask, String displayMask,
      String caption, String longCaption, String charset, String collate) {
		super(name, DBComponent.COLUMN_COMPONENT_TYPE);
    this.name = name;
    this.sqlType = sqlType;
    this.primaryKey = primaryKey;
    this.notNull = notNull;
    this.editMask = editMask;
    this.displayMask = displayMask;
    this.caption = caption;
    this.longCaption = longCaption;
    this.charset = charset;
    this.collate = collate;
	}
	
	 public DBColumn(String name, AbstractSQLDataType sqlType, boolean primaryKey) {
	    this(name, sqlType, primaryKey, primaryKey, null, null, null, null, null, null);
	  }

	  public DBColumn(String name, AbstractSQLDataType sqlType, boolean primaryKey, boolean notNull) {
	    this(name, sqlType, primaryKey, notNull, null, null, null, null, null, null);
	  }

	  public DBColumn(String name, AbstractSQLDataType sqlType, boolean primaryKey, boolean notNull, String editMask, String displayMask) {
	    this(name, sqlType, primaryKey, notNull, editMask, displayMask, null, null, null, null);
	  }

	  /**
	   *
	   *
	   * @param name column name
	   * @param sqlType column sql type
	   * @param primaryKey true - if this column is a primary key
	   * @param notNull true - if this column do not be null
	   * @param charset - encoding - charset of column
	   */
	  public DBColumn(String name, AbstractSQLDataType sqlType, boolean primaryKey, boolean notNull, String charset) {
	    this(name, sqlType, primaryKey, notNull, null, null, null, null, charset, null);
	  }

	@Override
	public Class<?> getDDLSQLSyntaxClass() {
		return ColumnDDLSQLSyntax.class;
	}
	
	/**
   * Get wrapped column, if it is active
   *
   * @return String
   */
  public String getWrappedName() {
    // wrapped column name information
    return properties.getWrappedCommands() + name + properties.getWrappedCommands();
  }
  
  public void setDatabaseType(int databaseType) {
    super.setDatabaseType(databaseType);
    sqlType.setDatabaseType(databaseType);
  }

  public void useDriverDescription(DriverDescription driver) throws Exception {
    driver.assignDriverFeatures(this);
  }

  public String getEditMask() {
    return (editMask != null) ? editMask.toString() : null;
  }

  public String getDisplayMask() {
    return (displayMask != null) ? displayMask.toString() : null;
  }

  public String getCaption() {
    return (caption != null) ? caption.toString() : null;
  }

  public String getLongCaption() {
    return (longCaption != null) ? longCaption.toString() : null;
  }

  public AbstractSQLDataType getSQLType() {
    return sqlType;
  }

  /**
   * Get Column name
   *
   * @return columnName String
   */
  public String getName() {
    return name;
  }

  /**
   * Return null information
   *
   * @return true - if is null, else return false
   */
  public boolean isNotNull() {
    return notNull;
  }

  /**
   * Return null information
   *
   * @return null as String
   */
  public String getNullInfo() {
    return isNotNull() ? " NOT NULL" : "";
  }

  /**
   * Get info if is a primary key
   *
   * @return true - if is primary key, else return false
   */
  public boolean isPrimaryKey() {
    return primaryKey;
  }


  /**
   * Set owner database table
   *
   * @param table name
   */
  public void setOwnerDbTable(DBTable table) {
    this.table = table;
  }

  /**
   * Get owner database table
   *
   * @return table DbTable
   */
  public DBTable getOwnerTable() {
    return table;
  }

  /**
   * Method for table connectivity Linking beween primary keys a foreign keys
   *
   * Example: ownerTable.getColumn("empno").joinColumn(targetTable.getColumn("empno")); => CONSTRAINT fk_ownerTable_1 FOREIGN KEY
   * empno REFERENCES targetTable(empno);
   *
   * @param column TargetColumn
   */
  public void joinColumn(DBColumn column) {
    // create foreign key between tables
    ForeignKeyConstraint fk = new ForeignKeyConstraint(getOwnerTable(), this, column.getOwnerTable(), column);
    getOwnerTable().addConstraint(fk);
  }

  /**
   * Gets character set for column.
   *
   * @return Object for charset.
   */
  public Object getCharset() {
    return charset;
  }

  /**
   * Sets character set for column.
   *
   * @param charset Charset.
   */
  public void setCharset(Object charset) {
    this.charset = charset;
  }

  /**
   * Gets collation for column.
   *
   * @return Object for collation.
   */
  public Object getCollate() {
    return collate;
  }

  /**
   * Sets collation for column.
   *
   * @param collate Collation.
   */
  public void setCollate(Object collate) {
    this.collate = collate;
  }
	
  public String toString() {
  	return getComponentName() + ": " + getName() + ";" + " SQL-Type: " + sqlType.getSyntax() + ", PK: " + isPrimaryKey()
  	+ ", isNotNull: " + isNotNull() + ", editMask: " + getEditMask() + ", displayMask: " + getDisplayMask() + ", caption: "
  	+ getCaption() + ", longCaption: " + getLongCaption();
  }

}

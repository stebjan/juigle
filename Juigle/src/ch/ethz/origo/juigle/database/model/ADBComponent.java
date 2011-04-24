package ch.ethz.origo.juigle.database.model;

import java.lang.reflect.Constructor;

import org.apache.log4j.Logger;

import ch.ethz.origo.juigle.context.exceptions.SQLDDLException;
import ch.ethz.origo.juigle.database.model.ddl.IDDLSQLSyntax;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see IDDLSQLSyntax
 * @version 0.1.4 (2/06/2011)
 * @since 1.0.0 (1/15/2011)
 */
public abstract class ADBComponent implements IDDLSQLSyntax {

  private static final Logger logger = Logger.getLogger(ADBComponent.class);
  /** Component name */
  protected final String name;
  /** Component type */
  protected final String type;
  /** Database component type - DATABASE */
  public static final String DATABASE_TYPE = "DATABASE";
  /** Database component type - TABLE */
  public static final String TABLE_COMPONENT_TYPE = "TABLE";
  /** Database component type - COLUMN */
  public static final String COLUMN_COMPONENT_TYPE = "COLUMN";
  /** Database component type - CONSTRAINT */
  public static final String CONSTRAINT_COMPONENT_TYPE = "CONSTRAINT";
  /** Database component type - INDEX */
  public static final String INDEX_COMPONENT_TYPE = "INDEX";
  /** Type of database */
  protected int databaseType = -1;
  /** Syntax of SQL DDL */
  private IDDLSQLSyntax ddlSyntax;
  protected DBComponentProperties properties;

  /**
   * Construct a new component with specific name and type. Also are set a default
   * component properties. You can change default properties by calling method
   * <code>setProperties(DBComponentProperties properties)</code>.
   *
   * @param name
   *          component name
   * @param type
   *          component type
   */
  public ADBComponent(String name, String type) {
    this.name = name;
    this.type = type;
    // set default component properties
    this.properties = new DBComponentProperties();
  }

  /**
   * Construct a new component
   *
   * @param name
   *          component name
   * @param type
   *          component type
   */
  public ADBComponent(String name, String type, DBComponentProperties properties) {
    this(name, type);
    this.properties = properties;
  }

  /**
   * Return name of component
   *
   * @return name of component as String
   */
  public String getComponentName() {
    return name;
  }

  /**
   * Return type of component
   *
   * @return type of component as String
   */
  public String getComponentType() {
    return type;
  }

  /**
   * Set database type It's important information for specific tasks in the
   * database (e.g. data types)
   *
   * @param databaseType
   *          int - type of database
   */
  public void setDatabaseType(int databaseType) {
    this.databaseType = databaseType;
  }

  /**
   * The method specifies which syntax will be use for DLL statements. E.g.
   * BasicTableDLLSQLSyntax, BasicColumnDDLSQLSyntax etc.
   *
   * @return syntax-class Class
   */
  abstract public Class<?> getDDLSQLSyntaxClass();

  /**
   * Set DDL SQL Syntax to DbComponent
   *
   * @param ddlSyntax
   *          DDLSQLSyntax
   */
  public void setDDLSQLSyntax(IDDLSQLSyntax ddlSyntax) {
    this.ddlSyntax = ddlSyntax;
  }

  /**
   *
   * 
   * @version 0.1.0 (1/22/2011)
   * @since 0.1.0 (1/22/2011)
   * @param properties
   */
  public void setProperties(DBComponentProperties properties) {
    this.properties = properties;
  }

  /**
   *
   * 
   * @version 0.1.0 (1/22/2011)
   * @since 0.1.0 (1/22/2011)
   * @return
   */
  public DBComponentProperties getProperties() {
    return properties;
  }

  /**
   * Create wrapped command
   *
   * @version 0.1.0 (1/22/2011)
   * @since 0.1.0 (1/22/2011)
   * @param command
   * @return wrapped command String
   */
  protected String getWrappedCommand(String command) {
    return properties.getWrappedCommands() + command
            + properties.getWrappedCommands();
  }

  @Override
  public String add() throws SQLDDLException {
    if (ddlSyntax == null) {
      createDDLSyntax();
    }
    return ddlSyntax.add();
  }

  @Override
  public String create() throws SQLDDLException {
    if (ddlSyntax == null) {
      createDDLSyntax();
    }
    return ddlSyntax.create();
  }

  @Override
  public String drop() throws SQLDDLException {
    if (ddlSyntax == null) {
      createDDLSyntax();
    }
    return ddlSyntax.drop();
  }

  @Override
  public String modify() throws SQLDDLException {
    if (ddlSyntax == null) {
      createDDLSyntax();
    }
    return ddlSyntax.modify();
  }

  /**
   *
   * 
   * @version 0.1.0 (2/06/2011)
   * @version 0.1.4 (2/06/2011)
   * @throws SQLDDLException
   */
  private void createDDLSyntax() throws SQLDDLException {
    try {
      Constructor constructor = getDDLSQLSyntaxClass().getDeclaredConstructor(
              ADBComponent.class);
      constructor.setAccessible(true);
      ddlSyntax = (IDDLSQLSyntax) constructor.newInstance(this);
      setDDLSQLSyntax(ddlSyntax);
    } catch (Exception ex) {
      logger.error(ex.getMessage(), ex);
      throw new SQLDDLException("Can not create DDL syntax...", ex);
    }
  }

  /**
   * {@inheritDoc}
   *
   * @version 0.1.0 (1/23/2011)
   * @since 0.1.3 (1/23/2011)
   * @return
   */
  @Override
  public String toString() {
    return "ADBComponent{ " + "name=" + name + ", type=" + type + ", databaseType="
            + databaseType + ", ddlSyntax=" + ddlSyntax + ", properties="
            + properties + " }";
  }
}

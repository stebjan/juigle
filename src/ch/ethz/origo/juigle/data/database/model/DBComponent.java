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
 *    Copyright (C) 2009 - 2010 
 *    							University of West Bohemia, 
 *                  Department of Computer Science and Engineering, 
 *                  Pilsen, Czech Republic
 */
package ch.ethz.origo.juigle.data.database.model;

import org.apache.log4j.Logger;

import ch.ethz.origo.juigle.application.exception.database.SQLDDLException;
import ch.ethz.origo.juigle.data.database.ComponentProperties;
import ch.ethz.origo.juigle.data.database.driver.IDriverDescription;
import ch.ethz.origo.juigle.data.database.model.ddl.IDDLSQLSyntax;

/**
 * <code>Abstract</code> class for the database components (Table, Column, ...)
 * 
 * @author Vaclav Souhrada
 * @version 0.2.1 (3/02/2010)
 * @since 0.1.0 (1/24/2010)
 * 
 */
public abstract class DBComponent {

	/** Logger */
	protected final static Logger logger = Logger.getLogger(DBComponent.class);

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

	/** Default component properties */
	ComponentProperties properties;

	/** Syntax of SQL DDL */
	IDDLSQLSyntax ddlSyntax;

	/**
	 * Construct a new component
	 * 
	 * @param name
	 *          component name
	 * @param type
	 *          component type
	 */
	public DBComponent(String name, String type) {
		this.name = name;
		this.type = type;
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
	 * Set BasicComponentProperties: extended features for creation of components
	 * 
	 * @param properties
	 *          BasicComponentProperties
	 */
	public void setProperties(ComponentProperties properties) {
		this.properties = properties;
	}

	/**
	 * Get BasicComponentProperties: extended features for creation of components
	 * 
	 * @return BasicComponentProperties
	 */
	public ComponentProperties getProperties() {
		return properties;
	}
	
  /**
   * Create wrapped command
   * 
   * @param command
   * @return wrapped command String
   */
  protected String getWrappedCommand(String command) {
    return properties.getWrappedCommands() + command + properties.getWrappedCommands();
  }

	/**
	 * The method specifies which syntax will be use for DLL statements. E.g.
	 * BasicTableDLLSQLSyntax, BasicColumnDDLSQLSyntax etc.
	 * 
	 * @return syntax-class Class
	 */
	abstract public Class<?> getDDLSQLSyntaxClass();
	
	/**
   * Driver specific properties
   * Support for a hierarchic structure of components
   * 
   * @param driver DriverDescription
   */
  abstract public void useDriverDescription(IDriverDescription driver) throws Exception;


	/**
	 * Set DDL SQL Syntax to DbComponent
	 * 
	 * @param ddlSyntax
	 *          DDLSQLSyntax
	 */
	public void setDDLSQLSyntax(IDDLSQLSyntax ddlSyntax) {
		this.ddlSyntax = ddlSyntax;
	}

	/** DDL SQL Syntax implementation **/

	public String add() throws SQLDDLException {
		return ddlSyntax.add();
	}

	public String create() throws SQLDDLException {
		return ddlSyntax.create();
	}

	public String drop() throws SQLDDLException {
		return ddlSyntax.drop();
	}

	public String modify() throws SQLDDLException {
		return ddlSyntax.modify();
	}

}
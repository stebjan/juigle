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
package ch.ethz.origo.juigle.data.database.driver;

import java.lang.reflect.Constructor;
import java.util.StringTokenizer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ch.ethz.origo.juigle.application.xml.XMLSerializable;
import ch.ethz.origo.juigle.data.database.ComponentProperties;
import ch.ethz.origo.juigle.data.database.model.DBComponent;
import ch.ethz.origo.juigle.data.database.model.constraint.ForeignKeyConstraint;
import ch.ethz.origo.juigle.data.database.model.ddl.CheckConstraintDDLSQLSyntax;
import ch.ethz.origo.juigle.data.database.model.ddl.ColumnDDLSQLSyntax;
import ch.ethz.origo.juigle.data.database.model.ddl.IDDLSQLSyntax;
import ch.ethz.origo.juigle.data.database.model.ddl.IndexDDLSQLSyntax;
import ch.ethz.origo.juigle.data.database.model.ddl.PrimaryKeyDDLSyntax;
import ch.ethz.origo.juigle.data.database.model.ddl.TableDDLSQLSyntax;
import ch.ethz.origo.juigle.data.database.model.ddl.UniqueConstraintDDLSQLSyntax;
import ch.ethz.origo.juigle.data.xml.JUIGLEDomParser;

/**
 * Abstract Driver Description Ancestor of all drivers. It is default setting
 * of drivers
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.1 (3/02/2010)
 * @since 0.1.0 (2/28/2010)
 * @see IDriverDescription
 * @see XMLSerializable
 * 
 */
public abstract class AbstractDriverDescription implements IDriverDescription,
		XMLSerializable {

	/** All DDL SQL syntaxes for specific driver **/
	protected Class<?>[] ddlsyntaxes;

	/** Extended properties **/
	protected ComponentProperties properties;

	/** Attributes **/

	protected String driverName;
	protected String[] url = new String[9];
	protected int urlCount;
	protected String classnName;
	protected String connectionString;

	/**
	 * CONSTRUCTOR: create driver description INCLUDES: - Basic DDL SQL syntax for
	 * DbComponents IMPORTANT: The constructor creates DEFAULT DLL SQL Syntaxes.
	 * You can change the syntax in children of drivers.
	 */
	public AbstractDriverDescription() {
		// DEFAULT: Basic DDL SQL Syntaxes for components
		ddlsyntaxes = new Class[] { 
				TableDDLSQLSyntax.class,
				ColumnDDLSQLSyntax.class, 
				PrimaryKeyDDLSyntax.class,
				UniqueConstraintDDLSQLSyntax.class, 
				ForeignKeyConstraint.class,
				CheckConstraintDDLSQLSyntax.class, 
				IndexDDLSQLSyntax.class };
		ddlsyntaxes = includeDDLSQLSyntaxes(ddlsyntaxes); // for children

		// DEFAULT: Extended properties
		properties = new ComponentProperties();
		properties = includeExtendedProperties(properties); // for children
	}

	public void assignDriverFeatures(DBComponent component) throws Exception {
		assignDLLSQLSyntax(component);
		assignExtendedProperties(component);
		assignDataTypes(component);
	}

	/** DESCRIPTION OF DATA TYPES **/

	/**
	 * Include specific database type for data types
	 * 
	 * @return databaseType by SQLDataType.MSSQL, SQLDataType.ORACLE, etc.
	 */
	abstract protected int includeDataTypeOfDatabase();

	/**
	 * Assign database type to the component for correct data types
	 * 
	 * @param component
	 *          DBComponent
	 */
	protected void assignDataTypes(DBComponent component) {
		component.setDatabaseType(includeDataTypeOfDatabase());
	}

	/** DESCRIPTION OF EXTENDED DB-PROPERTIES **/

	/**
	 * Include extended properties to the DbComponent
	 * 
	 * @param properties
	 *          input DbComponentProperties
	 * @return properties output DbComponentProperties
	 */
	abstract protected ComponentProperties includeExtendedProperties(
			ComponentProperties properties);

	/**
	 * Assign extended properties to the DbComponent
	 * 
	 * @param component
	 *          DbComponent
	 */
	protected void assignExtendedProperties(DBComponent component) {
		component.setProperties(properties);
	}

	/** DESCRIPTION OF DDL SQL SYNTAXES **/

	/**
	 * Include DLL SQL Syntaxes for DbComponents
	 * 
	 * @param dllsyntaxes
	 *          Class[]
	 * @return new dllsyntaxes Class[]
	 */
	abstract protected Class<?>[] includeDDLSQLSyntaxes(Class<?>[] dllsyntaxes);

	/**
	 * Assign DLL Syntaxes to the DbComponent (it's part of assignDriverFeatures()
	 * method)
	 * 
	 * @param component
	 *          DbComponent
	 * @throws Exception
	 */
	protected void assignDLLSQLSyntax(DBComponent component) throws Exception {
		Class<?> syntaxClass = component.getDDLSQLSyntaxClass();
		// find correct syntax
		for (int i = 0; i < ddlsyntaxes.length; i++) {
			if (syntaxClass.isAssignableFrom(ddlsyntaxes[i])) { // is parent of
				// create syntax
				Class<?> ddlSyntax = ddlsyntaxes[i];

				Class<?>[] params = { DBComponent.class };
				Constructor<?> constructor = ddlSyntax.getConstructor(params);

				Object[] initargs = { component };

				// assign syntax to component
				component.setDDLSQLSyntax((IDDLSQLSyntax) constructor
						.newInstance(initargs));
			}
		}
	}

	/**
	 * Get full URL of driver
	 * 
	 * @param URL
	 *          String
	 */
	public String[] getFullURL() {
		// separate variables of connection string
		String[] path = new String[9];
		String s;
		for (int i = 0; url[i] != null; i++) {
			s = url[i];
			if (s.charAt(0) == '.') {
				path[i] = new String(System.getProperty("user.dir").concat(
						s.substring(1)));
			} else {
				path[i] = new String(s);
			}
			path[i] = wrappedJAR(path[i]);
		}
		return path;
	}

	/**
	 * Creates path wrapped to the JAR file syntax
	 * 
	 * @param url
	 * @return String syntax
	 */
	protected String wrappedJAR(String url) {
		String path = "jar:file:///<user.dir>!/";
		StringTokenizer st = new StringTokenizer(path, "<>");
		StringBuffer sb = new StringBuffer();
		while (st.hasMoreElements()) {
			String token = st.nextToken();
			if (token.equals("user.dir")) {
				sb.append(url);
			} else {
				sb.append(token);
			}
		}
		return sb.toString();
	}

	/**
	 * Return connection string
	 * 
	 * @return coonnection as string
	 */
	public String getConnectionString() {
		return connectionString;
	}

	public String getDriverClassName() {
		return classnName;
	}

	/**
	 * Return driver name
	 * 
	 * @return name of driver
	 */
	public String getDriverName() {
		return driverName;
	}

	public String[] getURL() {
		return url;
	}

	/** XML Serialization **/

	public void xmlLoad(Node node) {
		Element driver = (Element) node;
		driverName = driver.getAttribute("name");
		NodeList nl = driver.getElementsByTagName("url");
		urlCount = nl.getLength();
		for (int i = 0; i < urlCount; i++) {
			url[i] = new String(nl.item(i).getFirstChild().getNodeValue());
		}

		classnName = JUIGLEDomParser.getTextFromElement(driver, "classname");
		connectionString = JUIGLEDomParser.getTextFromElement(driver, "connectionstring");
	}

	/**
	 * NOT IMPLEMENTED
	 */
	public Node xmlSave(Document doc) {
		// not implemented
		return null;
	}

	public int getDataTypeOfDatabase() {
		return -1;
	}

}

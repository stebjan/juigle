package ch.ethz.origo.juigle.data.database.driver;

import ch.ethz.origo.juigle.data.database.model.DBComponent;

/**
 * 
 * JDBC Driver description
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (2/28/2010)
 * @since 0.1.0 (2/28/2010)
 * @see Exception
 * 
 */
public interface IDriverDescription {

	/**
	 * Driver description name
	 * 
	 * @return String description
	 */
	public String getDriverName();

	/**
	 * Get driver url (e.g. path)
	 * 
	 * @return url String
	 */
	public String[] getURL();

	/**
	 * Get full driver url (full path, not relative)
	 * 
	 * @return url String
	 */
	public String[] getFullURL();

	/**
	 * Get driver class path
	 * 
	 * @return classpath String
	 */
	public String getDriverClassName();

	/**
	 * Get connection string Using keywords: database ... database name host ...
	 * host server port ... password
	 * 
	 * @return connection String
	 */
	public String getConnectionString();

	/**
	 * Return type of database
	 * 
	 * @return type int
	 */
	public int getDataTypeOfDatabase();

	/**
	 * Assign driver features to the DbComponent
	 * 
	 * @param component
	 *          DbComponent
	 * @throws Exception
	 */
	public void assignDriverFeatures(DBComponent component) throws Exception;

	/**
	 * Return default port of database
	 * 
	 * @return port int
	 */
	public int getDefaultPort();

}
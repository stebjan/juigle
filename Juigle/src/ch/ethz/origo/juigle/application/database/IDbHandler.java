package ch.ethz.origo.juigle.application.database;

import java.io.File;

/**
 * Interface for handling with database
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (1/24/2010)
 * @since 0.1.0 (1/24/2010)
 * 
 */
public interface IDbHandler {
	
	/**
	 * This method create a new <code>JERPA</code> EEG database 
	 * (if not exist) from <code>XML</code> file.
	 * 
	 * @param xmlFile file
	 */
	public void createDatabaseFromXML(File xmlFile);
	
	/**
	 * This method create a new database from SQL command.
	 *  
	 * @param command database SQL command
	 */
	public void createDatabase(String command);

}

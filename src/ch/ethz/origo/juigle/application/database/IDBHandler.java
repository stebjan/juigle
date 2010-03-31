package ch.ethz.origo.juigle.application.database;

import java.io.File;

import ch.ethz.origo.juigle.application.exception.database.DatabaseException;
import ch.ethz.origo.juigle.data.database.model.DBTable;
import ch.ethz.origo.juigle.data.database.model.Database;

/**
 * Interface for handling with database
 * 
 * @author Vaclav Souhrada
 * @version 0.2.0 (1/31/2010)
 * @since 0.1.0 (1/24/2010)
 * 
 */
public interface IDBHandler {
	
	/**
	 * This method create a new database 
	 * (if not exist) from <code>XML</code> file.
	 * 
	 * @param xmlFile file
	 */
	public void createDatabaseFromXML(File xmlFile) throws DatabaseException;
	
	/**
	 * This method create a new database by given name.
	 *  
	 * @param name of database
	 * @version 0.1.0 (3/31/2010)
	 * @since 0.1.0 (1/24/2010)
	 */
	public void createDatabase(String name) throws DatabaseException;

}

package ch.ethz.origo.juigle.application.database;

import java.util.List;

/**
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (1/24/2010)
 * @since 0.1.0 (1/24/2010)
 * 
 */
public interface IDatabaseAction {

	/**
	 * Execute database command such as INSERT, DELETE, UPDATE...
	 * 
	 * @param command database (SQL) command
	 * @version 0.1.0 (1/24/2010)
	 * @since 0.1.0 (1/24/2010)
	 */
	public void execute(String command);
	
	/**
	 * Execute database commands such as INSERT, DELETE, UPDATE...
	 * 
	 * @param commands list of SQL commands
	 * @version 0.1.0 (1/24/2010)
	 * @since 0.1.0 (1/24/2010)
	 * 
	 */
	public void execute(List<String> commands);
}

package ch.ethz.origo.juigle.data.database.model;

import java.util.ArrayList;
import java.util.List;

import ch.ethz.origo.juigle.data.database.DBUser;
import ch.ethz.origo.juigle.data.database.driver.IDriverDescription;
import ch.ethz.origo.juigle.data.database.model.ddl.DatabaseDDLSQLSyntax;

/**
 * 
 * @author Vaclav Souhrada
 * @version 0.1.1 (3/31/2010)
 * @since 0.1.0 (2/07/2010)
 * 
 */
public class Database extends DBComponent {
	
	private List<DBTable> listOfTables;
	
	private DBUser user;
	
	public Database(String name) {
		super(name, DBComponent.DATABASE_TYPE);
		listOfTables = new ArrayList<DBTable>();
	}
	
	public String getDatabaseName() {
		return getComponentName();
	}
	
	/**
	 * Add table to list of database tables
	 * 
	 * @param table which will be inserted to the list of all database tables
	 * @version 0.1.0 (3/31/2010)
	 * @since 0.1.1 (3/31/2010)
	 */
	public void addTable(DBTable table) {
		listOfTables.add(table);
	}
	
	/**
	 * Return list of tables
	 * 
	 * @return list of db tables
	 * @version 0.1.0 (3/31/2010)
	 * @since 0.1.1 (3/31/2010)
	 */
	public List<DBTable> getListOfTables() {
		return listOfTables;
	}

	@Override
	public Class<?> getDDLSQLSyntaxClass() {
		return DatabaseDDLSQLSyntax.class;
	}
	
	 /**
   * Get Wrapped table name
   * 
   * @return wrapped table name String
   */
  public String getWrappedDatabaseName() {
    return getWrappedCommand(getDatabaseName());
  }

	@Override
	public void useDriverDescription(IDriverDescription driver) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
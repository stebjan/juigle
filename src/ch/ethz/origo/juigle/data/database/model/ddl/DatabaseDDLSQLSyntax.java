package ch.ethz.origo.juigle.data.database.model.ddl;

import ch.ethz.origo.juigle.application.exception.database.SQLDDLException;
import ch.ethz.origo.juigle.data.database.model.DBComponent;
import ch.ethz.origo.juigle.data.database.model.Database;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/31/2010)
 * @since 0.1.0 (3/31/2010)
 * @see AbstractDDLSQLSyntax
 *
 */
public class DatabaseDDLSQLSyntax extends AbstractDDLSQLSyntax {
	
	private Database database;

	
	public DatabaseDDLSQLSyntax(DBComponent component) {
		super(component);
		if (component instanceof Database) {
			database = (Database) component;			
		}
		// FIXME throw exception if not corrent instanceof
	}

	@Override
	public String add() throws SQLDDLException {
		throw new SQLDDLException(new RuntimeException("THIS METHOD IS NOT IMPLEMENTED"));
	}

	@Override
	public String create() throws SQLDDLException {
		return "CREATE DATABASE " + database.getWrappedDatabaseName();
	}

	@Override
	public String drop() throws SQLDDLException {
		return "DROP DATABASE " + database.getWrappedDatabaseName();
	}

	@Override
	public String modify() throws SQLDDLException {
		throw new SQLDDLException(new RuntimeException("THIS METHOD IS NOT IMPLEMENTED"));
	}

}

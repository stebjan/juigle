package ch.ethz.origo.juigle.data.database.model.ddl;

import ch.ethz.origo.juigle.application.exception.database.SQLDDLException;
import ch.ethz.origo.juigle.data.database.model.DBComponent;
import ch.ethz.origo.juigle.data.database.model.DBTable;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/1/2010)
 * @since 0.1.0 (3/1/2010)
 * @see AbstractDDLSQLSyntax
 *
 */
public class TableDDLSQLSyntax extends AbstractDDLSQLSyntax {

	DBTable table;
	
	public TableDDLSQLSyntax(DBComponent component) {
		super(component);
		if (component instanceof DBTable) {
			this.table = (DBTable) component;
		}
	}

	@Override
	public String add() throws SQLDDLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String create() throws SQLDDLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String drop() throws SQLDDLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modify() throws SQLDDLException {
		// TODO Auto-generated method stub
		return null;
	}

}

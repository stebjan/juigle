package ch.ethz.origo.juigle.data.database.model.ddl;

import ch.ethz.origo.juigle.application.exception.database.SQLDDLException;
import ch.ethz.origo.juigle.data.database.model.DBColumn;
import ch.ethz.origo.juigle.data.database.model.DBComponent;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/1/2010)
 * @since 0.1.0 (3/1/2010)
 * @see AbstractDDLSQLSyntax
 *
 */
public class ColumnDDLSQLSyntax extends AbstractDDLSQLSyntax {
	
	private DBColumn column;

	public ColumnDDLSQLSyntax(DBComponent component) {
		super(component);
		this.column = (DBColumn) component;
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

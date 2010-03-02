package ch.ethz.origo.juigle.data.database.model.ddl;

import ch.ethz.origo.juigle.data.database.model.DBComponent;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/1/2010)
 * @since 0.1.0 (3/1/2010)
 * @see IDDLSQLSyntax
 */
public abstract class AbstractDDLSQLSyntax implements IDDLSQLSyntax {

	protected DBComponent component;
	
	public AbstractDDLSQLSyntax(DBComponent component) {
		this.component = component;
	}
}

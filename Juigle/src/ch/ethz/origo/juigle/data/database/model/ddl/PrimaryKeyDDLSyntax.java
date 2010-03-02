package ch.ethz.origo.juigle.data.database.model.ddl;

import ch.ethz.origo.juigle.data.database.model.DBComponent;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/02/2010)
 * @since 0.1.0 (3/02/2010)
 * @see ConstraintDDLSQLSyntax
 * 
 */
public class PrimaryKeyDDLSyntax extends ConstraintDDLSQLSyntax {

	public PrimaryKeyDDLSyntax(DBComponent component) {
		super(component);
	}

}

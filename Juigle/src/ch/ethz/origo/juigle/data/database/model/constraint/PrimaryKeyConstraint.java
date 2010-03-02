package ch.ethz.origo.juigle.data.database.model.constraint;

import ch.ethz.origo.juigle.data.database.model.DBTable;
import ch.ethz.origo.juigle.data.database.model.ddl.PrimaryKeyDDLSyntax;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/02/2010)
 * @since 0.1.0 (3/02/2010)
 * @see DBConstraint
 *
 */
public class PrimaryKeyConstraint extends DBConstraint {

	public PrimaryKeyConstraint(String name, DBTable ownerTable) {
		super(name, ownerTable);
	}
	
	public PrimaryKeyConstraint(DBTable ownerTable) {
		super(autoNameGenerator("PK", ownerTable.getComponentName()), ownerTable);
	}
	
	public Class<?> getDDLSQLSyntaxClass() {
		return PrimaryKeyDDLSyntax.class;
	}
	
	public String toString() {
    return "PRIMARY KEY " + super.toString() + " Owner: " + ownerTable.getComponentName();
  }

}

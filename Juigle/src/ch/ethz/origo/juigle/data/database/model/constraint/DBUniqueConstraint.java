package ch.ethz.origo.juigle.data.database.model.constraint;

import ch.ethz.origo.juigle.data.database.model.DBTable;
import ch.ethz.origo.juigle.data.database.model.ddl.UniqueConstraintDDLSQLSyntax;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/02/2010)
 * @since 0.1.0 (3/02/2010)
 * @see DBConstraint
 * 
 */
public class DBUniqueConstraint extends DBConstraint {

	public DBUniqueConstraint(String name, DBTable ownerTable) {
		super(name, ownerTable);
	}
	
	public DBUniqueConstraint(DBTable ownerTable) {
		this(autoNameGenerator("UK", ownerTable.getComponentName()), ownerTable);
	}

	public Class<?> getDDLSQLSyntaxClass() {
    return UniqueConstraintDDLSQLSyntax.class;
  }

  public String toString() {
    return "UNIQUE " + super.toString() + " Owner: " + ownerTable.getComponentName();
  }
}

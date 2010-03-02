package ch.ethz.origo.juigle.data.database.model.ddl;

import ch.ethz.origo.juigle.application.exception.database.SQLDDLException;
import ch.ethz.origo.juigle.data.database.model.DBComponent;
import ch.ethz.origo.juigle.data.database.model.constraint.DBUniqueConstraint;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/02/2010)
 * @since 0.1.0 (3/02/2010)
 * @see ConstraintDDLSQLSyntax
 */
public class UniqueConstraintDDLSQLSyntax extends ConstraintDDLSQLSyntax {

	protected DBUniqueConstraint constraint;
	
  public UniqueConstraintDDLSQLSyntax(DBComponent component) {
		super(component);
		if (component instanceof DBUniqueConstraint) {
			this.constraint = (DBUniqueConstraint) component;
		}
	}

	public String add() throws SQLDDLException {
    return "CONSTRAINT " + constraint.getWrappedContraintName() + " UNIQUE (" +
      assemblyColumnsToCommand(constraint.getColumns()) + ")";
  }

  public String drop() throws SQLDDLException {
    return "ALTER TABLE " + constraint.getOwnerTable().getWrappedTableName() + " DROP CONSTRAINT " +
      constraint.getWrappedContraintName();
  }

  public String create() throws SQLDDLException {
    return "ALTER TABLE " + constraint.getOwnerTable().getWrappedTableName() + " ADD " + add();
  }

  public String modify() throws SQLDDLException {
    throw new SQLDDLException("Constraint " + constraint.getComponentName() +
    " hasn't implemented modify SQL-Command.");
  }
}

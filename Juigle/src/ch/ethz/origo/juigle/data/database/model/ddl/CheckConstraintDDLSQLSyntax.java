package ch.ethz.origo.juigle.data.database.model.ddl;

import ch.ethz.origo.juigle.application.exception.database.SQLDDLException;
import ch.ethz.origo.juigle.data.database.model.DBComponent;
import ch.ethz.origo.juigle.data.database.model.constraint.DBCheckConstraint;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/02/2010)
 * @since 0.1.0 (3/02/2010)
 * @see ConstraintDDLSQLSyntax
 * 
 */
public class CheckConstraintDDLSQLSyntax extends ConstraintDDLSQLSyntax {

	protected DBCheckConstraint constraint;
	
	public CheckConstraintDDLSQLSyntax(DBComponent component) {
		super(component);
		if (component instanceof DBCheckConstraint) {
			this.constraint = (DBCheckConstraint) component;
		}
	}
	
  public String add() throws SQLDDLException {
    return "CONSTRAINT " + constraint.getWrappedContraintName() + " CHECK (" +
      constraint.getCondition() + ")";
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

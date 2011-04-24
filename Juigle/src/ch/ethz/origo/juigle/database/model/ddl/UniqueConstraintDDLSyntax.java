package ch.ethz.origo.juigle.database.model.ddl;

import ch.ethz.origo.juigle.context.exceptions.SQLDDLException;
import ch.ethz.origo.juigle.database.model.ADBComponent;
import ch.ethz.origo.juigle.database.model.constraint.DBUniqueConstraint;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see ConstraintDDLSyntax
 * @version 0.1.0 (1/23/2011)
 * @since 1.0.0 (1/23/2011)
 */
public class UniqueConstraintDDLSyntax extends ConstraintDDLSyntax {

  private DBUniqueConstraint constraint;

  public UniqueConstraintDDLSyntax(ADBComponent dbComponent) {
    super(dbComponent);
    if (dbComponent instanceof DBUniqueConstraint) {
      constraint = (DBUniqueConstraint) dbComponent;
    } // TODO reaction if not correct instance
  }

  @Override
  public String add() throws SQLDDLException {
    return "CONSTRAINT " + constraint.getWrappedConstraintName() + " UNIQUE ("
            + assemblyColumnsNamesToCommand(constraint.getListOfColumnsName())
            + ")";
  }

  @Override
  public String drop() throws SQLDDLException {
    return "ALTER TABLE " + constraint.getWrappedOwnerTableName() 
            + " DROP CONSTRAINT " + constraint.getWrappedConstraintName();
  }

  @Override
  public String create() throws SQLDDLException {
    return "ALTER TABLE " + constraint.getWrappedOwnerTableName()
            + " ADD " + add();
  }

  @Override
  public String modify() throws SQLDDLException {
    throw new SQLDDLException("Constraint " + constraint.getComponentName()
            + " hasn't implemented modify SQL-Command.");
  }
}

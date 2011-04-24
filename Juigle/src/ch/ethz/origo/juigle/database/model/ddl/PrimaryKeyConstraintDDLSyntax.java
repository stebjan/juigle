package ch.ethz.origo.juigle.database.model.ddl;

import ch.ethz.origo.juigle.context.exceptions.SQLDDLException;
import ch.ethz.origo.juigle.database.model.ADBComponent;
import ch.ethz.origo.juigle.database.model.constraint.DBPrimaryKeyConstraint;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see ConstraintDDLSyntax
 * @version 0.1.0 (1/22/2011)
 * @since 1.0.0 (1/22/2011)
 */
public class PrimaryKeyConstraintDDLSyntax extends ConstraintDDLSyntax {

  private DBPrimaryKeyConstraint constraint;

  public PrimaryKeyConstraintDDLSyntax(ADBComponent dbComponent) {
    super(dbComponent);
    if (dbComponent instanceof DBPrimaryKeyConstraint) {
      constraint = (DBPrimaryKeyConstraint) dbComponent;
    }// TODO - reaction if is not primary key :)
  }

  @Override
  public String add() throws SQLDDLException {
    return "CONSTRAINT " + constraint.getWrappedConstraintName() + " PRIMARY KEY (" +
      assemblyColumnsNamesToCommand(constraint.getListOfColumnsName()) + ")";
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
    throw new SQLDDLException("Constraint " + constraint.getComponentName() +
    " hasn't implemented modify SQL-Command.");
  }
}

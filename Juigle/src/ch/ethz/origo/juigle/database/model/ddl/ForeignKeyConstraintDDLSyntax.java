package ch.ethz.origo.juigle.database.model.ddl;

import java.util.List;

import ch.ethz.origo.juigle.context.exceptions.SQLDDLException;
import ch.ethz.origo.juigle.database.model.ADBComponent;
import ch.ethz.origo.juigle.database.model.FKColumnPair;
import ch.ethz.origo.juigle.database.model.constraint.DBForeignKeyConstraint;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see ConstraintDDLSyntax
 * @version 0.1.0 (1/23/2011)
 * @since 1.0.0 (1/23/2011)
 */
public class ForeignKeyConstraintDDLSyntax extends ConstraintDDLSyntax {

  private DBForeignKeyConstraint constraint;

  public ForeignKeyConstraintDDLSyntax(ADBComponent dbComponent) {
    super(dbComponent);
    constraint = (DBForeignKeyConstraint) dbComponent;
  }

  @Override
  public String add() throws SQLDDLException {
    List<FKColumnPair> listOfFKColumns = constraint.getListOfFKColumns();
    StringBuffer sb = new StringBuffer();
    sb.append("CONSTRAINT ").append(constraint.getWrappedConstraintName())
            .append(" FOREIGN KEY (");
    boolean first = true;
    for (int i = 0; i < constraint.getFKColumnCount(); i++) {
      if (!first) {
        sb.append(",");
      } else {
        first = false;
      }
      sb.append(constraint.getChildColumnName(i, true));
    }
    sb.append(")").append(" REFERENCES ").append(constraint.getWrappedParentTableName()).append(" (");

    first = true;
    for (int i = 0; i < constraint.getFKColumnCount(); i++) {
      if (!first) {
        sb.append(",");
      } else {
        first = false;
      }
      sb.append(constraint.getParentColumnName(i, true));
    }
    sb.append(")");

    return sb.toString();
  }

  @Override
  public String create() throws SQLDDLException {
    return "ALTER TABLE " + constraint.getWrappedOwnerTableName()
            + " ADD " + add();
  }

  @Override
  public String drop() throws SQLDDLException {
    return "ALTER TABLE " + constraint.getWrappedOwnerTableName()
            + " DROP CONSTRAINT " + constraint.getWrappedConstraintName();
  }

  @Override
  public String modify() throws SQLDDLException {
    throw new SQLDDLException("Constraint " + constraint.getComponentName()
            + " hasn't implemented modify SQL-Command.");
  }
}

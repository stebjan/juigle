package ch.ethz.origo.juigle.database.model.constraint;

import ch.ethz.origo.juigle.database.model.DBConstraint;
import ch.ethz.origo.juigle.database.model.ddl.PrimaryKeyConstraintDDLSyntax;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see DBConstraint
 * @version 0.1.0 (1/22/2011)
 * @since 1.0.0 (1/22/2011)
 */
public class DBPrimaryKeyConstraint extends DBConstraint {

  public DBPrimaryKeyConstraint(String constraintName, String tableName) {
    super(constraintName, tableName);
  }

  @Override
  public Class getDDLSQLSyntaxClass() {
    return PrimaryKeyConstraintDDLSyntax.class;
  }

  @Override
  public String toString() {
    return "PRIMARY KEY " + super.toString() + " Owner: " + getOwnerName();
  }

}

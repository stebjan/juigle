package ch.ethz.origo.juigle.database.model.constraint;

import ch.ethz.origo.juigle.database.model.DBConstraint;
import ch.ethz.origo.juigle.database.model.ddl.UniqueConstraintDDLSyntax;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see DBConstraint
 * @version 0.1.0 (1/23/2011)
 * @since 1.0.0 (1/23/2011)
 */
public class DBUniqueConstraint extends DBConstraint {
  

  public DBUniqueConstraint(String constraintName, String ownerName) {
    super(constraintName, ownerName);
  }

  @Override
  public Class<?> getDDLSQLSyntaxClass() {
    return UniqueConstraintDDLSyntax.class;
  }

  @Override
  public String toString() {
    return "UNIQUE " + super.toString() + " Owner: " + getOwnerName();
  }
}

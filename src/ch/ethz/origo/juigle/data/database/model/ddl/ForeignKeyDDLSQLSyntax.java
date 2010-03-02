package ch.ethz.origo.juigle.data.database.model.ddl;

import ch.ethz.origo.juigle.application.exception.database.SQLDDLException;
import ch.ethz.origo.juigle.data.database.model.DBComponent;
import ch.ethz.origo.juigle.data.database.model.constraint.ForeignKeyConstraint;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/02/2010)
 * @since 0.1.0 (3/02/2010)
 * @see ConstraintDDLSQLSyntax
 * 
 */
public class ForeignKeyDDLSQLSyntax extends ConstraintDDLSQLSyntax {
	
	ForeignKeyConstraint fkConstraint;

	public ForeignKeyDDLSQLSyntax(DBComponent component) {
		super(component);
		if (component instanceof ForeignKeyConstraint) {
			fkConstraint = (ForeignKeyConstraint) component;			
		}
	}
	
	public String add() throws SQLDDLException {
    StringBuffer sb = new StringBuffer();
    sb.append("CONSTRAINT ").append(fkConstraint.getWrappedContraintName()).append(" FOREIGN KEY (");
    boolean first = true;
    for (int i = 0; i < fkConstraint.getFkColumnCount(); i++) {
      if (!first) {
        sb.append(",");
      } else {
        first = false;
      }
      sb.append(fkConstraint.getFKownerColumn(i).getWrappedName());
    }
    sb.append(")").append(" REFERENCES ").append(fkConstraint.getTargetTable().getWrappedTableName()).append(" (");

    first = true;
    for (int i = 0; i < fkConstraint.getPkColumnCount(); i++) {
      if (!first) {
        sb.append(",");
      } else {
        first = false;
      }
      sb.append(fkConstraint.getPKtargetColumn(i).getWrappedName());
    }
    sb.append(")");

    return sb.toString();
  }

  public String create() throws SQLDDLException {
    return "ALTER TABLE " + fkConstraint.getOwnerTable().getWrappedTableName() + " ADD " + add();
  }

  public String drop() throws SQLDDLException {
    return "ALTER TABLE " + fkConstraint.getOwnerTable().getWrappedTableName() + " DROP CONSTRAINT " +
    fkConstraint.getWrappedContraintName();
  }

  public String modify() throws SQLDDLException {
    throw new SQLDDLException("Constraint " + fkConstraint.getComponentName() +
    " hasn't implemented modify SQL-Command.");
  }

}

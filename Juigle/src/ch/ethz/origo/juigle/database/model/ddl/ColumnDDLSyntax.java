package ch.ethz.origo.juigle.database.model.ddl;

import ch.ethz.origo.juigle.context.exceptions.SQLDDLException;
import ch.ethz.origo.juigle.database.model.ADBComponent;
import ch.ethz.origo.juigle.database.model.DBColumn;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see ADDLSQLSyntax
 * @version 0.1.0 (1/23/2011)
 * @since 1.0.0 (1/23/2011)
 */
public class ColumnDDLSyntax extends ADDLSQLSyntax {

  private DBColumn column;

  public ColumnDDLSyntax(ADBComponent dbComponent) {
    super(dbComponent);
    if (dbComponent instanceof DBColumn) {
      this.column = (DBColumn) dbComponent;
    } // TODO reaction if instance is not correct
  }

  @Override
  public String create() throws SQLDDLException {
    StringBuffer query = new StringBuffer(column.getWrappedColumnName() + " "
            + column.getSQLType().getSyntax());
    query.append(column.getNullInfo());

    return query.toString();
  }

  @Override
  public String drop() throws SQLDDLException {
    throw new SQLDDLException("Column " + column.getComponentName()
            + " hasn't implemented drop SQL-Command.");
  }

  @Override
  public String add() throws SQLDDLException {
    if (column.getOwnerTableName() != null) {
      return "ALTER TABLE " + column.getWrappedOwnerTableName()
              + " ADD " + create();
    } else {
      throw new SQLDDLException("Column " + column.getComponentName()
              + " has not owner's table.");
    }
  }

  @Override
  public String modify() throws SQLDDLException {
    if (column.getOwnerTableName() != null) {
      return "ALTER TABLE " + column.getWrappedOwnerTableName() + " MODIFY "
              + create();
    } else {
      throw new SQLDDLException("Column " + column.getComponentName()
              + " has not owner's table.");
    }
  }
}

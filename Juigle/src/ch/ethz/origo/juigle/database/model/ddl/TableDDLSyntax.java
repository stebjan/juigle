package ch.ethz.origo.juigle.database.model.ddl;

import java.util.List;

import ch.ethz.origo.juigle.context.exceptions.SQLDDLException;
import ch.ethz.origo.juigle.database.model.ADBComponent;
import ch.ethz.origo.juigle.database.model.DBColumn;
import ch.ethz.origo.juigle.database.model.DBConstraint;
import ch.ethz.origo.juigle.database.model.DBTable;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see ADDLSQLSyntax
 * @version 0.1.0 (1/23/2011)
 * @since 1.0.0 (1/23/2011)
 */
public class TableDDLSyntax extends ADDLSQLSyntax {

  private DBTable table;

  public TableDDLSyntax(ADBComponent dbComponent) {
    super(dbComponent);
    if (dbComponent instanceof DBTable) {
      table = (DBTable) dbComponent;
    } // TODO reaction if not instance correct
  }

  /**
   *
   * 
   * @version 0.1.0 (1/23/2011)
   * @since 0.1.0 (1/23/2011)
   * @return
   * @throws SQLDDLException
   */
  public String prepareColumnsCommand() throws SQLDDLException {
    StringBuffer sb = new StringBuffer();
    List<DBColumn> listOfColumns = table.getColumnsList();
    int size = listOfColumns.size();
    int commaChar = size - 1;
    for (int i = 0; i < size; i++) {
      sb.append(listOfColumns.get(i).create());
      if (i < commaChar) {
        sb.append(", ");
      }
    }

    return sb.toString();
  }

  /**
   *
   *
   * @version 0.1.0 (1/23/2011)
   * @since 0.1.0 (1/23/2011)
   * @return
   * @throws SQLDDLException
   */
  public String prepareConstraintsCommand() throws SQLDDLException {
    StringBuffer sb = new StringBuffer();
    List<DBConstraint> listOfColumns = table.getConstraintsList();
    int size = listOfColumns.size();
    int commaChar = size - 1;
    for (int i = 0; i < size; i++) {
      sb.append(listOfColumns.get(i).add());
      if (i < commaChar) {
        sb.append(", ");
      }
    }

    return sb.toString();
  }

  @Override
  public String create() throws SQLDDLException {
    // prepare columns
    String cols = prepareColumnsCommand();
    // prepare contraints
    String cons = prepareConstraintsCommand();
    // assembly
    StringBuffer command = new StringBuffer();
    command.append(cols);
    if (cons.length() > 0) {
      command.append(", " + cons);
    }
    // create table query
    return "CREATE TABLE " + table.getWrappedTableName()
            + " (" + command.toString() + ")";
  }

  @Override
  public String drop() throws SQLDDLException {
    return "DROP TABLE " + table.getWrappedTableName();
  }

  @Override
  public String add() throws SQLDDLException {
    throw new SQLDDLException("Table " + table.getComponentName()
            + " hasn't implemented addition SQL-Command. "
            + " You have to apply command to column, or another substructure.");
  }

  @Override
  public String modify() throws SQLDDLException {
    throw new SQLDDLException("Table " + table.getComponentName()
            + " hasn't implemented modification SQL-Command. "
            + " You have to apply command to column, index, or another substructure.");
  }
}

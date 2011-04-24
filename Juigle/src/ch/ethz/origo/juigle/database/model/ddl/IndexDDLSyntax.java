package ch.ethz.origo.juigle.database.model.ddl;

import java.util.List;

import ch.ethz.origo.juigle.context.exceptions.SQLDDLException;
import ch.ethz.origo.juigle.database.model.ADBComponent;
import ch.ethz.origo.juigle.database.model.DBIndex;
import ch.ethz.origo.juigle.database.model.DBIndexColumn;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see ADDLSQLSyntax
 * @version 0.1.0 (1/22/2011)
 * @since 1.0.0 (1/22/2011)
 */
public class IndexDDLSyntax extends ADDLSQLSyntax {

  private DBIndex index;

  public IndexDDLSyntax(ADBComponent dbComponent, String ownerTableName) {
    super(dbComponent);
    index = new DBIndex(dbComponent.getComponentName(), ownerTableName);
  }

  /**
   * Assembly columns and order to this form col1 DESC, col2 ASC, col3, ...atd.
   * orders: ASC is true, DESC is false (default is ASC)
   *
   * @param listOfColumns
   * @version 0.1.0 (1/22/2011)
   * @since 0.1.0 (1/22/2011)
   * @return command String
   */
  protected String assemblyColumnsToCommand(List<DBIndexColumn> listOfColumns) {
    StringBuffer command = new StringBuffer();
    int size = listOfColumns.size();
    int indexComma = size - 1;
    for (int i = 0; i < size; i++) {
      DBIndexColumn column = listOfColumns.get(i);
      command.append(index.getWrappedColumnName(column.getName()));
      if (!column.isOrder()) { // desc order
        command.append(" DESC");
      }
      if (i < indexComma) {
        command.append(", ");
      }
    }
    return command.toString();
  }

  @Override
 public String create() throws SQLDDLException {
    return "CREATE INDEX " + index.getWrappedIndexName() + " ON " +
      index.getWrappedTableName() + " (" +
      assemblyColumnsToCommand(index.getListOfColumns()) + ")";
  }

  @Override
  public String drop() throws SQLDDLException {
    return "DROP INDEX " + index.getWrappedIndexName();
  }

  @Override
  public String add() throws SQLDDLException {
    throw new SQLDDLException("Index " + index.getComponentName() +
      " hasn't implemented add SQL-Command.");
  }

  @Override
  public String modify() throws SQLDDLException {
    throw new SQLDDLException("Index " + index.getComponentName() +
      " hasn't implemented modify SQL-Command.");
  }

}

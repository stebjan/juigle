package ch.ethz.origo.juigle.database.model.constraint;

import java.util.ArrayList;
import java.util.List;

import ch.ethz.origo.juigle.database.model.DBConstraint;
import ch.ethz.origo.juigle.database.model.FKColumnPair;
import ch.ethz.origo.juigle.database.model.ddl.ForeignKeyConstraintDDLSyntax;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see DBConstraint
 * @version 0.1.0 (1/23/2011)
 * @since 1.0.0 (1/23/2011)
 */
public class DBForeignKeyConstraint extends DBConstraint {

  private String parentTableName;
  private List<FKColumnPair> listOfFKColumns;

  public DBForeignKeyConstraint(String constraintName, String childTableName,
          String parentTableName) {
    super(constraintName, childTableName);
    this.parentTableName = parentTableName;
    listOfFKColumns = new ArrayList<FKColumnPair>();
  }

  @Override
  public Class<?> getDDLSQLSyntaxClass() {
    return ForeignKeyConstraintDDLSyntax.class;
  }

  @Deprecated
  @Override
  public void addColumnName(String columnName) {
    throw new UnsupportedOperationException("Not supported for FK Constraint");
  }

  @Override
  protected String getWrappedCommand(String command) {
    return super.getWrappedCommand(command);
  }

  public String getWrappedParentTableName() {
    return getWrappedCommand(parentTableName);
  }

  public String getParentTableName() {
    return parentTableName;
  }

  public String getChildTableName() {
    return getComponentName();
  }

  public void addFKColumnPair(FKColumnPair pair) {
    listOfFKColumns.add(pair);
  }

  public void addFKColumnPair(String childColumnName, String parentColumnName) {
    listOfFKColumns.add(new FKColumnPair(childColumnName, parentTableName));
  }

  /**
   * Gets count of columns, which are linked between FK column(s) and PK column(s).
   *
   * @return Count of column.
   */
  public int getFKColumnCount() {
    return listOfFKColumns.size();
  }

  public String getChildColumnName(int index, boolean wrapped) {
    if (wrapped) {
      return getWrappedCommand(listOfFKColumns.get(index).getChildName());
    }

    return listOfFKColumns.get(index).getChildName();
  }

  public String getParentColumnName(int index, boolean wrapped) {
    if (wrapped) {
      return getWrappedCommand(listOfFKColumns.get(index).getParentName());
    }

    return listOfFKColumns.get(index).getParentName();
  }

  public List<FKColumnPair> getListOfFKColumns() {
    return listOfFKColumns;
  }
}

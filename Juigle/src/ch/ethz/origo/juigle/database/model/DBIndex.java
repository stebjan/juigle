package ch.ethz.origo.juigle.database.model;

import java.util.ArrayList;
import java.util.List;

import ch.ethz.origo.juigle.database.model.ddl.IndexDDLSyntax;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see ADBComponent
 * @version 0.1.0 (1/22/2011)
 * @since 1.0.0 (1/22/2011)
 */
public class DBIndex extends ADBComponent {

    private String ownerName;
      /** list of columns **/
  protected List<DBIndexColumn> listOfColumns;

  public DBIndex(String name, String ownerName) {
    super(name, ADBComponent.INDEX_COMPONENT_TYPE);
    this.ownerName = ownerName;
    listOfColumns = new ArrayList<DBIndexColumn>();
  }

  @Override
  public Class<?> getDDLSQLSyntaxClass() {
    return IndexDDLSyntax.class;
  }

  public void addColumn(DBIndexColumn column) {
    listOfColumns.add(column);
  }

  public void addColumn(String columnName, boolean orderASC) {
    listOfColumns.add(new DBIndexColumn(name, orderASC));
  }

  public List<DBIndexColumn> getListOfColumns() {
    return listOfColumns;
  }

  public String getWrappedIndexName() {
    return getWrappedCommand(name);
  }

  public String getWrappedTableName() {
    return getWrappedCommand(ownerName);
  }

   public String getWrappedColumnName(String columnName) {
    return getWrappedCommand(columnName);
  }

  @Override
  public String toString() {
    return "DBIndex{ " + "ownerName=" + ownerName + ", listOfColumns="
            + listOfColumns + " } " + super.toString();
  }



}

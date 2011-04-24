package ch.ethz.origo.juigle.database.dbi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ch.ethz.origo.juigle.context.exceptions.DatabaseException;
import ch.ethz.origo.juigle.context.exceptions.DbiException;

/**
 *
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see IDbiTable
 * @version 2.0.1 (2/24/2011)
 * @since 1.0.0 (1/8/2011)
 */
public abstract class ADbiTable implements IDbiTable {

  /** Logger for this class */
  private static final Logger logger = Logger.getLogger(ADbiTable.class);
  private String tableName;
  protected Map<String, DbiMethodPair> columnsMap;
  private List<ADbiRow> listOfRows;

  /**
   *
   * 
   * @version 0.1.0 (1/30/2011)
   * @since 0.2.0 (1/30/2011)
   * @param tableName
   */
  public ADbiTable(String tableName) {
    this.tableName = tableName;
    columnsMap = new HashMap<String, DbiMethodPair>();
    listOfRows = new ArrayList<ADbiRow>();
  }

  /**
   * By this method are mapped columns into columns map. This map is used for
   * filling row by <code>ResultSet</code>. Method is calling from class
   * constructor.
   *
   * @version 0.1.0 (2/11/2011)
   * @since 1.0.0 (2/11/2011)
   */
  public abstract void mapColumns();

  /**
   *
   * 
   * @version 2.1.0 (2/24/2011)
   * @since 0.2.1 (2/06/2011)
   * @return 
   */
  public abstract <R extends ADbiRow> R createDbiRow();

  @Override
  public String getTableName() {
    return tableName;
  }

  /**
   *
   *
   * @version 0.1.0 (1/8/2011)
   * @since 0.1.0 (1/8/2011)
   * @param row
   */
  public void addRow(ADbiRow row) {
    if (columnsMap.isEmpty()) {
      mapColumns();
    }
    row.setColumnsMap(columnsMap);
    listOfRows.add(row);
  }

  /**
   *
   *
   * @version 0.1.0 (1/8/2011)
   * @since 0.1.0 (1/8/2011)
   * @param row
   */
  public void removeRow(ADbiRow row) {
    listOfRows.remove(row);
  }

  /**
   *
   * 
   * @version 0.1.0 (1/8/2011)
   * @since 0.1.0 (1/8/2011)
   */
  public void removeAllRows() {
    if (listOfRows.size() > 0) {
      listOfRows.clear();
    }
  }

  /**
   * Return number of rows in current table instance. So count is depend on type
   * of SQL select/update/insert.
   *
   * @version 0.1.0 (1/30/2011)
   * @since 0.1.1 (1/30/2011)
   * @return number of rows in current table instance.
   */
  public int getCount() {
    return listOfRows.size();
  }

  /**
   *
   * 
   * @version 1.0.0 (2/11/2011)
   * @since 1.0.0 (2/11/2011)
   * 
   * @return
   */
  public List<? extends ADbiRow> getListOfRows() {
    return listOfRows;
  }

  /**
   *
   * @version 0.1.0 (2/06/2011)
   * @since 1.0.0 (2/11/2011)
   * @return
   */
  public int getColumnsCount() {
    if (columnsMap.isEmpty()) {
      mapColumns();
    }
    return columnsMap.size();
  }

  /**
   *
   * @version 0.1.0 (2/06/2011)
   * @since 1.0.0 (2/11/2011)
   * @return
   */
  public String[] getColumnsName() {
    if (columnsMap.isEmpty()) {
      mapColumns();
    }
    return columnsMap.keySet().toArray(new String[columnsMap.size()]);
  }

  /**
   *
   * 
   * @version 1.0.0 (2/13/2011)
   * @since 1.1.0 (2/13/2011)
   * @return
   */
  public List<String> getListOfColumnsName() {
    return Arrays.asList(getColumnsName());
  }

  /**
   *
   * 
   * @version 1.0.0 (2/13/2011)
   * @since 1.0.0 (2/11/2011)
   * @return
   */
  public String getColumnsSeparatedByComma() {
    if (columnsMap.isEmpty()) {
      mapColumns();
    }
    StringBuilder sb = new StringBuilder();
    String[] columns = getColumnsName();
    int insComma = getColumnsCount() - 1;
    for (int i = 0; i < getColumnsCount(); i++) {
      sb.append(columns[i]);
      if (i < insComma) {
        sb.append(", ");
      }
    }

    return sb.toString();
  }

  /**
   * 
   *
   * @version 0.1.0 (2/13/2011)
   * @since 1.1.0 (2/13/2011)
   * @return
   */
  public Map<String, DbiMethodPair> getColumnsMap() {
    return columnsMap;
  }

  @Override
  public boolean isEmpty() {
    if (getCount() > 0) {
      return false;
    }

    return true;
  }

  /**
   *
   * 
   * @param columns
   * @param rs
   * @throws DatabaseException
   * @version 1.0.0 (2/13/2011)
   * @since 0.2.1 (2/06/2011)
   */
  public void fillTable(List<String> columns, ResultSet rs)
          throws DatabaseException {
    if (columnsMap.isEmpty()) {
      mapColumns();
    }
    if (rs != null) {
      try {
        while (rs.next()) {
          ADbiRow row = createDbiRow();
          row.fillRow(columns, rs);
          if (row != null) {
            addRow(row);
          }
        }
      } catch (DbiException ex) {
        String msg = "Can not fill dbi row...";
        logger.error(msg, ex);
        throw new DatabaseException(msg, ex);
      } catch (SQLException ex) {
        logger.error(ex);
        throw new DatabaseException(ex);
      }
    }
  }
}

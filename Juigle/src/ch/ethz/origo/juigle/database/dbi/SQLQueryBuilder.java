package ch.ethz.origo.juigle.database.dbi;

import java.util.List;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see ADbiTable
 * @see ADbiRow
 * @version 0.4.1 (2/28/2011)
 * @since 1.0.0 (2/06/2011)
 */
public class SQLQueryBuilder<T extends ADbiTable, R extends ADbiRow> {

  private static final String CLAUSE_SELECT = "select";
  private static final String CLAUSE_FROM = "from";
  //
  private ColumnClause<String> columns = null;
  protected WhereClause whereClause = null;
  //
  protected T table;
  protected R row;

  @Deprecated
  public SQLQueryBuilder(T table, R row) {
    this.table = table;
    this.row = row;
  }

  public SQLQueryBuilder(T table) {
    this.table = table;
    this.row = (R)table.createDbiRow();
  }

  /**
   *
   * @since 1.0.0 (2/06/2011)
   * @return
   */
  public SQLQuery getSQLQuery() {
    StringBuilder sb = new StringBuilder();
    sb.append(CLAUSE_SELECT);
    sb.append(" ");
    if (hasColumns()) {
      sb.append(columns.getColumnsSeparatedByComma());
    } else {
      sb.append(table.getColumnsSeparatedByComma());
    }
    sb.append(" ");
    sb.append(CLAUSE_FROM);
    sb.append(" ");
    sb.append(table.getTableName());
    // join where clause if exist
    if (hasCondition()) {
      sb.append(" ");
      sb.append(whereClause.getWhereClause());
    }
    // create sql query
    SQLQuery query = new SQLQuery();
    query.setContainsSelectClause(true);
    query.setQueryString(sb.toString());

    return query;
  }

  /**
   *
   * @param block
   * 
   * @since 0.3.0 (2/20/2011)
   * @return
   */
  public WhereClause appendCondition(Block block) {
    if (whereClause == null) {
      whereClause = new WhereClause();
    }
    whereClause.and(block);

    return whereClause;
  }

  /**
   *
   *
   * @since 0.2.0 (2/13/2011)
   * @param columnName
   */
  public void appendColumn(String columnName) {
    if (columns == null) {
      columns = new ColumnClause<String>();
    }
    columns.add(columnName);
  }

  /**
   * Return true, if <code>SQL - Query</code> has <ode>WHERE</code> clause. It's
   * mean that contain conditions which are wrapped into <ode>WHERE</code> clause.
   * Else return false.
   *
   * @since 0.1.0 (2/06/2011)
   * @return true, if <code>SQL - Query</code> has <ode>WHERE</code> clause. It's
   * mean that contain conditions which are wrapped into <ode>WHERE</code> clause.
   * Else return false.
   */
  protected boolean hasCondition() {
    if (whereClause != null) {
      return true;
    }

    return false;
  }

  /**
   * 
   *
   * @since 0.2.0 (2/13/2011)
   * @return
   */
  protected boolean hasColumns() {
    if (columns != null && columns.size() > 0) {
      return true;
    }

    return false;
  }

  protected String getConditionAsString() {
    if (hasCondition()) {
      return whereClause.toString();
    }

    return null;
  }

  public R getRow() {
    return row;
  }

  public T getTable() {
    return table;
  }

  /**
   *
   * 
   * @version 1.0.0 (2/13/2011)
   * @since 0.2.0 (2/13/2011)
   * @return
   */
  public List<String> getColumnsList() {
    if (hasColumns()) {
      return columns;
    } else {
      return table.getListOfColumnsName();
    }
  }
}

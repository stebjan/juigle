package ch.ethz.origo.juigle.database.dbi;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see SQLQueryBuilder
 * @version 0.3.0 (2/28/2011)
 * @since 1.0.0 (2/06/2011)
 */
public class UpdateSQLQueryBuilder<T extends ADbiTable, R extends ADbiRow>
        extends SQLQueryBuilder<T, R> {

  private static final String CLAUSE_UPDATE = "update";
  private static final String CLAUSE_SET = "set";

  public UpdateSQLQueryBuilder(T table) {
    super(table);
  }

  /**
   *
   *
   * @version 0.2.1 (2/24/2011)
   * @since 1.0.0 (2/06/2011)
   * @return
   */
  @Override
  public SQLQuery getSQLQuery() {
    StringBuilder sb = new StringBuilder();
    sb.append(CLAUSE_UPDATE);
    sb.append(" ");
    sb.append(table.getTableName());
    sb.append(" ");
    sb.append(CLAUSE_SET);

    if (hasColumns()) {
      // FIXME, umoznit jen set pro urcite sloupce
      //sb.append(columns.getColumnsSeparatedByComma());
    } else {
      int comma = table.getColumnsCount() - 1;
      String[] columnName = table.getColumnsName();
      for (int i = 0; i < table.getColumnsCount(); i++) {
        sb.append(" ");
        sb.append(columnName[i]);
        sb.append("=");
        sb.append("?");
        if (i < comma) {
          sb.append(",");
        }
      }
    }
    // join where clause if exist
    if (hasCondition()) {
      sb.append(" ");
      sb.append(whereClause.getWhereClause());
    }
    // create sql query
    SQLQuery query = new SQLQuery();
    query.setContainsUpdateClause(true);
    query.setQueryString(sb.toString());

    return query;
  }
}

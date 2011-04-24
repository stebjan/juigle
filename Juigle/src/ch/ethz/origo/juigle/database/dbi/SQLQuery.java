package ch.ethz.origo.juigle.database.dbi;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The <code>SQLQuery</code> class represents
 * SQL query strings and its parameter values.
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see Serializable
 * @version 0.1.3 (3/6/2011)
 * @since 1.0.0 (1/22/2011)
 */
public class SQLQuery implements Serializable {

  private static final long serialVersionUID = -2851351898597894139L;
  
  private String query;
  private List<Object> values = new ArrayList<Object>();   // question
  
  private int sqlTypes[];
  
  private Boolean containsSelectClause;
  private Boolean containsUpdateClause;

  /**
   * The Constructor of Class SQLQuery
   * @param query SQL query string
   */
  public SQLQuery(String query) {
    this(query, null);
  }

  public SQLQuery(String query, int sqlTypes[]) {
    this.query = query;
    this.sqlTypes = sqlTypes;
  }

  public SQLQuery() {
    this(null, null);
  }

  public String getQueryString() {
    return query;
  }

  public void setQueryString(String query) {
    this.query = query;
  }

  /**
   * Allows to manually set, if the query contains SELECT clause.
   * If it's not defined, auto detection will be used.
   */
  public void setContainsSelectClause(Boolean containsSelectClause) {
    this.containsSelectClause = containsSelectClause;
  }

  /**
   * Allows to manually set, if the query contains UPDATE clause.
   * If it's not defined, auto detection will be used.
   * 
   * @version 0.1.0 (2/06/2011)
   * @since 0.1.1 (2/06/2011)
   */
  public void setContainsUpdateClause(Boolean containsUpdateClause) {
    this.containsUpdateClause = containsUpdateClause;
  }

  /**
   * @return
   * boolean value, if query string wrapped by this {@link SQLQuery} contains
   * SELECT clause
   * @see SQLQuery#setContainsSelectClause(Boolean)
   */
  public boolean containsSelectClause() {
    return containsSelectClause == null
            ? query != null ? query.regionMatches(true, 0, "select", 0, 6) 
            : false : containsSelectClause;
  }

  /**
   * 
   * boolean value, if query string wrapped by this {@link SQLQuery} contains
   * UPDATE clause
   * 
   * @version 0.1.0 (2/06/2011)
   * @since 0.1.1 (2/06/2011)
   * @see SQLQuery#setContainsUpdateClause(Boolean)
   * @return
   */
  public boolean containsUpdateClause() {
    return containsUpdateClause == null
            ? query != null ? query.regionMatches(true, 0, "update", 0, 6)
            : false : containsUpdateClause;
  }

  public int[] getSQLTypes() {
    return sqlTypes;
  }

  public void setSQLTypes(int sqlTypes[]) {
    this.sqlTypes = sqlTypes;
  }

  /**
   * @param index column index
   * @return parameter value specified by
   */
  public Object getValue(int index) {
    return values.get(index);
  }

  /** @return parameter count */
  public int getValueCount() {
    return values.size();
  }

  /** clears all parameter values */
  public void clearValues() {
    values.clear();
  }

  /**
   * adds parameter value
   * @param value parameter value
   */
  public void addValue(Object value) {
    values.add(value);
  }

  public final int fillStatement(PreparedStatement stmt) throws SQLException {
    return fillStatement(stmt, 1);
  }

  public int fillStatement(PreparedStatement stmt, int fromIndex)
          throws SQLException {
    int j = fromIndex;
    for (int i = 0; i < values.size(); i++) {
      Object obj = values.get(i);
      int sqlType = getSQLType(i);
      if (obj != null) {
        if (obj instanceof String) {
          stmt.setString(j, String.valueOf(obj));
        } else {
          stmt.setObject(j, obj);
        }
      } else {
        stmt.setNull(j, sqlType);
      }
      j++;
    }
    return j;
  }

  public int getSQLType(int i) {
    if (sqlTypes != null && sqlTypes.length > i) {
      return sqlTypes[i];
    }
    return -1;
  }

  @Override
  public String toString() {
    int count = getValueCount();
    StringBuilder buf = new StringBuilder(query.length() + 10 * count);
    buf.append(query);
    buf.append(": ");
    for (int i = 0; i < count; i++) {
      if (i > 0) {
        buf.append(", ");
      }
      buf.append(getValue(i));
    }
    return buf.toString();
  }
}

/*
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

/*
 *  
 *    Copyright (C) 2009 - 2011 
 *    							University of West Bohemia, 
 *                  Department of Computer Science and Engineering, 
 *                  Pilsen, Czech Republic
 */
package ch.ethz.origo.juigle.database;

import java.sql.ResultSet;

import org.apache.log4j.Logger;

import ch.ethz.origo.juigle.context.exceptions.DatabaseException;
import ch.ethz.origo.juigle.context.exceptions.DbiException;
import ch.ethz.origo.juigle.database.dbi.ADbiRow;
import ch.ethz.origo.juigle.database.dbi.ADbiTable;
import ch.ethz.origo.juigle.database.dbi.SQLQuery;
import ch.ethz.origo.juigle.database.dbi.SQLQueryBuilder;
import ch.ethz.origo.juigle.database.dbi.UpdateSQLQueryBuilder;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 2.5.0 (3/12/2011)
 * @since 1.0.0 (1/22/2011)
 */
public class DataAccessObject {

  /** Logger for this class */
  private static final Logger logger = Logger.getLogger(DataAccessObject.class);
  /** Database Manager */
  private DatabaseManager dbManager;

  /**
   * Default constructor. Inside creating instance of
   * <code>DatabaseManager</code>.
   */
  public DataAccessObject() {
    dbManager = DatabaseManager.getInstance();
  }

  /**
   *
   * @param row
   * @param table
   *
   * @version 1.0.0 (1/22/2011)
   * @since 1.0.0 (1/22/2011)
   * @throws DatabaseException
   */
  protected void insertRow(ADbiRow row, ADbiTable table) throws DatabaseException {
    table.addRow(row);
    insertTable(table);
  }

  /**
   *
   * @param table
   *
   * @version 1.0.1 (3/6/2011)
   * @since 1.0.0 (1/22/2011)
   * @throws DatabaseException
   */
  protected void insertTable(ADbiTable table) throws DatabaseException {
    if (!table.isEmpty()) {
      StringBuilder sb = new StringBuilder();
      sb.append("INSERT INTO ");
      sb.append(table.getTableName());
      sb.append("(").append(table.getColumnsSeparatedByComma()).append(")");
      sb.append(" VALUES(");
      int comma = table.getColumnsCount() - 1;
      for (int i = 0; i < table.getColumnsCount(); i++) {
        sb.append("?");
        if (i < comma) {
          sb.append(",");
        }
      }
      sb.append(");");
      for (ADbiRow row : table.getListOfRows()) {
        // now create SQLQuery and add values
        SQLQuery query = new SQLQuery(sb.toString());
        try {
          for (Object value : row.getRowValues()) {
            query.addValue(value);
          }
          IDatabase database = dbManager.getCurrentDatabase();
          database.executeQuery(query);
        } catch (DbiException ex) {
          String msg = "Can not create SQLQuery for insert table";
          logger.error(msg, ex);
          throw new DatabaseException(msg, ex);
        } catch (DatabaseException ex) {
          logger.error(ex.getMessage(), ex);
          throw new DatabaseException(ex.getMessage(), ex);
        }
      }
    }
  }

  /**
   *
   *
   * @param row
   * @param table
   *
   * @version 1.0.0 (1/22/2011)
   * @since 1.0.0 (1/22/2011)
   * @return
   * @throws DatabaseException
   */
  protected int updateRow(ADbiRow row, ADbiTable table) throws DatabaseException {
    table.addRow(row);
    return updateTable(table);
  }

  /**
   *
   * @param table
   * @version 1.2.0 (3/6/2011)
   * @since 1.0.0 (1/22/2011)
   * @return
   * @throws DatabaseException
   */
  protected int updateTable(ADbiTable table) throws DatabaseException {
    int updated = 0;
    if (!table.isEmpty()) {
      StringBuilder sb = new StringBuilder();
      sb.append("UPDATE ");
      sb.append(table.getTableName());
      sb.append(" SET");
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
      sb.append(";");

      IDatabase database = dbManager.getCurrentDatabase();
      for (ADbiRow row : table.getListOfRows()) {
        // now create SQLQuery and add values
        SQLQuery query = new SQLQuery(sb.toString());
        try {
          for (Object value : row.getRowValues()) {
            query.addValue(value);
          }
          updated += database.executeQuery(query);
        } catch (DbiException ex) {
          String msg = "Can not create SQLQuery for update table";
          logger.error(msg, ex);
          throw new DatabaseException(msg, ex);
        } catch (DatabaseException ex) {
          logger.error(ex.getMessage(), ex);
          throw new DatabaseException(ex.getMessage(), ex);
        }
      }
    }

    return updated;
  }

  protected int deleteFromTable(ADbiRow row, ADbiTable table) {
    table.addRow(row);
    return deleteFromTable(table);
  }

  protected int deleteFromTable(ADbiTable table) {
    throw new UnsupportedOperationException("Not Implemented yet...");
  }

  /**
   *
   * @param builder
   * @version 1.0.0 (2/13/2011)
   * @since 0.1.2 (2/06/2011)
   * @return
   */
  protected ADbiTable selectTableQueryBuilder(SQLQueryBuilder builder)
          throws DatabaseException {
    IDatabase database = dbManager.getCurrentDatabase();
    SQLQuery query = builder.getSQLQuery();
    if (!query.containsSelectClause()) {
      String msg = "SQL query not contains SELECT clause";
      logger.error(msg);
      throw new DatabaseException(msg);
    }
    ADbiTable table = builder.getTable();
    table.fillTable(builder.getColumnsList(),
            database.executeSelectQuery(query));

    return table;
  }

  /**
   *
   * @param builder
   * @version 1.0.0 (2/13/2011)
   * @since 0.1.2 (2/06/2011)
   * @return
   */
  protected ADbiRow selectRowQueryBuilder(SQLQueryBuilder builder)
          throws DatabaseException {
    ADbiRow row = null;
    IDatabase database = dbManager.getCurrentDatabase();
    SQLQuery query = builder.getSQLQuery();
    if (!query.containsSelectClause()) {
      String msg = "SQL query not contains SELECT clause";
      logger.error(msg);
      throw new DatabaseException(msg);
    }
    try {
      row = builder.getRow();
      if (row.isColumnsMapNull()) {
        row.setColumnsMap(builder.getTable().getColumnsMap());
      }
      row.fillRow(builder.getColumnsList(), database.executeSelectQuery(query));
    } catch (DbiException ex) {
      logger.error(ex.getMessage(), ex);
      throw new DatabaseException(ex.getMessage(), ex);
    }

    return row;
  }

  /**
   *
   * 
   * @param builder
   *
   * @version 1.1.0 (3/6/2011)
   * @since 2.3.0 (2/28/2011)
   * @return
   * @throws DatabaseException
   */
  protected int updateTableQueryBuilder(UpdateSQLQueryBuilder builder,
          ADbiTable table) throws DatabaseException {
    int updated = 0;
    IDatabase database = dbManager.getCurrentDatabase();
    SQLQuery defaultQuery = builder.getSQLQuery();
    if (!defaultQuery.containsUpdateClause()) {
      String msg = "SQL query not contains UPDATE clause";
      logger.error(msg);
      throw new DatabaseException(msg);
    }

    for (ADbiRow row : table.getListOfRows()) {
      // now create SQLQuery and add values
      SQLQuery query = defaultQuery;
      try {
        for (Object value : row.getRowValues()) {
          query.addValue(value);
        }
        updated += database.executeQuery(query);
      } catch (DbiException ex) {
        String msg = "Can not create SQLQuery for update table";
        logger.error(msg, ex);
        throw new DatabaseException(msg, ex);
      } catch (DatabaseException ex) {
        logger.error(ex.getMessage(), ex);
        throw new DatabaseException(ex.getMessage(), ex);
      }
    }
    return updated;
  }

  /**
   *
   * @param query
   * 
   * @version 1.0.0 (3/12/2011)
   * @since 2.5.0 (3/12/2011)
   * @return
   */
  protected ResultSet executeSelectQuery(SQLQuery query) {
    IDatabase database = dbManager.getCurrentDatabase();
    try {
      return database.executeSelectQuery(query);
    } catch (DatabaseException ex) {
      logger.error(ex.getMessage(), ex);
      return null;
    }
  }

  /* protected int updateTableQueryBuilder(UpdateSQLQueryBuilder> queries) {
  int update = 0;
  }*/
  /**
   *
   * 
   * @param table
   * @param row
   * @version 1.0.0 (2/18/2011)
   * @since 0.1.2 (2/06/2011)
   * @return
   */
  @Deprecated
  protected <T extends ADbiTable, R extends ADbiRow> SQLQueryBuilder<T, R> createSQLQBuilder(T table,
          R row) {
    return new SQLQueryBuilder<T, R>(table, row);
  }

  /**
   *
   * @param <T>
   * @param <R>
   * @param table
   * @version 1.0.0 (2/20/2011)
   * @since 2.1.0 (2/20/2011)
   * @return
   */
  protected <T extends ADbiTable, R extends ADbiRow> SQLQueryBuilder<T, R> createSQLQBuilder(
          T table) {
    return new SQLQueryBuilder<T, R>(table);
  }

  /**
   *
   * @param <T>
   * @param <R>
   * @param table
   * @version 1.0.0 (2/28/2011)
   * @since 2.3.0 (2/28/2011)
   * @return
   */
  protected <T extends ADbiTable, R extends ADbiRow> UpdateSQLQueryBuilder<T, R> createUpdateSQLBuilder(
          T table) {
    return new UpdateSQLQueryBuilder<T, R>(table);
  }
}

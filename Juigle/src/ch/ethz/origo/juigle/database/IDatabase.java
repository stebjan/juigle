package ch.ethz.origo.juigle.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import ch.ethz.origo.juigle.context.exceptions.DatabaseException;
import ch.ethz.origo.juigle.database.dbi.SQLQuery;

/**
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 1.0.0 (2/28/2011)
 * @since 1.0.0 (1/6/2011)
 */
public interface IDatabase {

  /**
   *
   * @param driver
   * @param url
   * @param username
   * @param password
   *
   * @version 1.0.0 (1/6/2011)
   * @since 0.1.0 (1/6/2011)
   * @throws DatabaseException
   */
  public void connect(String driver, String url, String username,
          String password) throws DatabaseException;

  /**
   * @version 1.0.0 (1/6/2011)
   * @since 0.1.0 (1/6/2011)
   * @throws DatabaseException
   */
  public void connect() throws DatabaseException;

  /**
   *
   * 
   * @version 0.2.0 (1/30/2011)
   * @since 0.1.2 (1/15/2011)
   * @return
   * @throws DatabaseException
   */
  public Connection getConnection() throws DatabaseException;

  /**
   *
   * @param connection
   *
   * @version 1.0.0 (1/6/2011)
   * @since 0.1.0 (1/6/2011)
   * @throws DatabaseException
   */
  public void returnConnection(Connection connection) throws DatabaseException;

  /**
   * 
   * @version 1.0.0 (1/6/2011)
   * @since 0.1.0 (1/6/2011)
   * @return
   */
  public String getName();

  /**
   *
   * 
   * @param query
   * @version 0.2.0 (2/06/2011)
   * @since 0.1.3 (1/30/2011)
   * @throws DatabaseException
   */
  public ResultSet executeSelectQuery(SQLQuery query) throws DatabaseException;

  /**
   *
   *
   * @param query
   * @version 0.1.0 (2/06/2011)
   * @since 0.2.0 (2/06/2011)
   * @throws DatabaseException
   */
  public int executeQuery(SQLQuery query) throws DatabaseException;

  /**
   *
   * @param queries
   * @version 1.0.0 (2/28/2011)
   * @since 1.0.0 (2/28/2011)
   * @return
   * @throws DatabaseException
   */
  public int executeQuery(List<SQLQuery> queries) throws DatabaseException;

}

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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ch.ethz.origo.juigle.context.exceptions.DatabaseException;
import ch.ethz.origo.juigle.database.dbi.SQLQuery;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see IDatabase
 * @version 0.3.0 (2/28/2011)
 * @since 1.0.0 (1/6/2011)
 */
public abstract class ADatabase implements IDatabase {

  /** Logger for this class */
  private static final Logger logger = Logger.getLogger(ADatabase.class);
  private List<Connection> listOfConnection;
  private String driver;
  private String url;
  private String username;
  private String password;

  @Override
  public void connect(String driver, String url, String username,
          String password) throws DatabaseException {
    this.driver = driver;
    this.url = url;
    this.username = username;
    this.password = password;
    Connection connection = null;
    try {
      Class.forName(driver).newInstance();
    } catch (Exception e) {
      String msg = "ERROR: failed to load HSQLDB JDBC driver.";
      logger.error(msg);
      throw new DatabaseException(msg, e);
    }
    connection = dbConnect();

    listOfConnection = new ArrayList<Connection>();
    listOfConnection.add(connection);
  }

  @Override
  public Connection getConnection() throws DatabaseException {
    Connection con = null;
    if (listOfConnection.size() > 0) {
      try {
        con = listOfConnection.get(0);
        con.setAutoCommit(false);
        listOfConnection.remove(0);
      } catch (SQLException e) {
        String msg = "Can not set autoCommit on false";
        logger.error(msg);
        throw new DatabaseException(msg, e);
      }
    } else {
      con = dbConnect();
      listOfConnection.add(con);
    }

    return con;
  }

  @Override
  public void returnConnection(Connection connection) throws DatabaseException {
    try {
      connection.clearWarnings();
      listOfConnection.add(connection);
    } catch (SQLException e) {
      String msg = "Can not return connection";
      logger.error(msg, e);
      throw new DatabaseException(msg, e);
    }
  }

  @Override
  public ResultSet executeSelectQuery(SQLQuery query) throws DatabaseException {
    if (query != null) {
      //FIXME only support now INSERT - UPDATE operation
      Connection con = getConnection();
      Statement st = null;
      try {
        if (query.getValueCount() > 0) {
          st = con.prepareStatement(query.getQueryString());
          query.fillStatement((PreparedStatement) st);
        } else {
          st = con.createStatement();
        }
        return st.executeQuery(query.getQueryString());
      } catch (SQLException e) {
        String msg = "Error while executing query: " + query.toString();
        logger.error(msg);
        throw new DatabaseException(msg, e);
      }
    }

    return null;
  }

  @Override
  public int executeQuery(SQLQuery query)
          throws DatabaseException {
    int changed = 0;
    if (query != null) {
      //FIXME only support now INSERT - UPDATE operation
      Connection con = getConnection();
      try {
        if (query.getValueCount() > 0) {
          PreparedStatement st = con.prepareStatement(query.getQueryString());
          query.fillStatement(st);
          changed = st.executeUpdate();
        } else {
          changed = con.createStatement().executeUpdate(query.getQueryString());
        }
        con.commit();
      } catch (SQLException e) {
        String msg = "Error while executing query: " + query.toString();
        logger.error(msg);
        throw new DatabaseException(msg, e);
      }
    }

    return changed;
  }

  @Override
  public int executeQuery(List<SQLQuery> queries) throws DatabaseException {
    int changed = 0;
    //FIXME only support now INSERT - UPDATE operation
    if (queries != null && !queries.isEmpty()) {
      Connection con = getConnection();
      for (SQLQuery query : queries) {
        if (query != null) {
          try {
            if (query.getValueCount() > 0) {
              PreparedStatement st = con.prepareStatement(query.getQueryString());
              query.fillStatement(st);
              changed += st.executeUpdate();
            } else {
              changed += con.createStatement().executeUpdate(query.getQueryString());
            }
          } catch (SQLException e) {
            String msg = "Error while executing query: " + query.toString();
            logger.error(msg);
            throw new DatabaseException(msg, e);
          }
        }
      }
      try {
        con.commit();
      } catch (SQLException ex) {
        logger.error("Commit failed...", ex);
        throw new DatabaseException("Commit failed...", ex);
      }
    }

    return changed;
  }

  private Connection dbConnect() throws DatabaseException {
    try {
      return DriverManager.getConnection(url, username, password);
    } catch (SQLException e) {
      String msg = "ERROR: failed connect to database=" + url
              + " user=" + username;
      logger.error(msg);
      throw new DatabaseException(msg, e);
    }
  }
}

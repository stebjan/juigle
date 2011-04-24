package ch.ethz.origo.juigle.database.dbi;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import ch.ethz.origo.juigle.context.exceptions.DbiException;

/**
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.4.0 (2/13/2011)
 * @since 1.0.0 (1/8/2011)
 */
public abstract class ADbiRow {

  /** Logger for this class */
  private static final Logger logger = Logger.getLogger(ADbiRow.class);
  protected Map<String, DbiMethodPair> columnsMap;
  private Class<?> commandHandlerArgTypes = Object.class;

  /**
   *
   *
   * @version 1.0.0 (2/13/2011)
   * @since 0.2.0 (2/06/2011)
   * @param columns
   * @param rs
   * @throws DbiException
   */
  public void fillRow(List<String> columns, ResultSet rs)
          throws DbiException {
    if (rs != null && columns != null && columns.size() > 0) {
      try {
        rs.next();
        Method[] allMethods = getClass().getDeclaredMethods();
        Map<String, Method> methodsMap = new HashMap<String, Method>();
        // fill all methods
        for (int i = 0; i < allMethods.length; i++) {
          Method m = allMethods[i];
          methodsMap.put(m.getName(), m);
        }
        // iterate all columns selected by SQL query
        for (String columnName : columns) {
          String methodName = columnsMap.get(columnName).getSetMethod();
          try {
            Method method = methodsMap.get(methodName);
            logger.debug("DbiRow - Calling method=" + method.getName());
            method.invoke(this, rs.getObject(columnName));
          } catch (Exception e) {
            String msg = "Can not invoke method: " + methodName;
            logger.error(msg, e);
            throw new DbiException(msg, e);
          }
        }
      } catch (SQLException ex) {
        String msg = "Problem with ResultSet";
        logger.error(msg, ex);
        throw new DbiException(msg, ex);
      }
    } else {
      throw new DbiException("ResultSet or List of columns is NULL...");
    }
  }

  /**
   *
   * @version 0.1.0 (2/11/2011)
   * @since 0.3.0 (2/11/2011)
   */
  public List<Object> getRowValues() throws DbiException {
    List<Object> listOfValues = new ArrayList<Object>();
    Method commandHandler = null;
    for (Entry<String, DbiMethodPair> entry : columnsMap.entrySet()) {
      try {
        commandHandler = getClass().getMethod(entry.getValue().getGetMethod());
        logger.info("DbiRow - Calling get method=" + commandHandler.getName());
        listOfValues.add(commandHandler.invoke(this));
      } catch (Exception e) {
        String msg = "Can not invoke method: "
                + entry.getValue().getGetMethod();
        logger.error(msg, e);
        throw new DbiException(msg, e);
      }
    }
    return listOfValues;
  }

  /**
   *
   *
   * @version 0.1.0 (2/11/2011)
   * @since 0.3.0 (2/11/2011)
   * @param columnsMap
   */
  public void setColumnsMap(Map<String, DbiMethodPair> columnsMap) {
    this.columnsMap = columnsMap;




  }

  /**
   *
   * 
   * @version 0.1.0 (2/13/2011)
   * @since 0.4.0 (2/13/2011)
   * @return
   */
  public boolean isColumnsMapNull() {
    return columnsMap == null;


  }
}

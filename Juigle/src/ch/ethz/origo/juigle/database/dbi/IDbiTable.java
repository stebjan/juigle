package ch.ethz.origo.juigle.database.dbi;

import ch.ethz.origo.juigle.context.exceptions.DatabaseException;

/**
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 1.0.1 (2/24/2011)
 * @since 1.0.0 (1/6/2011)
 */
public interface IDbiTable {

  	public static final Object[] VIEW = {0, 1};

  /**
   * Return database table name
   *
   * @return name of table
   */
  public String getTableName();
  
  public void clearResources() throws DatabaseException;

  public boolean isEmpty();


}

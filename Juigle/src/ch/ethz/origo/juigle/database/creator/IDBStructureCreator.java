package ch.ethz.origo.juigle.database.creator;

import ch.ethz.origo.juigle.context.exceptions.DatabaseException;
import ch.ethz.origo.juigle.database.model.DBModel;

/**
 *
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 1.0.0 (2/11/2011)
 * @since 1.0.0 (1/16/2011)
 */
public interface IDBStructureCreator {

  /**
   * 
   *
   * @version 0.1.0 (1/30/2011)
   * @since 0.2.0 (1/30/2011)
   * @param model
   */
  public void setDatabaseModel(DBModel model);

  /**
   * 
   *
   * @version 0.2.0 (1/30/2011)
   * @since 1.0.0 (1/16/2011)
   * @throws DatabaseException
   */
  public String createStructure() throws DatabaseException;

  /**
   *
   * @return
   * @throws DatabaseException
   *
   * @version 1.0.0 (2/11/2011)
   * @since 1.0.0 (2/11/2011)
   */
  public String createTableStructure() throws DatabaseException;

  /**
   *
   * @return
   * @throws DatabaseException
   *
   * @version 1.0.0 (2/11/2011)
   * @since 1.0.0 (2/11/2011)
   */
  public String createRelationsStructure() throws DatabaseException;

  /**
   *
   *
   * @version 0.1.0 (1/30/2011)
   * @since 0.2.0 (1/30/2011)
   * @throws DatabaseException
   */
  public String dropStructure() throws DatabaseException;
}

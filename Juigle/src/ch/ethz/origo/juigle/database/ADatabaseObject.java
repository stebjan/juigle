package ch.ethz.origo.juigle.database;

import org.apache.log4j.Logger;

import ch.ethz.origo.juigle.context.exceptions.InvalidDatabaseObjectException;

/**
 *
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.1 (2/06/2011)
 * @since 1.0.0 (1/8/2011)
 */
public abstract class ADatabaseObject {

  /** Logger for this class */
  private static final Logger logger = Logger.getLogger(ADatabaseObject.class);

  /**
   * 
   * @version 0.2.0 (2/06/2011)
   * @since 0.1.0 (1/08/2011)
   * @throws InvalidDatabaseObjectException
   */
  public abstract void validate() throws InvalidDatabaseObjectException;

  @Override
  public abstract String toString();

  /**
   * 
   *
   * @version 0.1.0 (1/8/2011)
   * @since 0.1.0 (1/8/2011)
   */
  public void writeToLogErrorNoValidDO() {
    logger.error("Database Object is not valid..." + toString());
  }
}

package ch.ethz.origo.juigle.database.creator;

import ch.ethz.origo.juigle.context.exceptions.DatabaseException;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see DBStructureCreator
 * @version 0.1.1 (1/30/2011)
 * @since 1.0.0 (1/16/2011)
 */
public class SQLFileDBStructureCreator extends DBStructureCreator {

  @Override
  public String createStructure() throws DatabaseException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public String dropStructure() throws DatabaseException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}

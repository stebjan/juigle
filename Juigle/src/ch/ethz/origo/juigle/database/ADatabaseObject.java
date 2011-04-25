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

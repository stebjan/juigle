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
 *    Copyright (C) 2009 - 2010 
 *    							University of West Bohemia, 
 *                  Department of Computer Science and Engineering, 
 *                  Pilsen, Czech Republic
 */
package ch.ethz.origo.juigle.data.database.model.ddl;

import ch.ethz.origo.juigle.application.exception.database.SQLDDLException;
import ch.ethz.origo.juigle.data.database.model.DBColumn;
import ch.ethz.origo.juigle.data.database.model.DBComponent;
import ch.ethz.origo.juigle.data.database.model.DBIndex;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/02/2010)
 * @since 0.1.0 (3/02/2010)
 * @see AbstractDDLSQLSyntax
 *
 */
public class IndexDDLSQLSyntax extends AbstractDDLSQLSyntax {

	protected DBIndex index;
	
	public IndexDDLSQLSyntax(DBComponent component) {
		super(component);
		if (component instanceof DBIndex) {
			this.index = (DBIndex) component;
		}
	}

  public String create() throws SQLDDLException {
    return "CREATE INDEX " + index.getWrappedIndexName() + " ON " +
      index.getOwnerTable().getWrappedTableName() + " (" + 
      assemblyColumnsToCommand(index.getColumns(), index.getOrder()) + ")";
  }
  
  public String drop() throws SQLDDLException {
    return "DROP INDEX " + index.getWrappedIndexName();
  }

  public String add() throws SQLDDLException {
    throw new SQLDDLException("Index " + index.getIndexName() +
      " hasn't implemented add SQL-Command.");
  }

  public String modify() throws SQLDDLException {
    throw new SQLDDLException("Index " + index.getIndexName() +
      " hasn't implemented modify SQL-Command.");
  }
  
  /**
   * Assembly columns and order to this form col1 DESC, col2 ASC, col3, ...atd.
   * orders: ASC is true, DESC is false (default is ASC)
   * 
   * @param columns DBColumn[]
   * @param order boolean[]
   * @return command String
   */
  protected String assemblyColumnsToCommand(DBColumn[] columns, boolean[] order) {
    StringBuffer command = new StringBuffer();
    for (int i = 0; i < columns.length; i++) {
      command.append(columns[i].getWrappedName());
      if (order[i] == false) { // desc order
        command.append(" DESC");        
      }
      if (i < columns.length-1) {
        command.append(", ");
      }
    }
    return command.toString();
  }

}

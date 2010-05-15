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

import java.util.List;

import ch.ethz.origo.juigle.application.exception.database.SQLDDLException;
import ch.ethz.origo.juigle.data.database.model.DBColumn;
import ch.ethz.origo.juigle.data.database.model.DBComponent;

/**
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (3/02/2010)
 * @since 0.1.0 (3/02/2010)
 * @see AbstractDDLSQLSyntax
 * 
 */
public class ConstraintDDLSQLSyntax extends AbstractDDLSQLSyntax {

	public ConstraintDDLSQLSyntax(DBComponent component) {
		super(component);
	}
	
	/**
   * Assembly columns to this form col1, col2, col3, ...atd.
   * 
   * @param columns
   * @return command structure String
   */
  protected String assemblyColumnsToCommand(List<DBColumn> columns) {
    int count = columns.size();
    StringBuffer command = new StringBuffer();
    for (int i = 0; i < count; i++) {
      command.append((columns.get(i)).getWrappedName());
      if (i < count-1) {
        command.append(", ");
      }
    }
    return command.toString();
  }

	@Override
	public String add() throws SQLDDLException {
		return null;
	}

	@Override
	public String create() throws SQLDDLException {
		return null;
	}

	@Override
	public String drop() throws SQLDDLException {
		return null;
	}

	@Override
	public String modify() throws SQLDDLException {
		return null;
	}

}

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
import ch.ethz.origo.juigle.data.database.model.DBComponent;
import ch.ethz.origo.juigle.data.database.model.constraint.DBUniqueConstraint;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/02/2010)
 * @since 0.1.0 (3/02/2010)
 * @see ConstraintDDLSQLSyntax
 */
public class UniqueConstraintDDLSQLSyntax extends ConstraintDDLSQLSyntax {

	protected DBUniqueConstraint constraint;
	
  public UniqueConstraintDDLSQLSyntax(DBComponent component) {
		super(component);
		if (component instanceof DBUniqueConstraint) {
			this.constraint = (DBUniqueConstraint) component;
		}
	}

	public String add() throws SQLDDLException {
    return "CONSTRAINT " + constraint.getWrappedContraintName() + " UNIQUE (" +
      assemblyColumnsToCommand(constraint.getColumns()) + ")";
  }

  public String drop() throws SQLDDLException {
    return "ALTER TABLE " + constraint.getOwnerTable().getWrappedTableName() + " DROP CONSTRAINT " +
      constraint.getWrappedContraintName();
  }

  public String create() throws SQLDDLException {
    return "ALTER TABLE " + constraint.getOwnerTable().getWrappedTableName() + " ADD " + add();
  }

  public String modify() throws SQLDDLException {
    throw new SQLDDLException("Constraint " + constraint.getComponentName() +
    " hasn't implemented modify SQL-Command.");
  }
}

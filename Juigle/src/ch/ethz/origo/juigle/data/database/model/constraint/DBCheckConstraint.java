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
package ch.ethz.origo.juigle.data.database.model.constraint;

import ch.ethz.origo.juigle.data.database.model.DBTable;
import ch.ethz.origo.juigle.data.database.model.ddl.CheckConstraintDDLSQLSyntax;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/02/2010)
 * @since 0.1.0 (3/02/2010)
 * @see DBConstraint
 * 
 */
public class DBCheckConstraint extends DBConstraint {

	protected String condition;
	
	public DBCheckConstraint(String name, DBTable ownerTable, String condition) {
		super(name, ownerTable);
		this.condition = condition;
		
	}
	
  /**
   * Create check constraint with owner table. Name of constraint
   * will be auto-generated.
   *
   * @param ownerTable DbTable
   */
  public DBCheckConstraint(DBTable ownerTable, String condition) {
    super(autoNameGenerator("C", ownerTable.getComponentName()), ownerTable);
    this.condition = condition;
  }

  public Class<?> getDDLSQLSyntaxClass() {
    return CheckConstraintDDLSQLSyntax.class;
  }

  public String getCondition() {
    return condition;
  }

  public String toString() {
    return "CHECK " + super.toString() + " Owner: " + ownerTable.getComponentName();
  }

}
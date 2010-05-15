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

import java.util.ArrayList;
import java.util.List;

import ch.ethz.origo.juigle.data.database.driver.IDriverDescription;
import ch.ethz.origo.juigle.data.database.model.DBColumn;
import ch.ethz.origo.juigle.data.database.model.DBComponent;
import ch.ethz.origo.juigle.data.database.model.DBTable;

/**
 * 
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (1/24/2010)
 * @since 0.1.0 (1/24/2010)
 * @see DBComponent
 */
public class DBConstraint extends DBComponent {

	/** Name of constraint **/
	protected String name;
	/** List of columns **/
	protected List<DBColumn> columns;
	
  private static int seed = 0;

	/** Owner table */
	protected DBTable ownerTable;

	/**
	 * Construct a new constraint
	 * 
	 * @param name
	 *          of constraint
	 * @param owner
	 *          of constraint
	 */
	public DBConstraint(String name, DBTable ownerTable) {
		super(name, DBComponent.CONSTRAINT_COMPONENT_TYPE);
		this.ownerTable = ownerTable;
		columns = new ArrayList<DBColumn>();
	}

	/**
	 * Add column to the constraint
	 * 
	 * @param column
	 *          for constraint
	 * 
	 */
	public void addColumn(DBColumn column) {
		columns.add(column);
	}

	public String getName() {
		return name;
	}

	public List<DBColumn> getColumns() {
		return columns;
	}

	public DBTable getOwnerTable() {
		return ownerTable;
	}
	
  public String getWrappedContraintName() {
    return getWrappedCommand(name);
  }
	
  /**
   * Get column by column name.
   * Return null if column doesn't exist.
   *
   * @param name of column
   * @return DbColumn
   */
  public DBColumn getColumn(String name) {
    for(int i = 0; i < columns.size(); i++) {
      if (columns.get(i).getComponentName().equals(name)) {
        return columns.get(i);
      }
    }
    return null;
  }

  /**
   * Remove all columns from contraint
   */
  public void clearColumnsList() {
    columns.clear();
  }

	public String toString() {
		// prepare list of columns
		StringBuffer cols = new StringBuffer();
		for (int i = 0; i < columns.size(); i++) {
			cols.append(columns.get(i).getComponentName());
			if (i < columns.size() - 1) {
				cols.append(", ");
			}
		}
		return type + ": " + name + " Columns: " + cols.toString();
	}

	@Override
	public Class<?> getDDLSQLSyntaxClass() {
		return null;
	}
	
	/**
   * Auto-generation of constraint name
   *
   * @param constraint String
   * @param tableName String
   * @return name String
   */
  protected static String autoNameGenerator(String constraint, String tableName) {
    return tableName + "_" + constraint + "_" + seed++;
  }

	@Override
	public void useDriverDescription(IDriverDescription driver) throws Exception {
		driver.assignDriverFeatures(this);
	}

}
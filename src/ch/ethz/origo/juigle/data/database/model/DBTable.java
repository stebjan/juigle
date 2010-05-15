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
package ch.ethz.origo.juigle.data.database.model;

import java.util.ArrayList;
import java.util.List;

import ch.ethz.origo.juigle.application.exception.database.SQLDDLException;
import ch.ethz.origo.juigle.data.database.driver.IDriverDescription;
import ch.ethz.origo.juigle.data.database.model.constraint.DBConstraint;
import ch.ethz.origo.juigle.data.database.model.constraint.PrimaryKeyConstraint;
import ch.ethz.origo.juigle.data.database.model.ddl.TableDDLSQLSyntax;

/**
 * 
 * 
 * @author Vaclav Souhrada
 * @version 0.1.1 (3/31/2010)
 * @since 0.1.0 (1/24/2010)
 * @see DBComponent
 */
public class DBTable extends DBComponent {

	/** Columns list */
	private List<DBColumn> columns;
	/** Constraints list */
	private List<DBConstraint> constraints;

	/** Driver **/
	protected IDriverDescription driver;

	/** Primary Key **/
	protected PrimaryKeyConstraint pk;

	/**
	 * Construct the database table component
	 * 
	 * @param name
	 *          table name
	 */
	public DBTable(String name) {
		super(name, DBComponent.TABLE_COMPONENT_TYPE);
		columns = new ArrayList<DBColumn>();
		constraints = new ArrayList<DBConstraint>();
		this.pk = new PrimaryKeyConstraint(this);
	}

	/**
	 * Add column table component to components list.
	 * 
	 * @param column
	 *          of table
	 */
	public void addColumn(DBColumn column) {
		column.setOwnerDbTable(this);
		columns.add(column);
	}

	/**
	 * Add list of columns to the Database table
	 * 
	 * @param columns list
	 */
	public void addColumns(List<DBColumn> columns) {
		for (DBColumn column : columns) {
			addColumn(column);
		}
	}

	/**
	 * Add column constraint to the constraints list.
	 * 
	 * @param constraint
	 *          of column
	 */
	public void addConstraint(DBConstraint constraint) {
		constraints.add(constraint);
	}

	/**
	 * Get Wrapped table name
	 * 
	 * @return wrapped table name String
	 */
	public String getWrappedTableName() {
		return getWrappedCommand(name);
	}

	/**
	 * Add component to components list.
	 * 
	 * @param component
	 *          table component
	 * @version 0.1.0
	 * @since 0.1.0
	 * 
	 */
	public void addComponent(DBComponent component) {
		if (component instanceof DBColumn) {
			DBColumn column = (DBColumn) component;
			column.setOwnerDbTable(this);
			columns.add(column);
		} else if (component instanceof DBConstraint) {
			constraints.add((DBConstraint) component);
		}
	}

	/**
	 * Remove component from components list.
	 * 
	 * @param component
	 *          table component
	 * @version 0.1.0
	 * @since 0.1.0
	 * 
	 */
	public void removeComponent(DBComponent component) {
		if (component instanceof DBColumn) {
			columns.remove((DBColumn) component);
		} else if (component instanceof DBConstraint) {
			constraints.remove((DBConstraint) component);
		}
	}
	
	/**
   * Remove constraint from the table
   * 
   * @param constraint instance of {@link DBConstraint}
   */
  public void removeConstraint(DBConstraint constraint) {
    constraints.remove(constraint);
  }

  /**
   * {@inheritDoc}
   */
  @Override
	public void useDriverDescription(IDriverDescription driver) throws Exception {
		this.driver = driver;
		driver.assignDriverFeatures(this);
		// set driver for all columns
		for (int i = 0; i < columns.size(); i++) {
			columns.get(i).useDriverDescription(driver);
		}
		// set driver for all constraint
		for (int i = 0; i < constraints.size(); i++) {
			constraints.get(i).useDriverDescription(driver);
		}
	}
	
	public List<DBColumn> getListOfColumns() {
		return columns;
	}
	
	public List<DBConstraint> getListOfConstraints() {
		return constraints;
	}
	
	/**
   * Get contraint by its name
   * 
   * @param constraintName
   * @return constraint DbConstraint
   */
  public DBConstraint getConstraintByName(String constraintName) {
    for(DBConstraint constraint : constraints) {
      if (constraint.getComponentName().equals(constraintName)) {
        return constraint;
      }
    }
    return null;    
  }
  
  /**
   * Auto create primary contraint from columns
   * 
   * @param columns ArrayList
   */
  protected void autoCreatePK(List<DBColumn> columns) {
    pk.clearColumnsList();
    for (int i = 0; i < columns.size(); i++) {
      DBColumn column = (DBColumn)columns.get(i);
      if (column.isPrimaryKey()) {
        pk.addColumn(column);
      }
    }
    if (!pk.getColumns().isEmpty()) {
      try {
        pk.useDriverDescription(driver);
        if (constraints.contains(pk)) {
          removeConstraint(pk);
        }
        addConstraint(pk);
      }
      catch (Exception e) {
        logger.error(e.getMessage(), e);
      }
    }
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public String create() throws SQLDDLException {
    // auto-create primary keys
    autoCreatePK(columns);
    
    // call DDL SQL SYNTAXer 
    return super.create();
  }

	@Override
	public Class<?> getDDLSQLSyntaxClass() {
		return TableDDLSQLSyntax.class;
	}

}
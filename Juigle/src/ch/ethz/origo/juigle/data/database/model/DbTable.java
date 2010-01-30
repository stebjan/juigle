package ch.ethz.origo.juigle.data.database.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (1/24/2010)
 * @since 0.1.0 (1/24/2010)
 * @see DbComponent
 */
public class DbTable extends DbComponent {
	
	/** Columns list */
	private List<DbColumn> columns;
	/** Constraints list */
	private List<DbConstraint> constraints;

	/**
	 * Construct the database table component
	 *  
	 * @param name table name
	 */
	public DbTable(String name) {
		super(name, DbComponent.TABLE_COMPONENT_TYPE);
		columns = new ArrayList<DbColumn>();
		constraints = new ArrayList<DbConstraint>();
	}
	
	/**
	 * Add column table component to components 
	 * list.
	 * 
	 * @param column of table
	 */
	public void addColumn(DbColumn column) {
		columns.add(column);
	}
	
	/**
	 * Add column constraint to the constraints 
	 * list.
	 * @param constraint of column
	 */
	public void addConstraint(DbConstraint constraint) {
		constraints.add(constraint);
	}
	/**
	 * Add component to components list.
	 * 
	 * @param component table component
	 * @version 0.1.0
	 * @since 0.1.0
	 * 
	 */
	public void addComponent(DbComponent component) {
		if (component instanceof DbColumn) {
			columns.add((DbColumn)component);			
		} else if (component instanceof DbConstraint) {
			constraints.add((DbConstraint)component);
		}
	}
	
	/**
	 * Remove component from components list.
	 * 
	 * @param component table component
	 * @version 0.1.0
	 * @since 0.1.0
	 * 
	 */
	public void removeComponent(DbComponent component) {
		if (component instanceof DbColumn) {
			columns.remove((DbColumn)component);			
		} else if (component instanceof DbConstraint) {
			constraints.remove((DbConstraint)component);
		}
	}
	
}
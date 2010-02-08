package ch.ethz.origo.juigle.data.database.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (1/24/2010)
 * @since 0.1.0 (1/24/2010)
 * @see DBComponent
 */
public class DBTable extends DBComponent {
	
	/** Columns list */
	private List<DBColumn> columns;
	/** Constraints list */
	private List<DBConstraint> constraints;

	/**
	 * Construct the database table component
	 *  
	 * @param name table name
	 */
	public DBTable(String name) {
		super(name, DBComponent.TABLE_COMPONENT_TYPE);
		columns = new ArrayList<DBColumn>();
		constraints = new ArrayList<DBConstraint>();
	}
	
	/**
	 * Add column table component to components 
	 * list.
	 * 
	 * @param column of table
	 */
	public void addColumn(DBColumn column) {
		columns.add(column);
	}
	
	/**
	 * Add column constraint to the constraints 
	 * list.
	 * @param constraint of column
	 */
	public void addConstraint(DBConstraint constraint) {
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
	public void addComponent(DBComponent component) {
		if (component instanceof DBColumn) {
			columns.add((DBColumn)component);			
		} else if (component instanceof DBConstraint) {
			constraints.add((DBConstraint)component);
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
	public void removeComponent(DBComponent component) {
		if (component instanceof DBColumn) {
			columns.remove((DBColumn)component);			
		} else if (component instanceof DBConstraint) {
			constraints.remove((DBConstraint)component);
		}
	}
	
}
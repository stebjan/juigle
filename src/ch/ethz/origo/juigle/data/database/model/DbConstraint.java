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
public class DbConstraint extends DbComponent {

	/** Name of constraint **/
	protected String name;
	/** List of columns **/
	protected List<DbColumn> columns;

	/** Owner table */
	private DbTable owner;

	/**
	 * Construct a new constraint
	 * 
	 * @param name
	 *          of constraint
	 * @param owner
	 *          of constraint
	 */
	public DbConstraint(String name, DbTable owner) {
		super(name, DbComponent.CONSTRAINT_COMPONENT_TYPE);
		this.owner = owner;
		columns = new ArrayList<DbColumn>();
	}

	/**
	 * Add column to the constraint
	 * 
	 * @param column
	 *          for constraint
	 * 
	 */
	public void addColumn(DbColumn column) {
		columns.add(column);
	}

	public String getName() {
		return name;
	}

	public List<DbColumn> getColumns() {
		return columns;
	}

	public DbTable getOwner() {
		return owner;
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

}
package ch.ethz.origo.juigle.data.database.model;

import org.apache.log4j.Logger;

/**
 * <code>Abstract</code> class for the database components (Table, Column, ...)
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (1/24/2010)
 * @since 0.1.0 (1/24/2010)
 * 
 */
public abstract class DbComponent {

	/** Logger */
	protected final static Logger logger = Logger.getLogger(DbComponent.class);  
	
	/** Component name */
	protected final String name;
	/** Component type */
	protected final String type;
	
	/** Database component type - TABLE */
	public static final String TABLE_COMPONENT_TYPE = "TABLE";
	/** Database component type - COLUMN */
	public static final String COLUMN_COMPONENT_TYPE = "COLUMN";
	/** Database component type - CONSTRAINT */
	public static final String CONSTRAINT_COMPONENT_TYPE = "CONSTRAINT";

	/**
	 * Construct a new component
	 * 
	 * @param name component name
	 * @param type component type
	 */
	public DbComponent(String name, String type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * Return name of component
	 * 
	 * @return name of component as String
	 */
	public String getComponentName() {
		return name;
	}
	
	/**
	 * Return type of component
	 * 
	 * @return type of component as String
	 */
	public String getComponentType() {
		return type;
	}
	
}
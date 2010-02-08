package ch.ethz.origo.juigle.data.database;

/**
 * 
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (1/24/2010)
 * @since 0.1.0 (1/24/2010)
 *
 */
public class DBUtils {
	
	private static int CONSTRAINT;

	/**
	 * Return name of constraint. Returned name is consist from
	 * name of constraint and name of table. As suffix is constraint 
	 * number.
	 * 
	 * @param name of constraint
	 * @param tableName of table (constraint owner)
	 * @return constraint name
	 */
	public static String getAutoConstraintName(String name, String tableName) {
		return tableName + "_" + name + "_" + (DBUtils.CONSTRAINT++);
	}
	
}
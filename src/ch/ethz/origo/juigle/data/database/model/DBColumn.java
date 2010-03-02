package ch.ethz.origo.juigle.data.database.model;

/**
 * 
 * @author Vaclav Souhrada
 * @version 0.1.1 (3/02/2010)
 * @since 0.1.0 (1/24/2010)
 * @see DBComponent
 * 
 */
public class DBColumn extends DBComponent {

	
	public DBColumn(String name) {
		super(name, DBComponent.COLUMN_COMPONENT_TYPE);
	}

	@Override
	public Class<?> getDDLSQLSyntaxClass() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
   * Get wrapped column, if it is active
   *
   * @return String
   */
  public String getWrappedName() {
    // wrapped column name information
    return properties.getWrappedCommands() + name + properties.getWrappedCommands();
  }
	

}

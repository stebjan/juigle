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
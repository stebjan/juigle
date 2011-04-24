package ch.ethz.origo.juigle.database.model;

import java.util.ArrayList;
import java.util.List;

import ch.ethz.origo.juigle.database.model.ddl.ConstraintDDLSyntax;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see ADBComponent
 * @version 0.2.0 (1/22/2011)
 * @since 1.0.0 (1/15/2011)
 */
public class DBConstraint extends ADBComponent {

  private List<String> listOfColumnsName;

  private String ownerName;

  public DBConstraint(String constraintName, String ownerName) {
    super(constraintName, ADBComponent.CONSTRAINT_COMPONENT_TYPE);
    this.ownerName = ownerName;
    listOfColumnsName = new ArrayList<String>();
  }

  @Override
  public Class<?> getDDLSQLSyntaxClass() {
    return ConstraintDDLSyntax.class;
  }
  
  /**
   * 
   * 
   * @version 0.1.0 (1/22/2011)
   * @since 0.2.0 (1/22/2011)
   * @param columnName
   */
  public void addColumnName(String columnName) {
    listOfColumnsName.add(getWrappedCommand(columnName));
  }

  /**
   *
   * 
   * @version 0.1.0 (1/22/2011)
   * @since 0.2.0 (1/22/2011)
   * @return
   */
  public List<String> getListOfColumnsName() {
    return listOfColumnsName;
  }

  /**
   *
   *
   * @version 0.1.0 (1/22/2011)
   * @since 0.2.0 (1/22/2011)
   * @return
   */
  public String getWrappedConstraintName() {
    return getWrappedCommand(name);
  }

  /**
   *
   *
   * @version 0.1.0 (1/22/2011)
   * @since 0.2.0 (1/22/2011)
   * @return
   */
  public String getWrappedOwnerTableName() {
    return getWrappedCommand(getOwnerName());
  }

  /**
   *
   * 
   * @version 0.1.0 (1/22/2011)
   * @since 0.2.0 (1/22/2011)
   * @return
   */
  public String getOwnerName() {
    return ownerName;
  }
}

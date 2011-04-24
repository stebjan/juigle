package ch.ethz.origo.juigle.database.model.ddl;

import java.util.List;

import ch.ethz.origo.juigle.context.exceptions.SQLDDLException;
import ch.ethz.origo.juigle.database.model.ADBComponent;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see ADDLSQLSyntax
 * @version 0.1.0 (1/22/2011)
 * @since 1.0.0 (1/22/2011)
 */
public class ConstraintDDLSyntax extends ADDLSQLSyntax {

  public ConstraintDDLSyntax(ADBComponent dbComponent) {
    super(dbComponent);
  }

  /**
   * @version 0.1.0 (1/22/2011)
   * @since 0.1.0 (1/22/2011)
   * @param listOfColumnsNames
   * @return
   */
  public String assemblyColumnsNamesToCommand(List<String> listOfColumnsNames) {
    int count = listOfColumnsNames.size();
    StringBuffer command = new StringBuffer();
    for (int i = 0; i < count; i++) {
      command.append(listOfColumnsNames.get(i));
      if (i < count-1) {
        command.append(", ");
      }
    }
    return command.toString();
  }

  @Override
  public String create() throws SQLDDLException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public String add() throws SQLDDLException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public String modify() throws SQLDDLException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public String drop() throws SQLDDLException {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
}

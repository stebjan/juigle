package ch.ethz.origo.juigle.data.database.model.ddl;

import java.util.List;

import ch.ethz.origo.juigle.application.exception.database.SQLDDLException;
import ch.ethz.origo.juigle.data.database.model.DBColumn;
import ch.ethz.origo.juigle.data.database.model.DBComponent;

/**
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (3/02/2010)
 * @since 0.1.0 (3/02/2010)
 * @see AbstractDDLSQLSyntax
 * 
 */
public class ConstraintDDLSQLSyntax extends AbstractDDLSQLSyntax {

	public ConstraintDDLSQLSyntax(DBComponent component) {
		super(component);
	}
	
	/**
   * Assembly columns to this form col1, col2, col3, ...atd.
   * 
   * @param columns
   * @return command structure String
   */
  protected String assemblyColumnsToCommand(List<DBColumn> columns) {
    int count = columns.size();
    StringBuffer command = new StringBuffer();
    for (int i = 0; i < count; i++) {
      command.append((columns.get(i)).getWrappedName());
      if (i < count-1) {
        command.append(", ");
      }
    }
    return command.toString();
  }

	@Override
	public String add() throws SQLDDLException {
		return null;
	}

	@Override
	public String create() throws SQLDDLException {
		return null;
	}

	@Override
	public String drop() throws SQLDDLException {
		return null;
	}

	@Override
	public String modify() throws SQLDDLException {
		return null;
	}

}

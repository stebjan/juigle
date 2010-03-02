package ch.ethz.origo.juigle.data.database.model.ddl;

import ch.ethz.origo.juigle.application.exception.database.SQLDDLException;
import ch.ethz.origo.juigle.data.database.model.DBColumn;
import ch.ethz.origo.juigle.data.database.model.DBComponent;
import ch.ethz.origo.juigle.data.database.model.DBIndex;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/02/2010)
 * @since 0.1.0 (3/02/2010)
 * @see AbstractDDLSQLSyntax
 *
 */
public class IndexDDLSQLSyntax extends AbstractDDLSQLSyntax {

	protected DBIndex index;
	
	public IndexDDLSQLSyntax(DBComponent component) {
		super(component);
		if (component instanceof DBIndex) {
			this.index = (DBIndex) component;
		}
	}

  public String create() throws SQLDDLException {
    return "CREATE INDEX " + index.getWrappedIndexName() + " ON " +
      index.getOwnerTable().getWrappedTableName() + " (" + 
      assemblyColumnsToCommand(index.getColumns(), index.getOrder()) + ")";
  }
  
  public String drop() throws SQLDDLException {
    return "DROP INDEX " + index.getWrappedIndexName();
  }

  public String add() throws SQLDDLException {
    throw new SQLDDLException("Index " + index.getIndexName() +
      " hasn't implemented add SQL-Command.");
  }

  public String modify() throws SQLDDLException {
    throw new SQLDDLException("Index " + index.getIndexName() +
      " hasn't implemented modify SQL-Command.");
  }
  
  /**
   * Assembly columns and order to this form col1 DESC, col2 ASC, col3, ...atd.
   * orders: ASC is true, DESC is false (default is ASC)
   * 
   * @param columns DBColumn[]
   * @param order boolean[]
   * @return command String
   */
  protected String assemblyColumnsToCommand(DBColumn[] columns, boolean[] order) {
    StringBuffer command = new StringBuffer();
    for (int i = 0; i < columns.length; i++) {
      command.append(columns[i].getWrappedName());
      if (order[i] == false) { // desc order
        command.append(" DESC");        
      }
      if (i < columns.length-1) {
        command.append(", ");
      }
    }
    return command.toString();
  }

}

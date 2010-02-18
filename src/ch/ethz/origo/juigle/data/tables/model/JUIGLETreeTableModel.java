package ch.ethz.origo.juigle.data.tables.model;

import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

import ch.ethz.origo.juigle.application.ILanguage;
import ch.ethz.origo.juigle.application.exception.DataStoreException;

/**
 * 
 * 
 * @author Vaclav Souhrada
 * @version 0.2.1 (2/08/2010)
 * @since 0.1.0 (11/25/09)
 * @see DefaultTreeTableModel
 * 
 */
public abstract class JUIGLETreeTableModel extends DefaultTreeTableModel implements ILanguage {

	/**
	 * Insert values to table model
	 * 
	 * @throws DataStoreException
	 * @since 0.1.0
	 */
	public abstract void fillByValues() throws DataStoreException;
	
	@Override
	public abstract String getColumnName(int column);

}

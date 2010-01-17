package ch.ethz.origo.juigle.data.tables.model;

import org.jdesktop.swingx.treetable.AbstractTreeTableModel;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

import ch.ethz.origo.juigle.application.exception.DataStoreException;

/**
 * 
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (11/25/09)
 * @since 0.1.0 (11/25/09)
 * @see DefaultTreeTableModel
 * 
 */
public abstract class JUIGLETreeTableModel extends AbstractTreeTableModel {

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

package ch.ethz.origo.juigle.prezentation.tables.model;

import javax.swing.table.AbstractTableModel;

import ch.ethz.origo.juigle.application.exception.DataStoreException;
import ch.ethz.origo.juigle.data.tables.Record;

/**
 * 
 * @author Vaclav Souhrada 
 * @version 0.1.0 (11/25/09)
 * @since 0.1.0 (11/25/09)
 * @see AbstractTableModel
 */
public abstract class TableModel extends AbstractTableModel {

	/** Only for serialization */
	private static final long serialVersionUID = 3518887611530241715L;

	public abstract void fillByValues() throws DataStoreException;
	
	@Override
	public abstract int getColumnCount();

	@Override
	public abstract int getRowCount();

	@Override
	public abstract Object getValueAt(int row, int column);
	
	public abstract Record getRecord(int row);
	
	public abstract String getColumnName(int column);
	
	/**
   * {@inheritDoc}
   */
  public abstract boolean isCellEditable(int rowIndex, int columnIndex);
  
  public abstract void clearResources() throws DataStoreException;
  
  public abstract void addRecord(Record record) throws DataStoreException;
  
  public abstract void updateRecord(Record record, int tableRow) throws DataStoreException;
  
  public abstract void deleteRecord(Record record, int tableRow) throws DataStoreException;
  
  public abstract void deleteAllRecords() throws DataStoreException;
  
  public abstract int getStoreLastInsertId() throws DataStoreException;

}

/*
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

/*
 *  
 *    Copyright (C) 2009 - 2010 
 *    							University of West Bohemia, 
 *                  Department of Computer Science and Engineering, 
 *                  Pilsen, Czech Republic
 */
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

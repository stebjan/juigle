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
 * Create table model for classic type of java table (JTable)
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (11/25/09)
 * @since 0.1.0 (11/25/09)
 * @see AbstractTableModel
 */
public abstract class TableModel extends AbstractTableModel {

	/** Only for serialization */
	private static final long serialVersionUID = 3518887611530241715L;

	/**
	 * This method fill table model by values.
	 * 
	 * @throws DataStoreException
	 *           problem with data loading
	 */
	public abstract void fillByValues() throws DataStoreException;

	@Override
	public abstract int getColumnCount();

	@Override
	public abstract int getRowCount();

	@Override
	public abstract Object getValueAt(int row, int column);

	/**
	 * Return object as table <code>Record</code> from specified row.
	 * 
	 * @param row
	 *          record
	 * @return table <code>Record</code> from specified row.
	 */
	public abstract Record getRecord(int row);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract String getColumnName(int column);

	/**
	 * {@inheritDoc}
	 */
	public abstract boolean isCellEditable(int rowIndex, int columnIndex);

	/**
	 * Method for clear resources and clear memory.
	 * @throws DataStoreException
	 *           problem with data operations
	 */
	public abstract void clearResources() throws DataStoreException;

	/**
	 * Add record to the table model
	 * 
	 * @param record
	 * @throws DataStoreException
	 *           problem with data operations
	 */
	public abstract void addRecord(Record record) throws DataStoreException;

	/**
	 * Update table record on specified row
	 *  
	 * @param record
	 * @param tableRow num. of table row where will be record updated
	 * @throws DataStoreException
	 *           problem with data operations
	 */
	public abstract void updateRecord(Record record, int tableRow)
			throws DataStoreException;

	/**
	 * Delete specified row
	 * 
	 * @param record of table
	 * @param tableRow num. of table row from which will be record deleted
	 * @throws DataStoreException
	 *           problem with data operations
	 */
	public abstract void deleteRecord(Record record, int tableRow)
			throws DataStoreException;

	/**
	 * Delete all records from table model
	 * @throws DataStoreException
	 *           problem with data operations
	 */
	public abstract void deleteAllRecords() throws DataStoreException;

	/**
	 * This method should be used when we working with database. Usually we need
	 * last inserted id. This method return it.
	 * 
	 * @return last inserted id of record
	 * @throws DataStoreException
	 *           problem with data operations
	 */
	public abstract int getStoreLastInsertId() throws DataStoreException;

}

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
package ch.ethz.origo.juigle.application.store;

import java.util.List;

import ch.ethz.origo.juigle.application.exception.DataStoreException;
import ch.ethz.origo.juigle.data.tables.Record;

/**
 * Database store - Incomplete - need to be finish to the next version
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (11/29/09)
 * @since 0.1.0 (11/29/09)
 * @see IStore
 *
 */
public class DatabaseStore implements IStore {

	@Override
	public void addRecord(Record record) throws DataStoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllRecords() throws DataStoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteRecord() throws DataStoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Record> getAllRecords() throws DataStoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRecord(Record record) throws DataStoreException {
		// TODO Auto-generated method stub

	}

}

package ch.ethz.origo.juigle.application.store;

import java.util.List;

import ch.ethz.origo.juigle.application.exception.DataStoreException;
import ch.ethz.origo.juigle.data.tables.Record;

/**
 * 
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

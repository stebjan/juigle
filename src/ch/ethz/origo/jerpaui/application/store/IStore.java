package ch.ethz.origo.juigle.application.store;

import java.util.List;

import ch.ethz.origo.juigle.application.exception.DataStoreException;
import ch.ethz.origo.juigle.data.tables.Record;

/**
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (11/29/09)
 * @since 0.1.0 (11/29/09)
 *
 */
public interface IStore {

	public List<Record> getAllRecords() throws DataStoreException;
	
	public void addRecord(Record record) throws DataStoreException;
	
	public void updateRecord(Record record) throws DataStoreException;
	
	public void deleteRecord() throws DataStoreException;
	
	public void deleteAllRecords() throws DataStoreException;
}

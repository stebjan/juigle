package ch.ethz.origo.juigle.data.database.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (2/08/2010)
 * @since 0.1.0 (2/07/2010)
 * 
 */
public class Database {
	
	private List<DBTable> listOfTables;
	
	public Database() {
		listOfTables = new ArrayList<DBTable>();
	}

}

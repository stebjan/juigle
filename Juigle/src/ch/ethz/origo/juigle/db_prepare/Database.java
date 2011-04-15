package ch.ethz.origo.juigle.db_prepare;

import ch.ethz.origo.juigle.application.exception.database.DatabaseException;

/**
 * 
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0.00 (10/13/2010)
 * @since 2.0.0 (10/13/2010)
 */
public class Database {

	private DatabaseInfo dbInfo;

	public Database() {
		dbInfo = new DatabaseInfo();
	}

	public DatabaseInfo getDatabaseInfo() throws DatabaseException {
		if (dbInfo == null) {
			throw new DatabaseException("JG032");
		}
		return dbInfo;
	}

	public void setDatabaseName(String dbName) {
		dbInfo.setName(dbName);
	}

	public void setDialect(String dialect) {
		dbInfo.setDialect(dialect);
	}

	public void setDriverClassName(String driverClassName) {
		dbInfo.setDriverClassName(driverClassName);
	}

	public void setURL(String url) {
		dbInfo.setURL(url);
	}

	public void setUserName(String userName) {
		dbInfo.setUserName(userName);
	}

	public void setPassword(String password) {
		dbInfo.setPassword(password);
	}

}
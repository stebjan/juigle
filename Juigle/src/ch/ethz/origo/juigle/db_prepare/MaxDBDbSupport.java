package ch.ethz.origo.juigle.db_prepare;

import java.util.Locale;
import java.util.Set;

import javax.sql.DataSource;



/**
 * Implementation of {@link DbSupport} for a MaxDB database.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0.00 (9/24/2010)
 * @since 2.0.0 (9/24/2010)
 * 
 */
public class MaxDBDbSupport extends DBSupport {

	public MaxDBDbSupport(DatabaseInfo databaseInfo, DataSource dataSource,
	    ISQLHandler sqlHandler, String customIdentifierQuoteString,
	    EDBIndetifierCase customDBIdentifierCase) {
		super(databaseInfo, dataSource, sqlHandler, customIdentifierQuoteString,
				customDBIdentifierCase);
	}

	/**
	 * Disables all referential constraints (e.g. foreign keys) on all table in
	 * the schema
	 * 
	 * @param schemaName
	 *          The schemaName, not null
	 */
	@Override
	public void disableReferentialConstraints(String schemaName) {
		Set<String> tableNames = getTableNames(schemaName);
		for (String tableName : tableNames) {
			disableReferentialConstraints(schemaName, tableName);
		}
	}

	// todo refactor (see oracle)
	@SuppressWarnings("unchecked")
	protected void disableReferentialConstraints(String schemaName,
	    String tableName) {
		ISQLHandler sqlHandler = getSQLHandler();
		Set<String> constraintNames = sqlHandler
		    .getItemsAsStringSet(
		        "select constraintname from constraints where constrainttype = 'FOREIGN KEY' AND tablename = '"
		            + tableName + "' and schemaname = '" + schemaName + "'",
		        getDataSource());
		for (String constraintName : constraintNames) {
			sqlHandler.executeUpdate("alter table "
			    + qualified(schemaName, tableName) + " drop foreign key "
			    + quoted(constraintName), getDataSource());
		}
	}

	/**
	 * Disables all value constraints (e.g. not null) on all tables in the schema
	 * 
	 * @param schemaName
	 *          The schema name, not null
	 */
	@Override
	public void disableValueConstraints(String schemaName) {
		Set<String> tableNames = getTableNames(schemaName);
		for (String tableName : tableNames) {
			disableValueConstraints(schemaName, tableName);
		}
	}

	// todo refactor (see oracle)

	/**
	 * Disables all value constraints (e.g. not null) on all tables in the schema
	 * 
	 * @param schemaName
	 *          The schema name, not null
	 * @param tableName
	 *          The table name, not null
	 */
	@SuppressWarnings("unchecked")
	protected void disableValueConstraints(String schemaName, String tableName) {
		ISQLHandler sqlHandler = getSQLHandler();

		// disable all unique constraints (check constraints are not
		// implemented)
		Set<String> constraintNames = sqlHandler
		    .getItemsAsStringSet(
		        "select constraintname from constraints where constrainttype in ('UNIQUE') AND tablename = '"
		            + tableName + "' and schemaname = '" + schemaName + "'",
		        getDataSource());
		for (String constraintName : constraintNames) {
			sqlHandler.executeUpdate("alter table "
			    + qualified(schemaName, tableName) + " drop key "
			    + quoted(constraintName), getDataSource());
		}

		// disable all not null constraints
		Set<String> notNullColumnNames = sqlHandler
		    .getItemsAsStringSet(
		        "select columnname from columns where nullable = 'NO' and mode <> 'KEY' and tablename = '"
		            + tableName + "' and schemaname = '" + schemaName + "'",
		        getDataSource());
		for (String notNullColumnName : notNullColumnNames) {
			// todo test length etc
			String columnType = sqlHandler.getItemAsString(
			    "select columntype from columns where schemaname = '" + schemaName
			        + "' and tablename = '" + tableName + "' and columnname = '"
			        + notNullColumnName + "'", getDataSource());
			sqlHandler.executeUpdate("alter table "
			    + qualified(schemaName, tableName) + " change column "
			    + quoted(notNullColumnName) + " " + quoted(notNullColumnName) + " "
			    + columnType + " NULL ", getDataSource());
		}
	}

	/**
	 * Gets the names of all columns of the given table.
	 * 
	 * @param schemaName
	 *          The schema name, not null
	 * @param tableName
	 *          The table, not null
	 * @return The names of the columns of the table with the given name
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getColumnNames(String schemaName, String tableName) {
		return getSQLHandler().getItemsAsStringSet(
		    "select columnname from columns where tablename = '" + tableName
		        + "' and schemaname = '" + schemaName + "'", getDataSource());
	}

	/**
	 * The database dialect supported by this db support class, not null
	 * 
	 * @return the database dialect supported by this db support class, not null
	 */
	@Override
	public String getSupportedDatabaseDialect() {
		return "maxdb";
	}

	/**
	 * Returns the names of all tables in the database.
	 * 
	 * @param The
	 *          schemaName, not null
	 * 
	 * @return The names of all tables in the database
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getTableNames(String schemaName) {
		return getSQLHandler().getItemsAsStringSet(
		    "select tablename from tables where schemaname = '" + schemaName
		        + "' and tabletype = 'TABLE'", getDataSource());
	}

	/**
	 * Retrieves the names of all the views in the database schema.
	 * 
	 * @return The names of all views in the database
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getViewNames(String schemaName) {
		return getSQLHandler().getItemsAsStringSet(
		    "select tablename from tables where schemaname = '" + schemaName
		        + "' and tabletype = 'VIEW'", getDataSource());
	}

	/**
	 * Retrieves the names of all the triggers in the database schema.
	 * 
	 * @return The names of all triggers in the database
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getTriggerNames(String schemaName) {
		return getSQLHandler().getItemsAsStringSet(
		    "select triggername from triggers where schemaname = '" + schemaName
		        + "'", getDataSource());
	}

	/**
	 * Gets the names of all identity columns of the given table.
	 * 
	 * @param tableName
	 *          The table, not null
	 * @return The names of the identity columns of the table with the given name
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set getIdentityColumnNames(String schemaName, String tableName) {
		// todo check, at this moment the PK columns are returned
		return getSQLHandler().getItemsAsStringSet(
		    "select columnname from columns where tablename = '" + tableName
		        + "' and mode = 'KEY' and schemaname = '" + schemaName + "'",
		    getDataSource());
	}

	@Override
	public void incrementIdentityColumnToValue(String schemaName,
	    String tableName, String identityColumnName, long identityValue) {
		getSQLHandler().executeUpdate(
		    "alter table " + qualified(schemaName, tableName)
		        + " AUTO_INCREMENT = " + identityValue, getDataSource());
	}

	/**
	 * Converts the given identifier to uppercase/lowercase
	 * 
	 * @param identifier
	 *          The identifier, not null
	 * @return The name converted to correct case if needed, not null
	 */
	@Override
	public String toCorrectCaseIdentifier(String identifier) {
		identifier = identifier.trim();
		String identifierQuoteString = getIdentifierQuoteString();
		if (identifier.startsWith(identifierQuoteString)
		    && identifier.endsWith(identifierQuoteString)) {
			identifier = identifier.substring(1, identifier.length() - 1);
		}
		EDBIndetifierCase storedIdentifierCase = getStoredIdentifierCase();
		if (storedIdentifierCase == EDBIndetifierCase.UPPER_CASE) {
			return identifier.toUpperCase(Locale.getDefault());
		} else if (storedIdentifierCase == EDBIndetifierCase.LOWER_CASE) {
			return identifier.toLowerCase(Locale.getDefault());
		} else {
			return identifier;
		}
	}

	/**
	 * Gets the column type suitable to store values of the Java
	 * <code>java.lang.Long</code> type.
	 * 
	 * @return The column type
	 */
	@Override
	public String getLongDataType() {
		return "FIXED(38)";
	}

	/**
	 * Triggers are supported.
	 * 
	 * @return True
	 */
	@Override
	public boolean supportsTriggers() {
		return true;
	}

	/**
	 * Identity columns are supported.
	 * 
	 * @return True
	 */
	@Override
	public boolean supportsIdentityColumns() {
		return true;
	}

	/**
	 * Cascade are supported.
	 * 
	 * @return True if database supported cascade
	 */
	@Override
	public boolean supportsCascade() {
		return true;
	}

}
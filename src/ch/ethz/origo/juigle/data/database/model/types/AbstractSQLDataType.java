package ch.ethz.origo.juigle.data.database.model.types;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/03/2010)
 * @since 0.1.0 (3/03/2010)
 * 
 */
public abstract class AbstractSQLDataType {

	/** MSSQL Data type */
	public static final int MSSQL = 0;
	/** ORACLE Data type */
	public static final int ORACLE = 1;
	/** MaxDB Data type */
	public static final int MAXDB = 2;
	/** FIREBIRD Data type */
	public static final int FIREBIRD = 3;
	/** MySQL Data type */
	public static final int MYSQL = 4;
	/** POSTGRESQL Data type */
	public static final int POSTGRESQL = 5;

	protected int size = -1;
	protected int precision = -1;
	protected int scale = -1;

	protected String syntaxType;

	protected String basicType;
	protected Class<?> javaType;

	/** type of actual database */
	protected int databaseType;

	/**
	 * CONSTRUCTOR: Main constructor Setting main attributes of SQLDataType
	 * 
	 */
	public AbstractSQLDataType() {
		basicType = setBasicType();
		javaType = setJavaType();
		syntaxType = basicType; // default
	}
	
	/**
	 * MSSQL data type syntax
	 * 
	 * @return MSSQL data type syntax
	 */
	abstract protected String getSyntaxMSSQL();
	
	abstract protected String getSyntaxORACLE();
	
	abstract protected String getSyntaxFIREBIRD();
	
	abstract protected String getSyntaxMYSQL();
	
	abstract protected String getSyntaxPOSTGRESQL();
	
	abstract protected String getSyntaxMaxDB();
	
	/**
	 * Standard overloaded method toString()
	 * 
	 * @return String
	 */
	public String toString() {
		return basicType + " -> " + syntaxType;
	}

	/**
	 * Set actual database for correct syntax of data type
	 * 
	 * @param databaseType
	 *          defines by SQLDataType.MSSQL, SQLDataType.ORACLE, etc.
	 */
	public void setDatabaseType(int databaseType) {
		this.databaseType = databaseType;
		assemblySyntax(); // change syntax
	}

	/**
	 * Get declaration syntax of data type. Depends of database type
	 * 
	 * @return syntax String
	 */
	abstract public String getSyntax();

	/**
	 * Get size of type
	 * 
	 * @return int size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Get precision of type
	 * 
	 * @return int precision
	 */
	public int getPrecision() {
		return precision;
	}

	/**
	 * Get scale of type
	 * 
	 * @return int scale
	 */
	public int getScale() {
		return scale;
	}

	/**
	 * Set Basic type of database data type
	 * 
	 * @return basic type String
	 */
	abstract public String setBasicType();

	/**
	 * Set java type
	 * 
	 * @return java type Class
	 */
	abstract public Class<?> setJavaType();

	/**
	 * Main method for assembly syntax by Database type
	 * 
	 */
	protected void assemblySyntax() {
		// switch by actual database
		switch (databaseType) {
		case MSSQL:
			syntaxType = getSyntaxMSSQL();
			break;
		case ORACLE:
			syntaxType = getSyntaxORACLE();
			break;
		case FIREBIRD:
			syntaxType = getSyntaxFIREBIRD();
			break;
		case MYSQL:
			syntaxType = getSyntaxMYSQL();
			break;
		case POSTGRESQL:
			syntaxType = getSyntaxPOSTGRESQL();
			break;
		case MAXDB:
			syntaxType = getSyntaxMaxDB();
			break;
		}

		if (syntaxType == null) { // not exist
			syntaxType = basicType;
		}
	}

}
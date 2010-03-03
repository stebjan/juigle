package ch.ethz.origo.juigle.data.database.model.types;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/03/2010)
 * @since 0.1.0 (3/03/2010)
 * @see AbstractSQLDataType
 * 
 */
public class SQLChar extends AbstractSQLDataType {
	
	public static final String CHAR_TYPE = "CHAR";
	
	public SQLChar(int size) {
		this.size = size;
	}

	@Override
	public String getSyntax() {
		return syntaxType + "(" + size + ")";
	}

	@Override
	protected String getSyntaxFIREBIRD() {
		return SQLChar.CHAR_TYPE;
	}

	@Override
	protected String getSyntaxMSSQL() {
		return SQLChar.CHAR_TYPE;
	}

	@Override
	protected String getSyntaxMYSQL() {
		return null;
	}

	@Override
	protected String getSyntaxMaxDB() {
		return SQLChar.CHAR_TYPE;
	}

	@Override
	protected String getSyntaxORACLE() {
		return SQLChar.CHAR_TYPE;
	}

	@Override
	protected String getSyntaxPOSTGRESQL() {
		return SQLChar.CHAR_TYPE;
	}

	@Override
	public String setBasicType() {
		return SQLChar.CHAR_TYPE;
	}

	@Override
	public Class<?> setJavaType() {
		return String.class;
	}

}

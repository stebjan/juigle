package ch.ethz.origo.juigle.data;

/**
 * Class which contains error constants. Value of these error constants is error
 * key code to the errors properties file. If constant end with <code>P</code>
 * so this means that error code contains one parameter. If error code has for
 * example <code>P2</code> - means that error code has 2 parameters.
 * 
 * @author Vaclav Souhrada (v.souhrad at gmail.com)
 * @version 0.1.0.00 (10/27/2010)
 * @since 2.0.0 (10/27/2010)
 * 
 */
public class ErrorCodes {

	public static final String UNEXPECTED_EXCEPTION = "JG000";
	public static final String FILE_NOT_FOUND_P = "JG001:";
	public static final String CANNOT_READ_CFG_FILE_P = "JG002:";
	public static final String RESOURCE_BD_KEY_OR_FILE_NOT_FND_P2 = "JG003:";
	public static final String NOT_INSTANCE_CREATED = "JG004";
	public static final String NOT_PERSPECTIVE_ADDED_P = "JG005:";
	public static final String NOT_PROJECT_OPEN = "JG006";
	public static final String NOT_PROJECT_SAVED = "JG007";
	public static final String NOT_PROJECT_CLOSED = "JG008";
	public static final String PROJECT_ERROR_SAVING_P = "JG009:";
	public static final String FILE_NOT_FOUND_IO_ERROR = "JG010";
	public static final String PROJECT_FILE_CORRUPTED_P = "JG011:";
	public static final String CALL_SAVE_METHOD_PRJ_NOT_OPN = "JG012";
	public static final String NOT_READ_JUIGLE_IMAGES = "JG013";
	public static final String IMG_NOT_FOUND_IO_ERROR = "JG014";
	public static final String IMG_NOT_FOUND_P = "JG015:";
	// public static final String = "JG016";
	// public static final String = "JG017";
	public static final String PLUGIN_NOT_FOUND_P = "JG018:";
	public static final String WRONG_PLUGIN_XML_SYNTAX = "JG019";
	public static final String DB_NOT_CREATED_P = "JG020:";
	public static final String CAN_NOT_REMOVE_CURRENT_DB_NOT_EXIST = "JG021";
	public static final String CAN_NOT_REMOVE_DB_WROND_DDL = "JG022";
	public static final String TABLE_NOT_ADDED_TO_DB_P = "JG023:";
	public static final String CFG_PERSPECTIVE_FILE_NOT_FOUND_P = "JG024:";
	public static final String FILE_NOT_SPECIFIC = "JG025";
	public static final String UPDATE_DB_ERROR_P = "JG026:";
	public static final String ERROR_DB_EXECUTE_STATEMENT_P = "JG027:";
	public static final String NO_ITEM_VALUE_FOUND_P = "JG028:";
	public static final String CONNECTION_ERROR = "JG029";
	public static final String PERSPECTIVE_NOT_LOADED_P = "JG030:";
	public static final String UNSUPPORTED_PERSPECTIVE_FILE_P = "JG031:";
	public static final String DB_INFO_CAN_NOT_BE_NULL = "JG032";
	public static final String PLUGIN_INSTANCE_IS_NULL = "JG033";

}

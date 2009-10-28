package ch.ethz.origo.juigle.application;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 10/18/09
 * @since 0.1.0 (05/18/09)
 */
public class PropertiesLoader {

	public static final int ERRORS = 0;
	
	protected static Logger logger = Logger.getLogger(PropertiesLoader.class);

	/** Only for serialization */
	private static final long serialVersionUID = 6089368520199982242L;

	/**
	 * 
	 * @param key
	 * @param type
	 * @return
	 * @version 0.1.0 (10/18/09)
	 * @since 0.1.0
	 */
	public static String getProperty(String key, int type) {
		// TODO write errors by default locale
		Properties props = PropertiesLoader.loadFile(PropertiesLoader.getFileNameByType(type) + "_en.properties");
		return props.getProperty(key);
	}
	
	/**
	 * 
	 * @param key
	 * @param file
	 * @return
	 * @version 0.1.0 (10/18/09)
	 * @since 0.1.0
	 */
	public static String getProperty(String key, String file) {
		Properties props = PropertiesLoader.loadFile(file);
		return props.getProperty(key);
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 * @version 0.1.0 (10/18/09)
	 * @since 0.1.0
	 */
	private static Properties loadFile(String name) {
		Properties props = new Properties();
		try {
			props.load(ClassLoader.getSystemResourceAsStream(name));
		} catch (IOException e) {
			PropertiesLoader.logger.warn("Properties key or file not found.", e);
		}
		return props;
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 * @version 0.1.0 (10/18/09)
	 * @since 0.1.0
	 */
	private static String getFileNameByType(int type) {
		switch(type) {
			case PropertiesLoader.ERRORS:
				return "errors";
			default:
				break;
		}
		return "";
	}
	
}
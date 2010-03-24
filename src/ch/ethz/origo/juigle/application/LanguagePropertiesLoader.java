package ch.ethz.origo.juigle.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import ch.ethz.origo.juigle.application.exception.PropertiesException;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/24/2010)
 * @since 0.1.0 (3/24/2010)
 *
 */
public class LanguagePropertiesLoader {
	
	/** Configuration file */
	private static final String PROPERTY_FILE = "config/lang.properties";
	
	private static boolean isLoaded = false;

	/** Contains the properties */
	public static Properties properties = new Properties();

	/**
	 * Load properties
	 * 
	 * @throws PropertiesException
	 */
	public static void loadProperties() throws PropertiesException {
		if (!LanguagePropertiesLoader.isLoaded) {
			try {
				properties.load(new FileInputStream(new File(PROPERTY_FILE)));
				PropertyConfigurator.configure(properties);
			} catch (IOException e) {
				throw new PropertiesException(LanguagePropertiesLoader.class.getName()
						+ " - cannot read config properties", e);
			}
			isLoaded = true;
		}
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public static String getApplicationLocale() {
		return properties.getProperty("default.lang");
	}

}

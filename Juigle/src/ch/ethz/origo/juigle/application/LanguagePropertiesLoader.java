/*
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

/*
 *  
 *    Copyright (C) 2009 - 2010 
 *    							University of West Bohemia, 
 *                  Department of Computer Science and Engineering, 
 *                  Pilsen, Czech Republic
 */
package ch.ethz.origo.juigle.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import ch.ethz.origo.juigle.application.exception.PropertiesException;
import ch.ethz.origo.juigle.data.ErrorCodes;

/**
 * Load property of languages.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.2.0.01 (10/03/2010)
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
	 * @since 0.1.0
	 * @throws PropertiesException
	 */
	public static void loadProperties() throws PropertiesException {
		if (!LanguagePropertiesLoader.isLoaded) {
			try {
				properties.load(new FileInputStream(new File(PROPERTY_FILE)));
				PropertyConfigurator.configure(properties);
			} catch (IOException e) {
				throw new PropertiesException(ErrorCodes.CANNOT_READ_CFG_FILE_P + PROPERTY_FILE, e);
			}
			isLoaded = true;
		}
	}

	/**
	 * Return default locale of the application
	 * 
	 * @since 0.1.0
	 * @return default locale of the application
	 */
	public static String getApplicationLocale() {
		return properties.getProperty("default.lang");
	}

	/**
	 * Write current language to property file. This language will be now
	 * defaulted for application
	 * 
	 * @param lang
	 *          language name which will be written to property file
	 * @since 0.1.0
	 */
	public static void writeLangProperty(String lang) {
		try {
			FileOutputStream out = new FileOutputStream(new File(PROPERTY_FILE));
			properties.setProperty("default.lang", lang);
			LanguagePropertiesLoader.properties.store(out, "---Language changed---");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method return <i>true</i>, if properties was loaded, else return
	 * <i>false</i>.
	 * 
	 * @version 0.1.0 (8/29/2010)
	 * @since 0.2.0 (8/29/2010)
	 * 
	 * @return true if language properties was loaded
	 * 
	 */
	public static boolean isLoad() {
		return LanguagePropertiesLoader.isLoaded;
	}

}

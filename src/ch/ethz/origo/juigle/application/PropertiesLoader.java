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

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Properties loader
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.1 (05/15/2010)
 * @since 0.1.0 (05/18/09)
 * 
 */
public class PropertiesLoader {

	public static final int ERRORS = 0;
	
	protected static Logger logger = Logger.getLogger(PropertiesLoader.class);

	/** Only for serialization */
	private static final long serialVersionUID = 6089368520199982242L;

	/**
	 * Return property
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
	 * Return property
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
	 * Load property from file path - name
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
	 * Return property which contains file name by type
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
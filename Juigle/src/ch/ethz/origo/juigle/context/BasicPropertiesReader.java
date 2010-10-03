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

/*		BasicPropertiesReader.java
 *  
 *    Copyright (C) 2009 - 2010 
 *    							University of West Bohemia, 
 *                  Department of Computer Science and Engineering, 
 *                  Pilsen, Czech Republic
 */
package ch.ethz.origo.juigle.context;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import ch.ethz.origo.juigle.application.exception.PropertiesException;

/**
 * Basic reader of the <code>.properties</code> files.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0.00 (10/03/2010)
 * @since 2.0.0 (10/03/2010)
 *
 */
public class BasicPropertiesReader {
	
	 /** Contains the properties */
	private static Properties properties = new Properties();
	private static String filePath;
	
	protected boolean isLoaded;
	
	/**
	 * Load properties
	 * 
	 * @throws PropertiesException
	 */
	public static void loadProperties(String filePath) throws PropertiesException {
		if (BasicPropertiesReader.filePath == null || !BasicPropertiesReader.filePath.equals(filePath)) {
			BasicPropertiesReader.filePath = filePath;
			try {
				properties.load(new FileInputStream(new File(filePath)));
				PropertyConfigurator.configure(properties);
			} catch (IOException e) {
				throw new PropertiesException("JG002:" + filePath, e);
			}
		}
	}
	
	public static String getPropertyValue(String key) {
		return properties.getProperty(key);
	}
	
	public static String[] getPropertyValues(String key, String separator) {
		return properties.getProperty(key).trim().split(separator);
	}

}
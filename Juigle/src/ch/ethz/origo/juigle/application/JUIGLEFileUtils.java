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

/*		JUIGLEFileUtils.java
 *  
 *    Copyright (C) 2009 - 2010 
 *    							University of West Bohemia, 
 *                  Department of Computer Science and Engineering, 
 *                  Pilsen, Czech Republic
 */
package ch.ethz.origo.juigle.application;

import java.io.File;

/**
 * Class contains some useful methods for working with files
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.1.00 (10/16/2010)
 * @since 1.0.1 (05/22/2010)
 * 
 */
public class JUIGLEFileUtils {

	/** Extension of the XML file */
	public static final String XML_FILE_EXTENSION = ".xml";
  /** Extension of the Properties file */
	public static final String PROPERTIES_FILE_EXTENSION = ".properties";

	/**
	 * Return same absolute path as file which was added as parameter
	 * 
	 * @param file
	 *          instance of file from we want to return his absolute file
	 * @return same absolute path as file which was added as parameter
	 * @version 0.1.0.00 (05/22/2010)
	 * @since 1.0.1 (05/22/2010)
	 */
	public static String getSameAbsolutePathAsOtherFile(File file) {
		String fileName = file.getName();
		String absolutePath = file.getAbsolutePath();
		return absolutePath.substring(0,
				(absolutePath.length() - fileName.length()));
	}

	/**
	 * Return user directory as String
	 * 
	 * @return user directory as String
	 * @version 0.1.0.00 (10/16/2010)
	 * @since 0.1.1.00 (10/16/2010)
	 */
	public static String getUserDirectory() {
		return System.getProperty("user.dir");
	}

	/**
	 * Return user home directory as String
	 * 
	 * @return user home directory as String
	 * @version 0.1.0.00 (10/16/2010)
	 * @since 0.1.1.00 (10/16/2010)
	 */
	public static String getUserHome() {
		return System.getProperty("user.home");
	}

	/**
	 * Return absolute path of the file
	 * 
	 * @param file
	 *          reference to file
	 * @return absolute path of the file
	 * @version 0.1.0.00 (10/16/2010)
	 * @since 0.1.1.00 (10/16/2010)
	 */
	public static String absolute(File file) {
		if (file.isAbsolute()) {
			return file.getPath();
		} else {
			return getUserDirectory() + File.separator + file.getPath();
		}
	}

}

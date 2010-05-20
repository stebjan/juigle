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

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * This class provide parser for Exception messages.
 * 
 * @author Vaclav Souhrada
 * @version 0.1.2 (3/20/2010)
 * @since 0.1.0 (1/30/2010)
 * 
 */
public class JUIGLEErrorParser {

	/**
	 * Parse and return errors messages from JUIGLE default file.
	 * 
	 * @param errorCode
	 *          error key
	 * @return errors messages from JUIGLE default file
	 */
	public static String getJUIGLEErrorMessage(String errorCode) {
		return JUIGLEErrorParser.parseMessage(errorCode,
				"ch.ethz.origo.juigle.data.errors");
	}

	/**
	 * Parse and return errors messages from given path.
	 * 
	 * @param errorCode code/key of the error
	 * @param filePath path of file where are errors messages
	 * @return errors messages from given path.
	 */
	public static String getErrorMessage(String errorCode, String filePath) {
		return JUIGLEErrorParser.parseMessage(errorCode, filePath);
	}

	private static String parseMessage(String errorCode, String filePath) {
		String[] args = errorCode.split(":");
		String pattern = ResourceBundle.getBundle(filePath).getString(args[0]);

		if (args.length > 1) {
			MessageFormat formatter = new MessageFormat(pattern);
			Object[] arguments = new Object[args.length - 1]; // ignore first position
			for (int i = 1; i < args.length; i++) {
				arguments[i - 1] = args[i];
			}
			return formatter.format(arguments);
		} else {
			return pattern;
		}
	}

}
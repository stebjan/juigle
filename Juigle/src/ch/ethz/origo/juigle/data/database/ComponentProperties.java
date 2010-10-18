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
package ch.ethz.origo.juigle.data.database;

/**
 * Extended component properties. Additional information for creation of
 * components
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0.00 (10/13/2010)
 * @since 2.0.0 (10/13/2010)
 */
public class ComponentProperties {

	/** wrapped table, columns; E.g. Firebird 1.5 version **/
	private String wrappedCommands = ""; // default

	/**
	 * Get wrapped command syntax: "table1", "column1"
	 * 
	 * @return wrapped command String
	 */
	public String getWrappedCommands() {
		return wrappedCommands;
	}

	/**
	 * Set Wrapped prefix and suffix
	 * 
	 * @param wrappedCommands
	 *          prefix/suffix String
	 */
	public void setWrappedCommands(String wrappedCommands) {
		this.wrappedCommands = wrappedCommands;
	}

}

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
package ch.ethz.origo.juigle.db_prepare;

/**
 * Utils for database framework
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (1/24/2010)
 * @since 0.1.0 (1/24/2010)
 * 
 */
public class DBUtils {

	private static int CONSTRAINT;

	/**
	 * Return name of constraint. Returned name is consist from name of constraint
	 * and name of table. As suffix is constraint number.
	 * 
	 * @param name
	 *          of constraint
	 * @param tableName
	 *          of table (constraint owner)
	 * @return constraint name
	 */
	public static String getAutoConstraintName(String name, String tableName) {
		return tableName + "_" + name + "_" + (DBUtils.CONSTRAINT++);
	}

}
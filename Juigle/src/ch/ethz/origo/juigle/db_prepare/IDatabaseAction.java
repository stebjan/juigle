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

import java.util.List;

/**
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (1/24/2010)
 * @since 0.1.0 (1/24/2010)
 * 
 */
public interface IDatabaseAction {

	/**
	 * Execute database command such as INSERT, DELETE, UPDATE...
	 * 
	 * @param command database (SQL) command
	 * @version 0.1.0 (1/24/2010)
	 * @since 0.1.0 (1/24/2010)
	 */
	public void execute(String command);
	
	/**
	 * Execute database commands such as INSERT, DELETE, UPDATE...
	 * 
	 * @param commands list of SQL commands
	 * @version 0.1.0 (1/24/2010)
	 * @since 0.1.0 (1/24/2010)
	 * 
	 */
	public void execute(List<String> commands);
}

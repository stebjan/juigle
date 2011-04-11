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
package ch.ethz.origo.juigle.prezentation.tables.model;

import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

import ch.ethz.origo.juigle.application.ILanguage;
import ch.ethz.origo.juigle.application.exception.DataStoreException;

/**
 * Construct localized table model for Tree tables.
 * 
 * @author Vaclav Souhrada
 * @version 0.2.1 (2/08/2010)
 * @since 0.1.0 (11/25/09)
 * @see DefaultTreeTableModel
 * @see ILanguage
 * 
 */
public abstract class JUIGLETreeTableModel extends DefaultTreeTableModel implements ILanguage {

	/**
	 * Insert values to table model
	 * 
	 * @throws DataStoreException
	 * @since 0.1.0
	 */
	public abstract void fillByValues() throws DataStoreException;
	
	@Override
	public abstract String getColumnName(int column);

}

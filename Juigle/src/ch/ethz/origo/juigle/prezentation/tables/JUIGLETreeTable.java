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
package ch.ethz.origo.juigle.prezentation.tables;

import javax.swing.plaf.TableUI;

import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.TreeTableModel;

/**
 * Create instance of <code>JXTreeTable</code>. All tree table should be created
 * as instance of this class or should extended it.
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (11/25/09)
 * @since 0.1.0 (11/25/09)
 * @see JXTreeTable
 */
public class JUIGLETreeTable extends JXTreeTable {

	/** Only for serialization */
	private static final long serialVersionUID = -5420004135021438115L;

	/**
	 * Default constructor
	 */
	public JUIGLETreeTable() {
		super();
	}

	/**
	 * Construct table with specified model
	 * 
	 * @param treeModel
	 *          table model
	 */
	public JUIGLETreeTable(TreeTableModel treeModel) {
		super(treeModel);
	}

	/**
	 * Create table with specified own user interface
	 * 
	 * @param ui
	 *          own user interface
	 */
	public JUIGLETreeTable(TableUI ui) {
		this();
		setUI(ui);
	}

	/**
	 * Construct table with specified model and user interface
	 * 
	 * @param model
	 *          table model
	 * @param ui
	 *          own user interface
	 * 
	 */
	public JUIGLETreeTable(TreeTableModel model, TableUI ui) {
		this(model);
		setUI(ui);
	}

}

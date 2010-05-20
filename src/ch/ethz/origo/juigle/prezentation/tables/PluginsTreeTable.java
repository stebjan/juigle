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

import javax.swing.tree.DefaultTreeCellRenderer;

import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;

import ch.ethz.origo.juigle.application.exception.DataStoreException;
import ch.ethz.origo.juigle.data.tables.PluginRecord;
import ch.ethz.origo.juigle.prezentation.tables.model.JUIGLETreeTableModel;
import ch.ethz.origo.juigle.prezentation.tables.model.PluginTreeTableModel;

/**
 * Create instance of <code>JXTreeTable</code>.This instance contains table
 * model filled by all installed plugins in the application.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.1 (4/05/2010)
 * @since 0.1.0 (3/28/2010)
 * @see JUIGLETreeTableModel
 * 
 */
public class PluginsTreeTable extends JUIGLETreeTable {

	/** Only for serialization */
	private static final long serialVersionUID = 5816119341806461897L;

	public PluginsTreeTable() throws DataStoreException {
		super();
		initTable();
	}

	private void initTable() throws DataStoreException {
		JUIGLETreeTableModel ttm = new PluginTreeTableModel();
		ttm.fillByValues();
		setTreeTableModel(ttm);
		setTreeCellRenderer(new DefaultTreeCellRenderer() {
			/** Only for serialization */
			private static final long serialVersionUID = 1L;

			public java.awt.Component getTreeCellRendererComponent(
					javax.swing.JTree tree, Object value, boolean sel, boolean expanded,
					boolean leaf, int row, boolean hasFocus) {
				super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
						row, hasFocus);
				if (value instanceof DefaultMutableTreeTableNode) {
					DefaultMutableTreeTableNode node = (DefaultMutableTreeTableNode) value;
					PluginRecord plug = null;
					if (node.getUserObject() instanceof PluginRecord) {
						plug = (PluginRecord) node.getUserObject();
					}
					if (node.isLeaf()) {
						setText(plug.getPlugin().getPluginName());
						if (node.getParent() == tree.getModel().getRoot()) {
							setIcon(getDefaultClosedIcon());
						}
					} else {
						setText(plug.getCategory());
					}
				}
				return this;
			};
		});
		addHighlighter(HighlighterFactory.createAlternateStriping());
	}

}
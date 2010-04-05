package ch.ethz.origo.juigle.prezentation.tables;

import javax.swing.tree.DefaultTreeCellRenderer;

import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;

import ch.ethz.origo.juigle.application.exception.DataStoreException;
import ch.ethz.origo.juigle.data.tables.PluginRecord;
import ch.ethz.origo.juigle.data.tables.model.JUIGLETreeTableModel;
import ch.ethz.origo.juigle.data.tables.model.PluginTreeTableModel;

/**
 * Create instance of <code>JXTreeTable</code>. This instance contains table
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
					PluginRecord plug = (PluginRecord) node.getUserObject();
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
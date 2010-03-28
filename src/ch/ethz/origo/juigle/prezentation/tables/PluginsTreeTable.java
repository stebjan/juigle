package ch.ethz.origo.juigle.prezentation.tables;

import javax.swing.tree.DefaultTreeCellRenderer;

import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;

import ch.ethz.origo.juigle.application.exception.DataStoreException;
import ch.ethz.origo.juigle.data.tables.PluginRecord;
import ch.ethz.origo.juigle.data.tables.model.JUIGLETreeTableModel;
import ch.ethz.origo.juigle.data.tables.model.PluginTreeTableModel;


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
		setEditable(false);
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
					} else if (!node.isLeaf()) {
						setText(plug.getCategory());
					}
				}
				return this;
			};
		});
		
	}


}

package ch.ethz.origo.juigle.data.tables.model;

import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;

import ch.ethz.origo.juigle.application.exception.DataStoreException;
import ch.ethz.origo.juigle.application.exception.JUIGLELangException;
import ch.ethz.origo.juigle.data.tables.PluginRecord;
import ch.ethz.origo.juigle.plugin.Pluggable;
import ch.ethz.origo.juigle.plugin.PluginEngine;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.1 (4/05/2010)
 * @since 0.1.0 (3/28/2010)
 * @see JUIGLETreeTableModel
 * 
 */
public class PluginTreeTableModel extends JUIGLETreeTableModel {

	private static final int NUM_OF_COLUMNS = 8;

	/** Only for serialization */
	private static final long serialVersionUID = -3327739502180192368L;

	private PluginEngine pluginEngine;

	@Override
	public void fillByValues() throws DataStoreException {
		this.pluginEngine = PluginEngine.getInstance();
		String prevLast = "";
		String currentLast = "";

		PluginTreeTableNode currentNameNode = null;
		DefaultMutableTreeTableNode root = new PluginTreeTableNode(
				new PluginRecord());

		for (Pluggable plugin : pluginEngine.getAllPluggables()) {
			PluginRecord pr = new PluginRecord(plugin);
			currentLast = pr.getCategory();
			if (currentLast.equals(prevLast)) {
				currentNameNode.add(new PluginTreeTableNode(pr));
			} else {
				if (currentNameNode != null) {
					root.add(currentNameNode);
				}
				PluginRecord fr = new PluginRecord();
				fr.setCategory(currentLast);
				currentNameNode = new PluginTreeTableNode(fr);
				currentNameNode.add(new PluginTreeTableNode(pr));
				prevLast = currentLast;
				root.add(currentNameNode);
			}
		}
		setRoot(root);
	}

	@Override
	public int getColumnCount() {
		return PluginTreeTableModel.NUM_OF_COLUMNS;
	}

	@Override
	public String getColumnName(int column) {
		String name = "";
		switch (column) {
		case 0:
			name = "Name";
			break;
		case 1:
			name = "Version";
			break;
		case 2:
			name = "Author";
			break;
		case 3:
			name = "Enable";
			break;
		case 4:
			name = "Hidden";
			break;
		case 5:
			name = "Update";
			break;
		case 6:
			name = "Compatible";
			break;
		case 7:
			name = "Description";
			break;
		default:
			name = "n/a";
			break;
		}
		return name;
	}

	@Override
	public String getResourceBundlePath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocalizedResourceBundle(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setResourceBundleKey(String key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateText() throws JUIGLELangException {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @author Vaclav Souhrada
	 * @version 0.1.0 (3/28/2010)
	 * @since 0.1.0 (3/28/2010)
	 * @see DefaultMutableTreeTableNode
	 * 
	 */
	class PluginTreeTableNode extends DefaultMutableTreeTableNode {

		public PluginTreeTableNode(PluginRecord record) {
			super(record);
		}

		@Override
		public Object getValueAt(int column) {
			Object toBeDisplayed = "";
			if (getUserObject() instanceof PluginRecord) {
				PluginRecord fr = (PluginRecord) getUserObject();
				if (fr.getPlugin() != null) {
					switch (column) {
					case 0:
						toBeDisplayed = fr.getPlugin().getPluginName();
						break;
					case 1:
						toBeDisplayed = fr.getPlugin().getPluginVersion();
						break;
					case 2:
						toBeDisplayed = fr.getPlugin().getAuthorName();
						break;
					case 3:
						toBeDisplayed = pluginEngine.isEnabled(fr.getPlugin());
						break;
					case 4:
						toBeDisplayed = pluginEngine.isHidden(fr.getPlugin());
						break;
					case 5:
						toBeDisplayed = pluginEngine.isUpdateEnabled(fr.getPlugin());
						break;
					case 6:
						toBeDisplayed = pluginEngine.isCompatible(fr.getPlugin());
						break;
					case 7:
						if (fr.getPlugin().getPluginBasicDescription() != null) {
							toBeDisplayed = fr.getPlugin().getPluginBasicDescription();
						} else {
							toBeDisplayed = "";
						}
					default:
						break;
					}
				} else {
					switch (column) {
					case 0:
						toBeDisplayed = fr.getCategory();
						break;
					default:
						break;
					}
				}
			}
			return toBeDisplayed;
		}

		@Override
		public int getColumnCount() {
			return PluginTreeTableModel.NUM_OF_COLUMNS;
		}

	}

	@Override
	public boolean isCellEditable(final Object node, final int col) {
		switch (col) {
		case 3:
		case 4:
		case 5:
			return true;
		default:
			return false;
		}
	}

	@Override
	public Class<?> getColumnClass(final int col) {
		switch (col) {
		case 3:
		case 4:
		case 5:
		case 6:
			return Boolean.class;
		default:
			return String.class;
		}
	}

}

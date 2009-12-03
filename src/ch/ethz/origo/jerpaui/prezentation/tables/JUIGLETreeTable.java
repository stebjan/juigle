package ch.ethz.origo.juigle.prezentation.tables;

import javax.swing.plaf.TableUI;

import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.TreeTableModel;

/**
 * 
 * @author Vaclav Souhrada 
 * @version 0.1.0 (11/25/09)
 * @since 0.1.0 (11/25/09)
 * @see JXTreeTable
 */
public class JUIGLETreeTable extends JXTreeTable {

	/** Only for serialization */
	private static final long serialVersionUID = -5420004135021438115L;
	
	public JUIGLETreeTable() {
		super();
	}
	
	public JUIGLETreeTable(TreeTableModel treeModel) {
		super(treeModel);
	}
	
	public JUIGLETreeTable(TableUI ui) {
		this();
		setUI(ui);
	}
	
	public JUIGLETreeTable(TreeTableModel model, TableUI ui) {
		this(model);
		setUI(ui);
	}
	
}

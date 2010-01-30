package ch.ethz.origo.juigle.prezentation.dialogs;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JDialog;

import org.jdesktop.swingx.JXDialog;
import org.jdesktop.swingx.action.BoundAction;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 09/06/09
 * @since 0.1.0 (05/18/09)
 * @see JXDialog
 * @see JDialog
 */
public abstract class JUIGLEDialog extends JXDialog {

	/** Only for serialization */
	private static final long serialVersionUID = 8514794546509775244L;

	/**
	 * 
	 */
	public JUIGLEDialog() {
		super(null);
	}

	/**
	 * Creates a non-modal dialog with the given component as content and without
	 * specified owner. A shared, hidden frame will be set as the owner of the
	 * dialog.
	 * <p>
	 * 
	 * @param content
	 *          the component to show and to auto-configure from.
	 */
	public JUIGLEDialog(JComponent content) {
		super(content);
	}

	/**
	 * Creates a non-modal dialog with the given component as content and the
	 * specified <code>Frame</code> as owner.
	 * <p>
	 * 
	 * @param frame
	 *          the owner
	 * @param content
	 *          the component to show and to auto-configure from.
	 */
	public JUIGLEDialog(Frame frame, JComponent content) {
		super(frame, content);
	}

	/**
	 * Creates a non-modal dialog with the given component as content and the
	 * specified <code>Dialog</code> as owner.
	 * <p>
	 * 
	 * @param dialog
	 *          the owner
	 * @param content
	 *          the component to show and to auto-configure from.
	 */
	public JUIGLEDialog(Dialog dialog, JComponent content) {
		super(dialog, content);
	}

	
	protected void setContent(JComponent content) {
		if (this.content != null) {
			throw new IllegalStateException("content must not be set more than once");
		}
		initActions();
		Action contentCloseAction = content.getActionMap()
				.get(CLOSE_ACTION_COMMAND);
		if (contentCloseAction != null) {
			putAction(CLOSE_ACTION_COMMAND, contentCloseAction);
		}
		Action contentExecuteAction = content.getActionMap().get(
				EXECUTE_ACTION_COMMAND);
		if (contentExecuteAction != null) {
			putAction(EXECUTE_ACTION_COMMAND, contentExecuteAction);
		}
		this.content = content;
		build();
		setTitleFromContent();
	}

	private void build() {
		JComponent contentBox = new Box(BoxLayout.PAGE_AXIS);
		contentBox.add(content);
		JComponent buttonPanel = createButtonPanel();
		contentBox.add(buttonPanel);
		contentBox.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
		// content.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		// fieldPanel.setAlignmentX();
		// buttonPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		add(contentBox);

	}

	private void initActions() {
		Action defaultAction = createCloseAction();
		putAction(CLOSE_ACTION_COMMAND, defaultAction);
		putAction(EXECUTE_ACTION_COMMAND, defaultAction);
	}

	private Action createCloseAction() {
		String actionName = getUIString(CLOSE_ACTION_COMMAND);
		BoundAction action = new BoundAction(actionName, CLOSE_ACTION_COMMAND);
		action.registerCallback(this, "doClose");
		return action;
	}
	
	/**
   * Convenience wrapper to access rootPane's actionMap.
   * 
   * @param key
   * @param action
   */
  private void putAction(Object key, Action action) {
      getRootPane().getActionMap().put(key, action);
  }
  
  protected Point getCenterPosition(Dimension component) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();   
		//Calculate the frame location  
		int x = (screenSize.width - component.width) / 2;  
		int y = (screenSize.height - component.height) / 2;
		return new Point(x, y);
	}

}

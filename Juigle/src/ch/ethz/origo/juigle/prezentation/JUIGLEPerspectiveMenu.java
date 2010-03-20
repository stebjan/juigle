package ch.ethz.origo.juigle.prezentation;

import java.awt.image.BufferedImage;
import java.util.ResourceBundle;

import javax.swing.Action;
import javax.swing.UIManager;

import nezarazeno.Utils;

import org.jdesktop.swingx.JXCollapsiblePane;

import ch.ethz.origo.juigle.application.exception.JUIGLEMenuException;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 08/14/09
 * @since 0.1.0 (05/18/09)
 *
 */
public class JUIGLEPerspectiveMenu extends JUIGLEMenu {

	/** Only for serialization */
	private static final long serialVersionUID = -4279622887648599046L;
	
	private JUIGLEMenuItem headerCollapseItem;
	private JUIGLEMenuItem footerrCollapseItem;
	
	/**
	 * Create <code>JUIGLE Menu</code>> on specific position.
	 * 
	 * @param position
	 */
	public JUIGLEPerspectiveMenu(String position) {
		super(position);
	}

	/**
	 * Create <code>JUIGLE Menu</code>> on specific position
	 * and with localized text
	 * 
	 * @param position
	 * @param resourcePath
	 */
	public JUIGLEPerspectiveMenu(String position, String resourcePath) {
		super(position, resourcePath);
	}
	
	public void addHeaderHideButton(boolean showText) {
			headerCollapseItem = new JUIGLEMenuItem();
			headerCollapseItem.showText(false);

		Action headerCollpsAction = JUIGLEFrame.headerCoollapse.getActionMap().get(
				JXCollapsiblePane.TOGGLE_ACTION);

		// use the collapse/expand icons from the JTree UI
		headerCollpsAction.putValue(JXCollapsiblePane.COLLAPSE_ICON, UIManager
				.getIcon("Tree.expandedIcon"));
		headerCollpsAction.putValue(JXCollapsiblePane.EXPAND_ICON, UIManager
				.getIcon("Tree.collapsedIcon"));

		headerCollapseItem.setAction(headerCollpsAction);
		headerCollapseItem.setResourceBundleKey("juigle.buttons.hide.header");
		this.addItem(headerCollapseItem);
	}

	public void addHeaderHideButton(BufferedImage image)
			throws JUIGLEMenuException {

	}

	public void addFooterHideButton(boolean showText) {
			footerrCollapseItem = new JUIGLEMenuItem();
			footerrCollapseItem.showText(false);

		Action footerCollpsAction = JUIGLEFrame.footerCollapse.getActionMap().get(
				JXCollapsiblePane.TOGGLE_ACTION);
		// use the collapse/expand icons from the JTree UI
		footerCollpsAction.putValue(JXCollapsiblePane.COLLAPSE_ICON, UIManager
				.getIcon("Tree.expandedIcon"));
		footerCollpsAction.putValue(JXCollapsiblePane.EXPAND_ICON, UIManager
				.getIcon("Tree.collapsedIcon"));

		footerrCollapseItem.setAction(footerCollpsAction);
		footerrCollapseItem.setResourceBundleKey("juigle.buttons.hide.footer");
		this.addItem(footerrCollapseItem);
	}

	public void addFooterHideButton(BufferedImage image)
			throws JUIGLEMenuException {

	}
	
}

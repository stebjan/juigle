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
package ch.ethz.origo.juigle.prezentation.menu;

import java.awt.image.BufferedImage;

import javax.swing.Action;
import javax.swing.UIManager;

import org.jdesktop.swingx.JXCollapsiblePane;

import ch.ethz.origo.juigle.application.exception.JUIGLEMenuException;
import ch.ethz.origo.juigle.prezentation.JUIGLEFrame;

/**
 * Menu which is added to perspectives (If programmer want).
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.1 (3/21/2010)
 * @since 0.1.0 (05/18/09)
 * @see JUIGLEMenu
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
	
	/**
	 * Add hide button which hide Header of application.
	 * @param showText true - if button have to show text
	 */
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

	/**
	 * Add Header hide button with specified image
	 * @param image of button
	 * @throws JUIGLEMenuException
	 */
	public void addHeaderHideButton(BufferedImage image)
			throws JUIGLEMenuException {

	}

	/**
	 *  Add button to persp. menu which hidden FOOTER panel
	 * @param showText true - if button have to show text
	 */
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

	/**
	 * Add Footer hide button with specified image
	 * @param image
	 * @throws JUIGLEMenuException
	 */
	public void addFooterHideButton(BufferedImage image)
			throws JUIGLEMenuException {

	}
	
}

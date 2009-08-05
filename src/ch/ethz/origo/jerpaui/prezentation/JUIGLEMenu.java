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
 *    JUIGLEMenu.java
 *    Copyright (C) 2009 University of West Bohemia, 
 *                       Department of Computer Science and Engineering, 
 *                       Pilsen, Czech Republic
 */

package ch.ethz.origo.jerpaui.prezentation;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import nezarazeno.ImageSeparator;
import nezarazeno.JUIGLEMenuException;

import org.jdesktop.swingx.JXButton;

import com.jhlabs.image.GlowFilter;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 07/16/09
 * @since 0.1.0 (05/18/09)
 */
public class JUIGLEMenu extends JToolBar {

	/** Only for serialization */
	private static final long serialVersionUID = 744283918627175663L;
	
	private GlowFilter glow = new GlowFilter();

	/** Menu location on top of panel */
	public static final String MENU_LOCATION_TOP = BorderLayout.NORTH;
	/** Menu location on left side of panel */
	public static final String MENU_LOCATION_LEFT = BorderLayout.WEST;
	/**  */
	public static final String MENU_LOCATION_BOTTOM = BorderLayout.SOUTH;
	/**  */
	public static final String MENU_LOCATION_RIGHT = BorderLayout.EAST;
	/**  */
	public static final String MENU_LOCATION_PAGE_START = BorderLayout.PAGE_START;
	/**  */
	public static final String MENU_LOCATION_PAGE_END = BorderLayout.PAGE_END;

	private String position;

	private List<JUIGLEMenuItem> items;// TODO zvazit pouziti

	/**
	 * Create <code>JUIGLE Menu</code>> on specific position.
	 * 
	 * @param position
	 */
	public JUIGLEMenu(String position) {
		this.position = position;
		glow.setAmount(1.08f);
		initialize();
	}

	/**
	 * Initialize menu
	 */
	private void initialize() {
		items = new ArrayList<JUIGLEMenuItem>();
		this.setFloatable(false);
		this.setRollover(true);
		this.setOpaque(false);
	}

	public void addItem(JUIGLEMenuItem item) {
		//prepareItemForMenu(item);
		createButton(item);
		items.add(item);
	}

	public List<JUIGLEMenuItem> getMenuItemsList() {
		return items;
	}

	/**
	 * Return position of menu
	 * @return position of menu as String value
	 */
	public String getMenuPosition() {
		return position;
	}

	/**
	 * 
	 * @param item
	 * @param ab
	 * @return
	 */
	private JPopupMenu createAndGetSubMenu(JUIGLEMenuItem item, final AbstractButton ab) {
		final JPopupMenu menu = new JPopupMenu();
		for (JUIGLEMenuItem it : item.getSubMenu()) {
			JMenuItem subItem = new JMenuItem(it.getAction());
			if (it.hasSubMenu()) {
				subItem.setComponentPopupMenu(createAndGetSubMenu(it, subItem));
			}
			subItem.setText(it.getLabel());
			if (it.getItemIcon() != null) {
				subItem.setIcon(new ImageIcon(it.getItemIcon()));				
			}
			menu.add(subItem);
			menu.revalidate();
		}
		
		Action action = new AbstractAction() {
			/** Only for serialization */
			private static final long serialVersionUID = 3729221760223444301L;

			@Override
			public void actionPerformed(ActionEvent e) {
				menu.show(ab, 0, ab.getHeight());				
			}		
		};
		ab.setAction(action);
		return menu;
	}

	private void createButton(JUIGLEMenuItem item) {
		boolean isRoot = false;
		final JXButton button = new JXButton();
		//button.setAction(item.getAction());
		if (item.hasSubMenu()) {
			isRoot = true;
			button.setComponentPopupMenu(createAndGetSubMenu(item, button));
		}
		if (item.getAction() != null && !isRoot) {
			button.setAction(item.getAction());							
		}
		if (item.getLabel() != null) { // TODO overit tady jestli to takle fakt musi byt
			button.setText(item.getLabel());
		}
		if (item.getItemIcon() != null) {
			button.setIcon(new ImageIcon(item.getItemIcon()));
			button.setRolloverIcon(new ImageIcon(glow.filter(item.getItemIcon(), null)));
			button.setForeground(GraphicsUtilities.TRANSPARENT_COLOR);
		}
		//button.setForeground(item.getColor() == null ? Color.RED : item.getColor());*/
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));	
		button.setBorder(new EmptyBorder(0, 6, 0, 6));
		//button.setBorder(null);
		button.setFocusPainted(false);
		button.setBackground(GraphicsUtilities.TRANSPARENT_COLOR);
		button.setContentAreaFilled(false);
		//button.setPreferredSize(new Dimension(32, 1));

		this.add(button);
	}
	
	/**
	 * Add separator as item to menu
	 * 
	 * @throws JUIGLEMenuException
	 */
	public void addMenuSeparator() throws JUIGLEMenuException {
		try {
			BufferedImage image = ImageIO.read(ClassLoader.getSystemResourceAsStream("ch/ethz/origo/jerpaui/data/images/toolbar.png"));
			createSeparator(image);
		} catch (IOException e) {
			throw new JUIGLEMenuException(e);
		}
	}
	
	/**
	 * 
	 * 
	 * @param separatorImg
	 */
	public void addMenuSeparator(BufferedImage separatorImg) {
		createSeparator(separatorImg);
	}
	
	private void createSeparator(BufferedImage image) {
		JToolBar.Separator separator = new JToolBar.Separator();
		separator.setUI(new ImageSeparator(image));
		separator.setSeparatorSize(new Dimension(image.getWidth(), image.getHeight()));
		this.add(separator);
	}
	
}
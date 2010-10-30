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

package ch.ethz.origo.juigle.prezentation.menu;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import ch.ethz.origo.juigle.application.ILanguage;
import ch.ethz.origo.juigle.application.JUIGLEErrorParser;
import ch.ethz.origo.juigle.application.exception.JUIGLELangException;
import ch.ethz.origo.juigle.application.exception.JUIGLEMenuException;
import ch.ethz.origo.juigle.application.observers.LanguageObservable;
import ch.ethz.origo.juigle.data.EmailErrorReporter;
import ch.ethz.origo.juigle.data.ErrorCodes;
import ch.ethz.origo.juigle.prezentation.ImageSeparator;
import ch.ethz.origo.juigle.prezentation.JUIGLEButton;
import ch.ethz.origo.juigle.prezentation.JUIGLEGraphicsUtils;
import ch.ethz.origo.juigle.prezentation.JUIGLErrorInfoUtils;

import com.jhlabs.image.GlowFilter;

/**
 * Construct basic menu which library JUIGLE offers. This class should be
 * extended by all new classes constructed menu.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.6.00 (10/30/2010)
 * @since 0.1.0 (07/16/09)
 * @see JToolBar
 * @see ILanguage
 */
public class JUIGLEMenu extends JToolBar implements ILanguage {

	/** Only for serialization */
	private static final long serialVersionUID = 744283918627175663L;

	/** Menu location on top of the panel */
	public static final String MENU_LOCATION_TOP = BorderLayout.NORTH;
	/** Menu location on left side of the panel */
	public static final String MENU_LOCATION_LEFT = BorderLayout.WEST;
	/**  */
	public static final String MENU_LOCATION_BOTTOM = BorderLayout.SOUTH;
	/**  */
	public static final String MENU_LOCATION_RIGHT = BorderLayout.EAST;
	/**  */
	public static final String MENU_LOCATION_PAGE_START = BorderLayout.PAGE_START;
	/**  */
	public static final String MENU_LOCATION_PAGE_END = BorderLayout.PAGE_END;

	protected String position;
	protected String resourcePath;

	protected List<JUIGLEMenuItem> listOfitems;
	protected List<JUIGLEButton> listOfButtons;

	private static GlowFilter glow = new GlowFilter();

	private static Logger logger = Logger.getLogger(JUIGLEMenu.class);

	protected ResourceBundle resource;

	/**
	 * Create empty <code>JUIGLE Menu</code>> with no specific position. You must
	 * set position later.
	 */
	public JUIGLEMenu() {
		initialize();
	}

	/**
	 * Create <code>JUIGLE Menu</code>> on specific position.
	 * 
	 * @param position
	 */
	public JUIGLEMenu(String position) {
		this.position = position;
		initialize();
	}

	/**
	 * Create <code>JUIGLE Menu</code>> on specific position and with localized
	 * text
	 * 
	 * @param position
	 * @param resourcePath
	 */
	public JUIGLEMenu(String position, String resourcePath) {
		this(position);
		this.resourcePath = resourcePath;
		setLocalizedResourceBundle(resourcePath);
	}

	/**
	 * Initialize menu
	 * 
	 * @version 0.1.1 (10/28/09)
	 * @since 0.1.0
	 */
	private void initialize() {
		// register as language observer
		LanguageObservable.getInstance().attach(this);
		// 1.08f
		glow.setAmount(0.04f);
		listOfitems = new ArrayList<JUIGLEMenuItem>(); // TODO mozna inicializovat
		// pouze kdyz fakt bude
		// itema
		listOfButtons = new ArrayList<JUIGLEButton>();
		this.setFloatable(false);
		this.setRollover(true);
		this.setOpaque(false);
	}

	public void addItem(JUIGLEMenuItem item) {
		listOfitems.add(item);
		createButton(item);
	}

	public List<JUIGLEMenuItem> getMenuItemsList() {
		return listOfitems;
	}

	/**
	 * Return position of menu
	 * 
	 * @return position String value of menu position (Location)
	 */
	public String getMenuPosition() {
		return position;
	}

	/**
	 * Set up position of menu
	 * 
	 * @param position
	 *          String value of menu position (Location)
	 */
	public void setMenuPosition(String position) {
		this.position = position;
	}

	@Override
	public void setLocalizedResourceBundle(String path) {
		this.resourcePath = path;
		this.resource = ResourceBundle.getBundle(resourcePath);
	}

	/**
	 * This method is not used !!! Is not implemented for {@link JUIGLEMenu}.
	 */
	@Override
	@Deprecated
	public void setResourceBundleKey(String key) {
	}

	/**
	 * 
	 * @param item
	 * @param ab
	 * @return
	 */
	private JPopupMenu createAndGetSubMenu(JUIGLEMenuItem item,
			final AbstractButton ab) {
		final JPopupMenu menu = new JPopupMenu();
		for (JUIGLEMenuItem it : item.getSubMenu()) {
			if (it.hasSubMenu()) {
				it.setComponentPopupMenu(createAndGetSubMenu(it, it));
			}
			// TODO tohle nefunguje
			/*
			 * if (it.canBeTextShow()) { it.setText(it.getT); }
			 */
			if (it.isOwnResourceBundleSets()) {
				try {
					it.updateText();
				} catch (JUIGLELangException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (it.getIcon() != null) {
				it.setIcon(it.getIcon());
			}
			menu.add(it);
			if (it.isItemSeparatorRequired()) {
				menu.addSeparator();
			}
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

	protected void createButton(JUIGLEMenuItem item) {
		boolean isRoot = false;
		final JUIGLEButton button = new JUIGLEButton();
		// button.setAction(item.getAction());
		if (item.hasSubMenu()) {
			isRoot = true;
			button.setComponentPopupMenu(createAndGetSubMenu(item, button));
		}
		if (item.getAction() != null && !isRoot) {
			button.setAction(item.getAction());
		}
		if (item.getText() != null) {
			// TODO overit tady jestli to takle fakt musi byt
			button.setText(item.getText());
		}
		if (!item.canBeTextShow()) {
			button.showText(false);
		}
		if (item.isToolTipTextExist()) {
			button.setToolTipResourceBundleKey(item.getToolTipResourceBundleKey());
			button.setToolTipText(item.getToolTipText());
		}
		if (item.getIcon() != null) {
			button.setIcon(item.getIcon());
			button.setRolloverIcon(new ImageIcon(
					glow.filter(item.getItemIcon(), null)));
			button.setForeground(JUIGLEGraphicsUtils.TRANSPARENT_COLOR);
		}
		if (resourcePath != null) {
			button.setLocalizedResourceBundle(resourcePath);
		} else {
			button.setLocalizedResourceBundle(item.getResourceBundlePath());
		}
		button.setResourceBundleKey(item.getResourceBundleKey());
		// button.setForeground(item.getColor() == null ? Color.RED :
		// item.getColor());*/
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setBorder(new EmptyBorder(0, 6, 0, 6));
		// button.setBorder(null);
		button.setFocusPainted(false);
		button.setBackground(JUIGLEGraphicsUtils.TRANSPARENT_COLOR);
		button.setContentAreaFilled(false);
		// button.setPreferredSize(new Dimension(32, 1));
		updateText();

		listOfButtons.add(button);
		this.add(button);
	}

	/**
	 * Add separator as item to menu. Image of separator is set up on default
	 * <code>JUIGLE</code> image for separators. If you want create separator with
	 * your own image, then you use method
	 * <code>addMenuSeparator(BufferedImage image)</code>.
	 * 
	 * @throws JUIGLEMenuException
	 */
	public void addMenuSeparator() throws JUIGLEMenuException {
		try {
			BufferedImage image = ImageIO
					.read(ClassLoader
							.getSystemResourceAsStream("ch/ethz/origo/juigle/data/images/toolbar.png"));
			createSeparator(image);
		} catch (IOException e) {
			throw new JUIGLEMenuException(e);
		}
	}

	/**
	 * Add separator to <code>JUIGLE</code> menu with specific image.
	 * 
	 * @param separatorImg
	 *          image for separator item
	 * @version 0.1.0
	 */
	public void addMenuSeparator(BufferedImage separatorImg) {
		createSeparator(separatorImg);
	}

	protected void createHideButton(boolean showText, Action action) {
		// FIXME not implemented yet - why?
	}

	private void createSeparator(BufferedImage image) {
		JToolBar.Separator separator = new JToolBar.Separator();
		separator.setUI(new ImageSeparator(image));
		separator.setSeparatorSize(new Dimension(image.getWidth(), image
				.getHeight()));
		this.add(separator);
	}

	@Override
	public void updateText() {
		/*
		 * SwingUtilities.invokeLater(new Runnable() {
		 * 
		 * @Override public void run() {
		 */
		setLocalizedResourceBundle(resourcePath);
		for (JUIGLEMenuItem item : listOfitems) {
			try {
				updateItemText(item);
				if (item.hasSubMenu()) {
					for (JUIGLEMenuItem subItem : item.getSubMenu()) {
						updateItemText(subItem);
					}
				}
			} catch (MissingResourceException e) {
				// parsing error message
				String errorMSG = JUIGLEErrorParser
						.getJUIGLEErrorMessage(ErrorCodes.RESOURCE_BD_KEY_OR_FILE_NOT_FND_P2
								+ item.getResourceBundleKey()
								+ ":"
								+ (resourcePath != null ? resourcePath : item
										.getResourceBundlePath()));
				// display error GUI
				JUIGLErrorInfoUtils.showErrorDialog("JUIGLE Error", errorMSG, e,
						Level.WARNING, new EmailErrorReporter());
				// write message to logger
				JUIGLEMenu.logger.error(errorMSG, e);
			} catch (JUIGLELangException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (JUIGLEButton butt : listOfButtons) {
			try {
				butt.updateText();
			} catch (JUIGLELangException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// }
		// });
	}

	/**
	 * Updated item text. If item has toolTip text, than is also updated.
	 * 
	 * @param item
	 * @throws JUIGLELangException
	 * @version 0.1.1 (3/20/2010)
	 * @since 0.1.0 (07/16/09)
	 */
	private void updateItemText(JUIGLEMenuItem item) throws JUIGLELangException {
		if (!item.isOwnResourceBundleSets()) {
			if (item.canBeTextShow()) {
				if (item.getResourceBundleKey() != null)
					item.updateText(resource.getString(item.getResourceBundleKey()));
			}
			if (item.isToolTipTextExist()) {
				item.updateToolTipText(resource.getString(item
						.getToolTipResourceBundleKey()));
			}
		} else {
			item.updateText();
		}
	}

	@Override
	public String getResourceBundlePath() {
		return resourcePath;
	}

}
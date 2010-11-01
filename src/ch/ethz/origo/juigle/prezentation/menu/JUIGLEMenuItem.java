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

import java.awt.Color;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import ch.ethz.origo.juigle.application.ILanguage;
import ch.ethz.origo.juigle.application.exception.JUIGLELangException;

/**
 * This class represent modified classic java JMenuItem. This item is costumized
 * for International Language Support and other JUIGLE needs.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.3.00 (10/30/2010)
 * @since 0.1.0 (05/18/09)
 * @see JMenuItem
 * @see ILanguage
 */
public class JUIGLEMenuItem extends JMenuItem implements ILanguage {

	/** Only for serialization */
	private static final long serialVersionUID = -8011842643730136500L;

	private BufferedImage icon;

	private Color color;

	private Collection<JUIGLEMenuItem> subMenuList;

	private String resourceBundleKey;

	private String tooltipResourceBundleKey;

	protected String resourcePath;

	private ResourceBundle resource;

	private boolean showText = true;

	private boolean isToolTipText = false;

	private boolean isItemSeparatorRequired;

	private String toolTipText;

	/**
	 * Default constructor. Variables are not sets.
	 */
	public JUIGLEMenuItem() {
		// super();
	}

	/**
	 * Construct menu item with key for localized text
	 * 
	 * @param resourceKey
	 *          key for resource bundle
	 */
	public JUIGLEMenuItem(String resourceKey) {
		setText(resourceKey);
		setResourceBundleKey(resourceKey);
	}

	/**
	 * Construct menu item with key for localized text and set specified action
	 * for this item.
	 * 
	 * @param resourceKey
	 *          key for localized text
	 * @param action
	 *          item action
	 */
	public JUIGLEMenuItem(String resourceKey, Action action) {
		this(resourceKey);
		setAction(action);
	}

	/**
	 * Construct menu item with key for localized text and set specified action
	 * for this item and specified icon.
	 * 
	 * @param resourceKey
	 *          key for localized text
	 * @param action
	 *          item action
	 * @param icon
	 *          item icon
	 */
	public JUIGLEMenuItem(String resourceKey, Action action, BufferedImage icon) {
		this(resourceKey, action);
		// setIcon(icon);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAction(Action action) {
		super.setAction(action);
	}

	/**
	 * Return item icon
	 * 
	 * @return item icon
	 */
	public BufferedImage getItemIcon() {
		BufferedImage bi = new BufferedImage(getIcon().getIconWidth(), getIcon()
				.getIconHeight(), Transparency.TRANSLUCENT);
		getIcon().paintIcon(null, bi.createGraphics(), 0, 0);
		return bi;
	}

	/*
	 * public void setIcon(BufferedImage icon) { this.icon = icon; }
	 */

	/**
	 * Return key for ResourceBundle
	 */
	public String getResourceBundleKey() {
		return resourceBundleKey;
	}

	// version 0.1.0 since 0.1.1 (3/20/2010)
	public String getToolTipResourceBundleKey() {
		return tooltipResourceBundleKey;
	}

	@Override
	public void setResourceBundleKey(String key) {
		this.resourceBundleKey = key;
	}

	/**
	 * Return collection of sub-menu (subitems) if exist for this item.
	 * 
	 * @return collection of sub-menu (subitems) if exist for this item
	 */
	public Collection<JUIGLEMenuItem> getSubMenu() {
		return subMenuList;
	}

	/**
	 * Return item color
	 * 
	 * @return item color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Set text color for this item
	 * 
	 * @param color
	 *          item color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	public void addSubItem(JUIGLEMenuItem subItem) {
		if (subMenuList == null) {
			subMenuList = new ArrayList<JUIGLEMenuItem>();
		}
		subMenuList.add(subItem);
	}

	/**
	 * Return true, if menu has sub-menu
	 * 
	 * @return true, if menu has sub-menu
	 */
	public boolean hasSubMenu() {
		return subMenuList != null;
	}

	/**
	 * Set if text of this item can be show
	 * 
	 * @param show
	 *          boolean value
	 */
	public void showText(boolean show) {
		this.showText = show;
	}

	/**
	 * Set Resource Bundle key for item tooltip
	 * 
	 * @param tooltipResourceBundleKey
	 *          key of resource bundle localized text
	 */
	public void setToolTipResourceBundleKey(String tooltipResourceBundleKey) {
		this.tooltipResourceBundleKey = tooltipResourceBundleKey;
		isToolTipText = true;
	}

	@Override
	public void setToolTipText(String text) {
		this.toolTipText = text;
	}

	@Override
	public String getToolTipText() {
		return toolTipText;
	}

	/**
	 * Return true, if text of this item can be show
	 * 
	 * @return true, if text of this item can be show
	 */
	public boolean canBeTextShow() {
		return showText;
	}

	/**
	 * Return true, if item has tooltip
	 * 
	 * @return true, if item has tooltip
	 */
	public boolean isToolTipTextExist() {
		return isToolTipText;
	}

	/**
	 * Update item text
	 * 
	 * @param text
	 *          - item
	 */
	public void updateText(final String text) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				setText(text);
			}
		});
	}

	/**
	 * This methdo update text for tooltip of these item
	 * 
	 * @param text
	 *          for tooltip of these item
	 */
	public void updateToolTipText(final String text) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				setToolTipText(text);
			}
		});
	}

	/**
	 * Return true, of this item has own instance of ResourceBundle class.
	 * 
	 * @return true, of this item has own instance of ResourceBundle class.
	 */
	protected boolean isOwnResourceBundleSets() {
		return resource == null ? false : true;
	}

	@Override
	public void setLocalizedResourceBundle(String resourcePath) {
		this.resourcePath = resourcePath;
		this.resource = ResourceBundle.getBundle(resourcePath);
	}

	@Override
	public void updateText() throws JUIGLELangException {
		setLocalizedResourceBundle(getResourceBundlePath());
		if (canBeTextShow() && getResourceBundleKey() != null) {
			super.setText(resource.getString(getResourceBundleKey()));
		}
		if (isToolTipText) {
			setToolTipText(resource.getString(tooltipResourceBundleKey));
		}
	}

	@Override
	public String getResourceBundlePath() {
		return resourcePath;
	}

	/**
	 * 
	 * @return
	 * @version 0.1.0.00 (10/30/2010)
	 * @since 0.1.3.00 (10/30/2010)
	 */
	public boolean isItemSeparatorRequired() {
		return isItemSeparatorRequired;
	}

	/**
	 * 
	 * 
	 * @version 0.1.0.00 (10/30/2010)
	 * @since 0.1.3.00 (10/30/2010)
	 */
	public void addSeparator() {
		this.isItemSeparatorRequired = true;
	}

}
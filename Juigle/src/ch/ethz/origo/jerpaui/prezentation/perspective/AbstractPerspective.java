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
 *    AbstractPerspective.java
 *    Copyright (C) 2009 University of West Bohemia, 
 *                       Department of Computer Science and Engineering, 
 *                       Pilsen, Czech Republic
 */
package ch.ethz.origo.jerpaui.prezentation.perspective;

import java.util.Locale;
import java.util.ResourceBundle;

import nezarazeno.ILanguage;
import nezarazeno.PerspectiveException;

import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTitledPanel;

import ch.ethz.origo.jerpaui.prezentation.JUIGLEMenu;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 07/12/09
 * @since 0.1.0
 */
public abstract class AbstractPerspective implements IPerspective, ILanguage {

	/** Only for serialization */
	private static final long serialVersionUID = 894536627512039840L;

	protected JXTitledPanel menuPanel;

	protected JUIGLEMenu menu;

	protected JXPanel mainPanel;

	protected boolean isDefault;
	
	protected ResourceBundle resource;

	/**
	 * Initialize perspective panel
	 */
	public abstract void initPerspectivePanel();

	/**
	 * Initialize menu of perspective.
	 * 
	 * @throws PerspectiveException
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public abstract void initPerspectiveMenuPanel() throws PerspectiveException;
	
	/**
   * @param string the String to be localized
   * @return the localized String
   */
  public abstract String getLocalizedString(String string);
  
  public abstract void  setLocalizedResource(Locale locale);
	/**
	 * Return boolean variable if perspective is default
	 * 
	 * @return true - if perspective is default, else return false
	 */
	public boolean isDefaultPerspective() {
		return isDefault;
	}

	public void setPerspectiveAsDefault(boolean value) {
		isDefault = value;
	}

	/**
	 * Return perspective menu
	 * 
	 * @return perspective menu
	 */
	public JUIGLEMenu getMenu() {
		return menu;
	}

	/**
	 * Initialize and return panel which contains main menu of perspective.
	 * 
	 * @return
	 */
	public JXTitledPanel getMenuPanel() {
		return menuPanel;
	}

	public JXPanel getMainPerspectivePanel() {
		return mainPanel;
	}

}
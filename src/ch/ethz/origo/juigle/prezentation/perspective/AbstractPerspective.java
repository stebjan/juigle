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
package ch.ethz.origo.juigle.prezentation.perspective;

import java.util.ResourceBundle;

import javax.swing.JPanel;

import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTitledPanel;

import ch.ethz.origo.juigle.application.ILanguage;
import ch.ethz.origo.juigle.application.exception.PerspectiveException;
import ch.ethz.origo.juigle.application.observers.PerspectiveObservable;
import ch.ethz.origo.juigle.prezentation.menu.JUIGLEMenu;
import ch.ethz.origo.juigle.prezentation.menu.JUIGLEPerspectiveMenu;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.3 (2/21/2010)
 * @since 0.1.0 (07/12/09)
 * @see IPerspective
 * 
 */
public abstract class AbstractPerspective implements IPerspective, ILanguage {
	
	//FIXME ILanguage a LangListener - zjisti jestli je to nutne
	/** Only for serialization */
	private static final long serialVersionUID = 894536627512039840L;

	protected boolean isDefault;
	
	protected static String resourcePath;
	protected String resourceBundleKey;
	
	protected JXTitledPanel menuTitledPanel;
	
	protected JXTaskPane menuTaskPane;

	protected JUIGLEPerspectiveMenu menu;

	protected JXPanel mainPanel;
	
	protected ResourceBundle resource;
	
	protected static PerspectiveObservable perspectiveObservable = PerspectiveObservable.getInstance();
	
	/**
	 * Initialize perspective panel
	 * 
	 * @throws PerspectiveException
	 * @version 0.1.1 (11/29/09)
	 * @since 0.1.0
	 */
	public abstract void initPerspectivePanel() throws PerspectiveException;

	/**
	 * Initialize menu of perspective.
	 * 
	 * @throws PerspectiveException
	 * @version 0.1.0
	 * @since 0.1.0 (07/12/09)
	 */
	public abstract void initPerspectiveMenuPanel() throws PerspectiveException;
	
	/**
	 * 
	 * 
   * @param string the String to be localized
   * @return the localized String
   */
  public abstract String getLocalizedString(String string);
  
  /**
	 * Return boolean variable if perspective is default
	 * 
	 * @return true - if perspective is default, else return false
	 * @version 0.1.0 (07/12/09)
	 * @since 0.1.0 (07/12/09)
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
	
	public abstract String getRBPerspectiveTitleKey();

	/**
	 * Initialize and return panel which contains main menu of perspective.
	 * 
	 * @return
	 */
	public JPanel getMenuPanel() {
		if (menuTaskPane != null) {
			return menuTaskPane;
		} else {
			return menuTitledPanel;
		}
	}

	public JXPanel getMainPerspectivePanel() {
		return mainPanel;
	}
	
	
}
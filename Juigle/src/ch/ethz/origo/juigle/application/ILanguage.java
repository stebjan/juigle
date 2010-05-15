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
package ch.ethz.origo.juigle.application;

import java.util.ResourceBundle;

import ch.ethz.origo.juigle.application.exception.JUIGLELangException;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.1 (09/15/09}
 * @since 0.1.0 (08/11/09)
 */
public interface ILanguage {
	
	/**
	 * 
	 * 
	 * @param path
	 * @version 0.1.1
	 * @since 0.1.0
	 */
	public void setLocalizedResourceBundle(String path);
	
	/**
	 * 
	 * @return
	 * @version 0.1.0
	 * @since 0.1.1
	 */
	public String getResourceBundlePath();
	
	/**
	 * Set key for resource bundle. In this method you should
	 * set up key for your <code>ResourceBundle</code>.
	 * For example:<br>
	 * key: <juigle.buttons.hide.footer>
	 * 
	 * @param key name of key as String parameter
	 * @see ResourceBundle
	 */
	public void setResourceBundleKey(String key);
	
	/**
	 * 
	 * 
	 * @throws JUIGLELangException
	 */
	public void updateText() throws JUIGLELangException;

}
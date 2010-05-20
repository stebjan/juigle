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
 *    AppButtonsListener.java
 *    Copyright (C) 2009 University of West Bohemia, 
 *                       Department of Computer Science and Engineering, 
 *                       Pilsen, Czech Republic
 */
package ch.ethz.origo.juigle.application.listener;

import java.util.EventListener;


/**
 * Button listner
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 08/12/09
 * @since 0.1.0 (05/18/09)
 *
 */
public interface AppButtonsListener extends EventListener {
	
	/**
	 * Application is minimalizing
	 * @param e
	 */
	public void minimalizelizeAppButton(AppButtonsEvent e);

	/**
	 * Application is maximalizing
	 * @param e
	 */
	public void maximalizeAppButton(AppButtonsEvent e);
	
	/**
	 * Application is closing
	 * @param e
	 */
	public void closeAppButton(AppButtonsEvent e);
}

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
package ch.ethz.origo.juigle.application.observers;

/**
 * Observer pattern for communication between each of perspectives.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.2.0 (2/21/2010)
 * @since 0.1.0 (08/15/09)
 * @see JUIGLEObservable
 */
public class PerspectiveObservable extends JUIGLEObservable {

	public static final int MSG_APP_CLOSE = 0;
	public static final int MSG_PERSPECTIVE_CHANGED = 1;
	public static final int MSG_PROJECT_CLOSED = 2;
	public static final int MSG_CURRENT_PROJECT_CHANGED = 3;
	public static final int MSG_UNDOABLE_COMMAND_INVOKED = 4;

	private static PerspectiveObservable instance;

	/**
	 * 
	 * @return
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public static synchronized PerspectiveObservable getInstance() {
		if (instance == null) {
			instance = new PerspectiveObservable();
		}
		return instance;
	}

}

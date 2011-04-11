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
 * Implementation of Observer Pattern. This interface is implemented by each of
 * listeners.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.1 (1/30/2010)
 * @since 0.1.0 (08/16/09)
 */
public interface IObserver {

	/**
	 * This method is calling from the Observable when he points on state change
	 */
	public void update();

	/**
	 * This method is calling from the Observable when he points on state change
	 * 
	 * @param state
	 *          object
	 */
	public void update(Object state);

	/**
	 * This method is calling from the Observable when he points on state change
	 * 
	 * @param o
	 *          observable, which we observe
	 * @param state
	 *          object
	 */
	public void update(IObservable o, Object state);

}

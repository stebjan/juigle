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
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.2 (3/14/2010)
 * @since 0.1.0 (08/16/09)
 * @see IObservable
 */
public abstract class AbstractJUIGLEObservable implements IObservable {
	
	/**
	 * Return current state of observable object(subject)
	 * 
	 * @return current state of observable
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public abstract Object getState();
	
	/**
	 * Set state of observable
	 * 
	 * @param state
	 * @version 0.1.1 (10/02/09)
	 * @since 0.1.0
	 */
	protected abstract void setState(int state);
	
	/**
	 * Indicates that this object has no longer changed, 
	 * or that it has already notified all of its observers of its most recent change.
	 * This method should be called by methods <code>notifyObservers()</code> and <code>notfyObserver(IObserver observer)</code>
	 * 
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	protected abstract void clearChanged();
	
	/**
	 * 
	 * @param observer
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	protected abstract void notifyObserver(IObserver observer);
	
	/**
	 * 
	 * 
	 * @param obj
	 * @version 0.1.0
	 * @since 0.1.2
	 */
	protected abstract void notifyObserver(Object obj);
	
	/**
	 * 
	 * 
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	protected abstract void notifyObservers();
	
}

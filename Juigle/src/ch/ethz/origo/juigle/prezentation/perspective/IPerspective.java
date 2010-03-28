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
 *    IPerspective.java
 *    Copyright (C) 2009 University of West Bohemia, 
 *                       Department of Computer Science and Engineering, 
 *                       Pilsen, Czech Republic
 */
package ch.ethz.origo.juigle.prezentation.perspective;

import javax.swing.Icon;

import ch.ethz.origo.juigle.application.exception.PerspectiveException;


/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.1 (3/28/2010)
 * @since 0.1.0 (project version)
 */
public interface IPerspective {

	/**
	 * This method return name of perspective. 
	 * 
	 * @return name of perspective
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public String getTitle(); 
	
	/**
	 * Return perspective image icon 
	 * 
	 * @return icon of perspective
	 * @throws PerspectiveException
	 * @version 0.1.1 (3/28/2010)
	 * @since 0.1.0 (07/12/09)
	 */
	public Icon getPerspectiveIcon() throws PerspectiveException;
		
}

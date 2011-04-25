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
 *    Copyright (C) 2009 - 2011 
 *    							University of West Bohemia, 
 *                  Department of Computer Science and Engineering, 
 *                  Pilsen, Czech Republic
 */
package ch.ethz.origo.juigle.application;

import java.util.List;

import ch.ethz.origo.juigle.application.exception.PerspectiveException;
import ch.ethz.origo.juigle.prezentation.perspective.Perspective;

/**
 * Interface for loader of perspective.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 1.0.0 (4/3/2011)
 * @since 0.1.0 (7/20/09)
 */
public interface IPerspectiveLoader {

	/**
	 * List of available perspectives.
	 * 
	 * @return List of available perspectives as Collection
	 * @throws PerspectiveException
	 */
	public List<Perspective> getListOfPerspectives() throws PerspectiveException;
	
	/**
	 * Return default (main) perspective
	 * 
	 * @return default perspective
	 */
	public Perspective getDefaultPerspective(); 
	
	/**
	 * Return perspective by identifier (id)
	 * 
	 * @param id a unique identifier of perspective
	 * @return
	 * @since 1.0.0 (4/3/2011)
	 */
	public Perspective getPerspective(String id);
	
}

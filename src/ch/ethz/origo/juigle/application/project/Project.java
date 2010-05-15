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
package ch.ethz.origo.juigle.application.project;

import java.io.File;


/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.2.1 (2/21/2010)
 * @since 0.1.0 (10/21/09)
 * @see Undoable    
 */
public abstract class Project extends Undoable {
	
	protected File projectFile; // neukladat
	
	@Override
	public abstract ProjectMementoCaretaker createMemento();
	
	@Override
	public abstract void setMemento(ProjectMementoCaretaker memento);
	
	public abstract void openFile();
	
	public abstract void saveFile();
	
	public abstract String getName();
	
	public abstract void setName(String name);
	
	public abstract void closeBuffers();
	
	public void setProjectFile(File file) {
		this.projectFile = file;
	}
	
	public File getProjectFile() {
		return projectFile;
	}

}
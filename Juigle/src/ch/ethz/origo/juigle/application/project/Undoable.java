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

import java.util.Stack;

/**
 * Memento Pattern for undo/redo mechanism in projects.
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @author Jiri Kucera
 * @version 0.1.0 10/21/2009 
 * @since 0.1.0 (06/07/2009)
 * @see IProjectOriginator    
 *
 */
public abstract class Undoable implements IProjectOriginator {

	protected Stack<ProjectMementoCaretaker> undoStack;
	protected Stack<ProjectMementoCaretaker> redoStack;
	protected boolean cmdLocked;
	
	/**
	 * Vytvo�� instanci t��dy.
	 */
	public Undoable() {
		undoStack = new Stack<ProjectMementoCaretaker>();
		redoStack = new Stack<ProjectMementoCaretaker>();
		cmdLocked = false;

	}

	/**
	 * Zamkne metodu <code>doCommand()</code> tak, aby nevykon�vala ��dnou akci.
	 */
	public void lockCommand() {
		cmdLocked = true;
	}

	/**
	 * Odemkne metodu <code>doCommand()</code>, aby vykon�vala svoji �innost.
	 */
	public void unlockCommand() {
		cmdLocked = false;
	}

	/**
	 * 
	 * @return cmdLocked
	 * @deprecated Slou�� pouze pro ��ely lazen�.
	 */
	@Deprecated
	public boolean commandLocked() {
		return cmdLocked;
	}

	/**
	 * Ulo�� stav projektu a umo�n� tak vr�tit projekt do p�edchoz�ho stavu
	 * vol�n�m undo().<br/> Aby byly providery oken informov�ny, m�sto t�to
	 * metody volejte GuiController.doCommand();
	 */
	public void doCommand() {
		if (cmdLocked) {
			return;
		}
		redoStack.clear();
		undoStack.add(createMemento());
	}

	/**
	 * Uvede projekt do p�edchoz�ho ulo�en�ho stavu (undo). Aby byly providery
	 * oken informov�ny, m�sto t�to metody volejte GuiController.undo();
	 */
	public void undo() {
		if (canUndo()) {
			redoStack.push(createMemento());
			setMemento(undoStack.pop());
		}
	}

	/**
	 * Uvede projekt do n�sleduj�c�ho ulo�en�ho stavu (redo). Aby byly providery
	 * oken informov�ny, m�sto t�to metody volejte GuiController.redo();
	 */
	public void redo() {
		if (canRedo()) {
			undoStack.push(createMemento());
			setMemento(redoStack.pop());
		}
	}

	/**
	 * Zjist�, zda je mo�no uv�st projekt do p�edchoz�ho ulo�en�ho stavu (undo).
	 * 
	 * @return <code>true</code>, pokud je mo�no projekt vr�tit do p�edchoz�ho
	 *         stavu, jinak <code>false</code>.
	 */
	public boolean canUndo() {
		return !undoStack.empty();
	}

	/**
	 * Zjist�, zda je mo�no uv�st projekt do n�sleduj�c�ho ulo�en�ho stavu (redo).
	 * 
	 * @return <code>true</code>, pokud je mo�no projekt vr�tit do
	 *         n�sleduj�c�ho stavu, jinak <code>false</code>.
	 */
	public boolean canRedo() {
		return !redoStack.empty();
	}

}

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
package ch.ethz.origo.juigle.application.listener;

import java.util.EventObject;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 08/12/09
 * @since 0.1.0 (05/18/09)
 *
 */
public class AppButtonsEvent extends EventObject {

  /**
   * Only for serialization
   */
  private static final long serialVersionUID = -7299616202161865296L;
  
  private int id; // id of event

  private String message; // information text
  
  private Exception exception; // exception
  
  /**
   * Initialize event
   * 
   * @param source object - owner
   * @param id type of event
   * @param message text to display
   * @param exception caused Exception
   */
  public AppButtonsEvent(Object source, int id, String message, Exception exception) {
    super(source);
    setId(id);
    this.setMessage(message);
    this.setException(exception);
  }

	public int getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public Exception getException() {
		return exception;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
}

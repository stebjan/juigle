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
package ch.ethz.origo.juigle.application.xml;

import org.w3c.dom.Node;
import org.w3c.dom.Document;

/**
 * XML serialization
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public interface XMLSerializable {
	
    /**
     * Create XML structure from class
     *
     * @param doc Document - new dom document
     * @return Node - is highest node of this dom-structure
     */
    public Node xmlSave(Document doc);

    /**
     * Initialization of class with DOM node
     * Note: usege in constructor this(Element element)
     *
     * @param node Node - is highest node of this dom-structure
     */
    public void xmlLoad(Node node);
}

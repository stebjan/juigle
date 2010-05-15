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
package ch.ethz.origo.juigle.data.database;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

import ch.ethz.origo.juigle.application.database.DBProvider;

/**
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (1/29/2010)
 * @since 0.1.0 (1/29/2010)
 *
 */
public class ProvidersLoader {

	private File file;
	private List<DBProvider> providers;
	
	public ProvidersLoader(File file) {
		this.file = file;
		providers = new ArrayList<DBProvider>();
	}
	
	public List<DBProvider> getListOfProviders() {
		if (providers.size() == 0) {
			//xmlLoad();			
		}
		return providers;
	}
	
	private void xmlLoad(String filename) {
		 DOMParser parser = new DOMParser();
     Document document = parser.getDocument();
     Element root = document.getDocumentElement(); // root element
	}
}

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
package ch.ethz.origo.juigle.prezentation.database;

import org.jdesktop.swingx.JXLoginPane;
import org.jdesktop.swingx.auth.JDBCLoginService;
import org.jdesktop.swingx.auth.LoginListener;

import ch.ethz.origo.juigle.database.service.LoginDialogService;

/**
 * 
 * @author vsouhrada (vsouhrada at gmail dot com)
 * @version 1.0.0 (4/25/2011)
 * @since 1.1.0 (4/25/2011)
 * @see JXLoginPane
 * @see LoginListener
 *
 */
public abstract class DatabaseDialog extends JXLoginPane implements LoginListener {

	/**
	 * Only for serialization
	 */
	private static final long serialVersionUID = 2879424870308743334L;
	
	private LoginDialogService db;
	
	private JDBCLoginService svc;
	
	public DatabaseDialog(LoginDialogService database) {
		this.db = database;
		initialize();
	}
	
	public void initialize() {
		svc = db.getLoginServiceInstance();
		svc.addLoginListener(this);
		setLoginService(svc);
		
		setBannerText("Connect to database");
		
		JXLoginPane.JXLoginFrame frame = JXLoginPane.showLoginFrame(this);
	  frame.setTitle("Database Login Dialog");
	  frame.setAlwaysOnTop(true);
	  frame.setVisible(true);
	}

}

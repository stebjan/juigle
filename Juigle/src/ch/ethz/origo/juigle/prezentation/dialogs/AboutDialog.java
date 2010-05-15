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
package ch.ethz.origo.juigle.prezentation.dialogs;

import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.painter.PinstripePainter;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 09/06/09
 * @since 0.1.0 (05/18/09)
 * @see JUIGLEDialog
 */
public class AboutDialog extends JUIGLEDialog {

	private JXPanel contentPanel;

	/** Only for serialization */
	private static final long serialVersionUID = -6954165040360494938L;

	public AboutDialog() {
		super(new JXPanel());
		initialize();
	}
	
	
	private void initialize() {
		initContentPanel();
		this.setAlwaysOnTop(true);
		this.setVisible(true);
		this.pack();
		this.setLocation(getCenterPosition(this.getSize()));
	}

	private void initContentPanel() {
		contentPanel = (JXPanel)content;
		contentPanel.setBackgroundPainter(new PinstripePainter());
	}

}
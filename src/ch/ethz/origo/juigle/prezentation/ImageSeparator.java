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
package ch.ethz.origo.juigle.prezentation;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicToolBarSeparatorUI;

/**
 * This class provide painting of separator from image.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 07/23/09
 * @since 0.1.0 (05/18/09)
 * @see BasicToolBarSeparatorUI
 */
public class ImageSeparator extends BasicToolBarSeparatorUI {

	private Image image;
	
	/**
	 * Construct and paint separator
	 * 
	 * @param image your image which will be represented separator
	 */
	public ImageSeparator(Image image) {
		this.image = image;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paint(Graphics g, JComponent c) {
		g.drawImage(image, 0, 0, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Dimension getPreferredSize(JComponent c) {
		return new Dimension(image.getWidth(null), image.getHeight(null));
	}
}

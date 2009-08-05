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
 *    GraphicsUtilities.java
 *    Copyright (C) 2009 University of West Bohemia, 
 *                       Department of Computer Science and Engineering, 
 *                       Pilsen, Czech Republic
 */
package ch.ethz.origo.jerpaui.prezentation;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import com.jhlabs.image.NoiseFilter;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1. 05/18/09
 * @since 0.1.0 (05/18/09)
 */
public class GraphicsUtilities {
	
	public static final Color TRANSPARENT_COLOR = new Color(0, 0, 0, 0);

	private static final GraphicsConfiguration configuration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
			.getDefaultConfiguration();

	public static BufferedImage createTranslucentCompatibleImage(int width, int height) {
		return configuration.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
	}
	
	/**
	 * Create texture for background of component
	 * 
	 * @param color1 color number 1
	 * @param color2 color number 2
	 * @param size the size of created image
	 * @return created instance of class java.awt.Paint {@link Paint}
	 * @since 0.1.0
	 */
	public static Paint createBackgroundTexture(Color color1, Color color2, int size) {
		BufferedImage image = GraphicsUtilities.createTranslucentCompatibleImage(
				size, size);
		Graphics2D g2d = image.createGraphics();
		Paint paint = new GradientPaint(0, 0, color1, 0, size, color2);
		g2d.setPaint(paint);
		g2d.fillRect(0, 0, size, size);
		g2d.dispose();
		NoiseFilter filter = new NoiseFilter();
		filter.setAmount(10);
		filter.setDensity(0.5f);
		filter.setDistribution(NoiseFilter.UNIFORM);
		filter.setMonochrome(true);
		filter.filter(image, image);

		Paint result = new TexturePaint(image, new Rectangle(size, size));
		return result;
	}

}

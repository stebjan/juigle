package ch.ethz.origo.juigle.prezentation;

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
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.jdesktop.swingx.graphics.GraphicsUtilities;

import ch.ethz.origo.juigle.application.exceptions.PerspectiveException;

import com.jhlabs.image.NoiseFilter;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1. 05/18/09
 * @since 0.1.0 (05/18/09)
 */
public class JUIGLEGraphicsUtilities {

	public static final Color TRANSPARENT_COLOR = new Color(0, 0, 0, 0);

	private static final GraphicsConfiguration configuration = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getDefaultScreenDevice()
			.getDefaultConfiguration();

	public static BufferedImage createTranslucentCompatibleImage(int width,
			int height) {
		return configuration.createCompatibleImage(width, height,
				Transparency.TRANSLUCENT);
	}

	/**
	 * Create texture for background of component
	 * 
	 * @param color1
	 *          color number 1
	 * @param color2
	 *          color number 2
	 * @param size
	 *          the size of created image
	 * @return created instance of class java.awt.Paint {@link Paint}
	 * @since 0.1.0
	 */
	public static Paint createBackgroundTexture(Color color1, Color color2,
			int size) {
		BufferedImage image = JUIGLEGraphicsUtilities
				.createTranslucentCompatibleImage(size, size);
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

	/**
	 * Returns an ImageIcon, or null if the path was invalid.
	 * 
	 * @param path
	 * @param description
	 * @return
	 */
	public static Icon createImageIcon(String path, String description) {
		java.net.URL imgURL = ClassLoader.getSystemResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			// TODO udelat lepsi error
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
		
	public static Icon createImageIcon(String path, int width, int height) throws PerspectiveException {
			return new ImageIcon(GraphicsUtilities.createThumbnail(JUIGLEGraphicsUtilities.getImage(path), width, height));			
	}

	public static BufferedImage getImage(String path) throws PerspectiveException {
		try {
			return ImageIO.read(ClassLoader.getSystemResourceAsStream(path));
		} catch (IOException e) {
			throw new PerspectiveException(e);
		}
	}

}

package ch.ethz.origo.juigle.prezentation;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicToolBarSeparatorUI;

/**
 * This class provide painting of separator from image.
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
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
	
	@Override
	public void paint(Graphics g, JComponent c) {
		g.drawImage(image, 0, 0, null);
	}

	@Override
	public Dimension getPreferredSize(JComponent c) {
		return new Dimension(image.getWidth(null), image.getHeight(null));
	}
}

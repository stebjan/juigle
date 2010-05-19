package ch.ethz.origo.juigle.prezentation.splashscreen;

import java.awt.BorderLayout;
import java.awt.Image;

import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXImagePanel;
import org.jdesktop.swingx.JXFrame.StartPosition;

import ch.ethz.origo.juigle.application.exception.PerspectiveException;
import ch.ethz.origo.juigle.prezentation.JUIGLEGraphicsUtils;

/**
 * Class which create SplashScreen as instance of JXFrame.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (5/19/2010)
 * @since 0.1.0 (5/19/2010)
 * 
 */
public class SplashScreen {

	/** Frame where will be frame displayed */
	private JXFrame frame;
	/** SplashScreen's image */
	private Image image;

	/**
	 * Construct splash screen with image by given path
	 * 
	 * @param path
	 *          image path
	 * @throws PerspectiveException
	 *           image can not be created
	 */
	public SplashScreen(String path) throws PerspectiveException {
		this.image = JUIGLEGraphicsUtils.getImage(path);
	}

	/**
	 * Construct splash screen with specified image.
	 * 
	 * @param image specified image
	 */
	public SplashScreen(Image image) {
		this.image = image;
	}

	/**
	 * Method show splash screen
	 */
	public void show() {
		if (frame == null) {
			frame = new JXFrame();
			frame.setLayout(new BorderLayout());

			JXImagePanel imagePanel = new JXImagePanel();
			imagePanel.setImage(image);
			imagePanel.setEditable(false);
			frame.add(imagePanel, BorderLayout.CENTER);
			frame.setStartPosition(StartPosition.CenterInScreen);
			frame.setUndecorated(true);
			frame.pack();
			frame.setVisible(true);
		}
	}

	/**
	 * Method close splash screen (if is showed).
	 */
	public void close() {
		if (frame != null) {
			frame.setVisible(false);
			frame = null;
		}
	}

}
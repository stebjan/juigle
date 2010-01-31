package ch.ethz.origo.juigle.prezentation.perspective;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.util.ResourceBundle;

import javax.swing.Icon;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.painter.Painter;

import ch.ethz.origo.juigle.application.exception.PerspectiveException;
import ch.ethz.origo.juigle.application.listener.LanguageEvent;
import ch.ethz.origo.juigle.prezentation.JUIGLEFrame;
import ch.ethz.origo.juigle.prezentation.JUIGLEGraphicsUtils;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 07/12/09
 * @since 0.1.0
 */
public class Perspective extends AbstractPerspective {

	/** Only for serialization */
	private static final long serialVersionUID = 1111614707483173796L;
	
	protected String resourcePath;
	
	protected static final Logger logger = Logger.getLogger(Perspective.class);
	
	@Override
	public void initPerspectivePanel() throws PerspectiveException {
		// TODO udelat vysku panelu packove, barvy dle uzivatele
		if (mainPanel == null) {
			mainPanel = new JXPanel();
			final Paint perspectiveBackground = JUIGLEGraphicsUtils
					.createBackgroundTexture(Color.WHITE, Color.LIGHT_GRAY, 400);
			Painter<Component> p = new Painter<Component>() {
				@Override
				public void paint(Graphics2D g, Component c, int width, int height) {
					g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
							RenderingHints.VALUE_ANTIALIAS_ON);
					g.setPaint(perspectiveBackground);

					if (JUIGLEFrame.getFrameState() != JUIGLEFrame.MAXIMIZED_BOTH) {
						g.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20);
					} else {
						g.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 40, 40);
					}
				}
			};
			mainPanel.setOpaque(false);
			mainPanel.setBackgroundPainter(p);
		}
	}

	@Override
	public String getTitle() {
		return resource.getString(getRBPerspectiveTitleKey());
	}

	@Override
	public void initPerspectiveMenuPanel() throws PerspectiveException {
		// do nothing
	}

	@Override
	public void updateText() {
		setLocalizedResourceBundle(resourcePath);
	}

	@Override
  public String getLocalizedString(String string) {
    return resource.getString(string);
  }

	@Override
	public  void setLocalizedResourceBundle(String path) {
		resourcePath = path;
		resource = ResourceBundle.getBundle(path);
	}

	/**
	 * NOT USED FOR THI CLASS
	 */
	@Override
	public void setResourceBundleKey(String key) {
		// NOT USED FOR THIS CLASS
	}
	
	@Override
	public String getResourceBundlePath() {
		return resourcePath;
	}
	
	@Override
	// FIXME MOZNA ODSTRANIT JE TU ASI ZBYTECNA
	public void fireLanguageChanged(LanguageEvent e) {
		if (e.getId() == LanguageEvent.LANGUAGE_CHANGED) {
			menu.updateText();
			updateText();
			System.out.println("...Zmena jazyka v Perspective...");
		}		
	}

	@Override
	public Icon getIcon() throws PerspectiveException {
		return null;
	}

	@Override
	public String getRBPerspectiveTitleKey() {
		return "perspective.title";
	}

}
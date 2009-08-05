package ch.ethz.origo.jerpaui.prezentation.perspective;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;

import nezarazeno.PerspectiveException;

import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.painter.Painter;

import ch.ethz.origo.jerpaui.prezentation.GraphicsUtilities;
import ch.ethz.origo.jerpaui.prezentation.JUIGLEFrame;

public class Perspective extends AbstractPerspective {

	/** Only for serialization */
	private static final long serialVersionUID = 1111614707483173796L;

	@Override
	public void initPerspectivePanel() {
		// TODO udelat vysku panelu packove, barvy dle uzivatele
		if (mainPanel == null) {
			mainPanel = new JXPanel();
			final Paint perspectiveBackground = GraphicsUtilities
					.createBackgroundTexture(Color.YELLOW, Color.LIGHT_GRAY, 400);
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
		return "No title";
	}

	@Override
	public void initPerspectiveMenuPanel() throws PerspectiveException {
		// do nothing
	}

}
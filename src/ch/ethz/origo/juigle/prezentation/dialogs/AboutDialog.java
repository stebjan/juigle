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
package ch.ethz.origo.juigle.prezentation;

import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.SwingUtilities;


import org.jdesktop.swingx.JXButton;

import ch.ethz.origo.juigle.application.ILanguage;
import ch.ethz.origo.juigle.application.exceptions.JUIGLELangException;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 07/13/09
 * @since 0.1.0 (05/18/09)
 * @see JXButton
 * @see JButton
 * @see ILanguage
 */
public class JUIGLEButton extends JXButton implements ILanguage {

	/** Only for serialization */
	private static final long serialVersionUID = -2127873707157982700L;
	
	private boolean showText = true;
	
	private String resourcePath;
	private String resourceBundleKey;
	
	private ResourceBundle resource;
	
	/**
	 * Default constructor
	 */
	public JUIGLEButton() {
		
	}
	
	/**
	 * 
	 * @param show
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public void showText(boolean show) {
		this.showText = show;
	}
	
	/**
	 * 
	 * @return
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public boolean canBeTextShow() {
		return showText;
	}

	@Override
	public void setLocalizedResource() {
		this.resource = ResourceBundle.getBundle(resourcePath);
	}
	
	@Override
	public void setResourceBundlePath(String path) {
		this.resourcePath = path;
	}
	
	@Override
	public void setResourceBundleKey(String key) {
		this.resourceBundleKey = key;
	}

	@Override
	public void updateText() throws JUIGLELangException {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				setLocalizedResource();
				setText(resource.getString(resourceBundleKey));
			}		
		});
	}
}
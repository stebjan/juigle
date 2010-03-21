package ch.ethz.origo.juigle.prezentation;

import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXButton;

import ch.ethz.origo.juigle.application.ILanguage;
import ch.ethz.origo.juigle.application.exception.JUIGLELangException;

/**
 * Create modified <code>J(X)Button</code>.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * 
 * 
 * @version 0.1.2 (3/20/2010)
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
	private String resourceTooTipBundleKey;

	private ResourceBundle resource;

	/**
	 * Default constructor
	 */
	public JUIGLEButton() {

	}

	/**
	 * Create <code>Button</code> with resource bundle (localized file) path and
	 * key
	 * 
	 * @param resourceBundlePath
	 *          path of file with localized text
	 * @param resourceBundleKey
	 *          resource bundle properties key
	 * @version 0.1.0
	 * @since 0.1.1 (1/31/2010)
	 * 
	 */
	public JUIGLEButton(String resourceBundlePath, String resourceBundleKey) {
		this.resourcePath = resourceBundlePath;
		this.resourceBundleKey = resourceBundleKey;
	}

	/**
	 * Create <code>Button</code> with resource bundle (localized file) path and
	 * key
	 * 
	 * @param resourceBundlePath
	 *          path of file with localized text
	 * @param resourceBundleKey
	 *          resource bundle properties key
	 * @param resourceToolTipBundleKey
	 *          resource bundle properties key for tool tip text
	 * @version 0.1.0
	 * @since 0.1.2 (3/20/2010)
	 * 
	 */
	public JUIGLEButton(String resourceBundlePath, String resourceBundleKey,
			String resourceToolTipBundleKey) {
		this(resourceBundlePath, resourceBundleKey);
		this.resourceTooTipBundleKey = resourceToolTipBundleKey;
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
	public void setLocalizedResourceBundle(String path) {
		this.resourcePath = path;
		this.resource = ResourceBundle.getBundle(resourcePath);
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
				setLocalizedResourceBundle(resourcePath);
				try {
					if (resourceBundleKey != null) {
						setText(resource.getString(resourceBundleKey));
					}
					if (resourceTooTipBundleKey != null) {
						setToolTipText(resource.getString(resourceTooTipBundleKey));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 */
	@Override
	public String getResourceBundlePath() {
		return resourcePath;
	}

	/**
	 * Set resource bundle key for button's tool tip.
	 * 
	 * @param key
	 * 
	 * @version 0.1.0 (3/20/2010)
	 * @since 0.1.2 (3/20/2010)
	 */
	public void setToolTipResourceBundleKey(String key) {
		this.resourceTooTipBundleKey = key;
	}
	
}
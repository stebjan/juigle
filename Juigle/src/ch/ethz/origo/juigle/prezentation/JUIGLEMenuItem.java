package ch.ethz.origo.juigle.prezentation;

import java.awt.Color;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import ch.ethz.origo.juigle.application.ILanguage;
import ch.ethz.origo.juigle.application.exception.JUIGLELangException;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 07/16/09
 * @since 0.1.0 (05/18/09)
 */
public class JUIGLEMenuItem extends JMenuItem implements ILanguage {
	
	/** Only for serialization */
	private static final long serialVersionUID = -8011842643730136500L;
	
	private BufferedImage icon;
	
	private Color color;
		
	private Collection<JUIGLEMenuItem> subMenuList;
	
	private String resourceBundleKey;
	
	protected String resourcePath;
	
	private ResourceBundle resource;
	
	private boolean showText = true;
	
	/**
	 * Default constructor. Variables are not sets.
	 */
	public JUIGLEMenuItem() {
		super();
	}
	
	/**
	 * 
	 * 
	 * @param label
	 */
	public JUIGLEMenuItem(String text) {
		setText(text);
		setResourceBundleKey(text);
	}
	
	/**
	 * 
	 * 
	 * @param label
	 * @param action
	 */
	public JUIGLEMenuItem(String text, Action action) {
		this(text);
		setAction(action);
	}
		
	/**
	 * 
	 * 
	 * @param label
	 * @param action
	 * @param icon
	 */
	public JUIGLEMenuItem(String text, Action action, BufferedImage icon) {
		this(text, action);
	//	setIcon(icon);
	}

	public void setAction(Action action) {
		super.setAction(action);
	}

	public BufferedImage getItemIcon() {
		BufferedImage bi = new BufferedImage(getIcon().getIconWidth(), getIcon().getIconHeight(), Transparency.TRANSLUCENT);
		getIcon().paintIcon(null, bi.createGraphics(), 0, 0);
		return bi;
	}
/*
	public void setIcon(BufferedImage icon) {
		this.icon = icon;
	}*/
	
	public String getResourceBundleKey() {
		return resourceBundleKey;
	}
	
	@Override
	public void setResourceBundleKey(String key) {
		this.resourceBundleKey = key;
	}
	
	public Collection<JUIGLEMenuItem> getSubMenu() {
		return subMenuList;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
		
	public void addSubItem(JUIGLEMenuItem subItem) {
		if (subMenuList == null) {
			subMenuList = new ArrayList<JUIGLEMenuItem>();
		}
		subMenuList.add(subItem);
	}
	
	public boolean hasSubMenu() {
		return subMenuList != null;
	}

	/**
	 * 
	 * 
	 * @param show
	 */
	public void showText(boolean show) {
		this.showText = show;
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public boolean canBeTextShow() {
		return showText;
	}

	public void updateText(final String text) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				setText(text);				
			}
		});
	}
	
	protected boolean isOwnResourceBundleSets() {
		return resource == null ? false : true;
	}

	@Override
	public void setLocalizedResourceBundle(String resourcePath) {
		this.resourcePath = resourcePath;
		this.resource = ResourceBundle.getBundle(resourcePath);		
	}

	@Override
	public void updateText() throws JUIGLELangException {
		super.setText(resource.getString(getResourceBundleKey()));		
	}
	
	
	@Override
	public String getResourceBundlePath() {
		return resourcePath;
	}
	
}

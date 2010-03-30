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
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.2 (3/29/2010)
 * @since 0.1.0 (05/18/09)
 * @see JMenuItem
 * @see ILanguage
 */
public class JUIGLEMenuItem extends JMenuItem implements ILanguage {
	
	/** Only for serialization */
	private static final long serialVersionUID = -8011842643730136500L;
	
	private BufferedImage icon;
	
	private Color color;
		
	private Collection<JUIGLEMenuItem> subMenuList;
	
	private String resourceBundleKey;
	
	private String tooltipResourceBundleKey;
	
	protected String resourcePath;
	
	private ResourceBundle resource;
	
	private boolean showText = true;
	
	private boolean isToolTipText = false;
	
	private String toolTipText;
	
	/**
	 * Default constructor. Variables are not sets.
	 */
	public JUIGLEMenuItem() {
		//super();
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
	
	// version 0.1.0 since 0.1.1 (3/20/2010)
	public String getToolTipResourceBundleKey() {
		return tooltipResourceBundleKey;
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
	
	public void setToolTipResourceBundleKey(String tooltipResourceBundleKey) {
		this.tooltipResourceBundleKey = tooltipResourceBundleKey;
		isToolTipText = true;
	}
	
	public void setToolTipText(String text) {
		this.toolTipText = text;
	}
	
	public String getToolTipText() {
		return toolTipText;
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public boolean canBeTextShow() {
		return showText;
	}
	
	public boolean isToolTipTextExist() {
		return isToolTipText;
	}

	public void updateText(final String text) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				setText(text);				
			}
		});
	}
	
	public void updateToolTipText(final String text) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				setToolTipText(text);				
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
		setLocalizedResourceBundle(getResourceBundlePath());
		if (canBeTextShow() && getResourceBundleKey() != null) {
			super.setText(resource.getString(getResourceBundleKey()));			
		}
		if (isToolTipText) {
			setToolTipText(resource.getString(tooltipResourceBundleKey));
		}
	}
	
	@Override
	public String getResourceBundlePath() {
		return resourcePath;
	}
	
}
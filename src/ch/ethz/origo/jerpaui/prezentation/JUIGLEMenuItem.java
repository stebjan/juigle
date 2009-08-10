package ch.ethz.origo.jerpaui.prezentation;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JMenuItem;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 07/16/09
 * @since 0.1.0 (05/18/09)
 */
public class JUIGLEMenuItem extends JMenuItem {
	
	/** Only for serialization */
	private static final long serialVersionUID = -8011842643730136500L;

	//private String label;
	
	private Action action;
	
	private BufferedImage icon;
	
	private Color color;
		
	private Collection<JUIGLEMenuItem> subMenuList;
	
	/**
	 * Default constructor. Variables are not sets.
	 */
	public JUIGLEMenuItem() {
		
	}
	
	/**
	 * 
	 * 
	 * @param label
	 */
	public JUIGLEMenuItem(String text) {
		setText(text);
	}
	
	/**
	 * 
	 * 
	 * @param label
	 * @param action
	 */
	public JUIGLEMenuItem(String text, Action action) {
		setText(text);
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
		setIcon(icon);
	}
	
	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		super.setAction(action);
	}

	public BufferedImage getItemIcon() {
		return icon;
	}

	public void setIcon(BufferedImage icon) {
		this.icon = icon;
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
	
}

package ch.ethz.origo.juigle.prezentation;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;

import ch.ethz.origo.juigle.application.BrowserLaunch;
import ch.ethz.origo.juigle.application.exception.PerspectiveException;
import ch.ethz.origo.juigle.prezentation.perspective.Perspective;
import ch.ethz.origo.juigle.prezentation.perspective.PerspectivePanel;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.2.0 10/28/09
 * @since 0.1.0 (c.v. 08/14/09)
 * @see JUIGLEMenu
 */
public class JUIGLEMainMenu extends JUIGLEMenu {

	
	/** Only for serialization */
	private static final long serialVersionUID = 2839975234788319679L;
	
	public JUIGLEMainMenu() {
		super();
	}
	
	/**
	 * 
	 * 
	 * @param icon
	 * @param container
	 * @param perspectives
	 * @version 0.1.1
	 * @since 0.1.0
	 */
	public void addPerspectiveItems(Icon icon, final PerspectivePanel container, List<Perspective> perspectives) {
		JUIGLEMenuItem perspectivesItem = new JUIGLEMenuItem("Perspectives");
		if (icon != null) {
			perspectivesItem.setIcon(icon);
		}
		//Collections.sort(perspectives);

		for (final Perspective perspective : perspectives) {
			JUIGLEMenuItem item = new JUIGLEMenuItem();
			item.setLocalizedResourceBundle(perspective.getResourceBundlePath());
			item.setResourceBundleKey(perspective.getRBPerspectiveTitleKey());

			Action action = new AbstractAction() {
				private static final long serialVersionUID = 1796718465304187844L;
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						container.add(perspective);
					} catch (PerspectiveException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// TODO UPOZORNI OSTATNI PERSPECTIVY ZE SE MENI NA JINOU
				}
			};
			item.setAction(action);
			perspectivesItem.addSubItem(item);
		}
		this.addItem(perspectivesItem);
		this.revalidate();
	}
	
	public void addSettingsItem(Icon icon) {
		
	}
	
	public void addHomePageItem(Icon icon, final String url) throws PerspectiveException {
		JUIGLEMenuItem item = new JUIGLEMenuItem();
		Action action = new AbstractAction() {
			private static final long serialVersionUID = 1796718465304187844L;
			@Override
			public void actionPerformed(ActionEvent e) {
				BrowserLaunch.openURL(url);
			}
		};
		item.setAction(action);
		if (icon == null) {
			item.setIcon(JUIGLEGraphicsUtilities.createImageIcon("ch/ethz/origo/juigle/data/images/Firefox_48x48.png", 32, 32));
		}
		this.addItem(item);
		this.revalidate();
	}
	
	public void addAboutItem(Icon icon) {

	}
	
	public void addCalendarItem(Icon icon) {
		
	}	

}
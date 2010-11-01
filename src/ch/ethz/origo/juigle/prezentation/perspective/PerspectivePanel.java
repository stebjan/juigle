/*
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

/*
 *  
 *    Copyright (C) 2009 - 2010 
 *    							University of West Bohemia, 
 *                  Department of Computer Science and Engineering, 
 *                  Pilsen, Czech Republic
 */
package ch.ethz.origo.juigle.prezentation.perspective;

import java.awt.BorderLayout;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTitledPanel;

import ch.ethz.origo.juigle.application.exception.PerspectiveException;

/**
 * Construct main panel which contains always current dispalyed 
 * perspective. 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.2 (4/29/2010
 * @since 0.1.0 (07/19/09)
 * @see JXPanel
 * @see Perspective
 */
public class PerspectivePanel extends JXPanel {

	/** Only for serialization */
	private static final long serialVersionUID = -6773985483599106242L;

	private Perspective currentPerspective;

	/**
	 * Default constructor
	 * 
	 * @throws PerspectiveException
	 */
	public PerspectivePanel() throws PerspectiveException {

	}

	/**
	 * Create perspective panel and added perspective to this panel
	 * 
	 * @param perspective instance of perspective which will be added to the panel.
	 * @throws PerspectiveException
	 */
	public PerspectivePanel(Perspective perspective) throws PerspectiveException {
		setPerspective(perspective);
		initialize();
	}

	private void initialize() throws PerspectiveException {
		if (currentPerspective != null) {
			setLayout(new BorderLayout());
			currentPerspective.setLocalizedResourceBundle(currentPerspective
					.getResourceBundlePath());
			if (currentPerspective.getMainPerspectivePanel() == null) {
				currentPerspective.initPerspectivePanel();
			}
			if (currentPerspective.getMenuPanel() == null) {
				currentPerspective.initPerspectiveMenuPanel();
			}
			currentPerspective.updateText();

			if (currentPerspective.getMenu() != null) {
				if (currentPerspective.getMenuPanel() instanceof JXTitledPanel) {
					((JXTitledPanel) currentPerspective.getMenuPanel())
							.setTitle(currentPerspective.getTitle());
				} else if (currentPerspective.getMenuPanel() instanceof JXTaskPane) {
					((JXTaskPane) currentPerspective.getMenuPanel())
							.setTitle(currentPerspective.getTitle());
					((JXTaskPane) currentPerspective.getMenuPanel())
							.setIcon(currentPerspective.getPerspectiveIcon());
				}
				add(currentPerspective.getMenuPanel(), currentPerspective.getMenu()
						.getMenuPosition());
			}
			add(currentPerspective.getMainPerspectivePanel(), BorderLayout.CENTER);
			// TODO zvazit jestli pridat neco do footeru
			revalidate();
		}
	}

	/**
	 * Adding perspective to current view
	 * 
	 * @param perspective
	 *          new perspective which will be added to current perspective view
	 * @throws PerspectiveException
	 * @version 0.2.0 (1/24/2010)
	 * @throws InvocationTargetException
	 * @throws InterruptedException
	 * @since 0.1.0 (07/19/09)
	 */
	public void add(final Perspective perspective) throws PerspectiveException {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (currentPerspective != null) {
					removeAll();
					repaint();
					validate();
				}
				setPerspective(perspective);
				try {
					initialize();
					repaint();
					validate();
				} catch (PerspectiveException e) {
					// throw new PerspectiveException("JG005:" + perspective.getTitle(),
					// e);
					e.printStackTrace();
				}
			}
		});
	}

	private void setPerspective(Perspective perspective) {
		this.currentPerspective = perspective;
	}

	private void removeCurrentPerspective() {
		if (currentPerspective.getMenuPanel() != null) {
			this.remove(currentPerspective.getMenuPanel());
		}
		if (currentPerspective.getMainPerspectivePanel() != null) {
			this.remove(currentPerspective.getMainPerspectivePanel());
		}
		this.removeAll();
		this.repaint();
		this.validate();
	}
	
}
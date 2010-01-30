package ch.ethz.origo.juigle.prezentation.perspective;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTitledPanel;

import ch.ethz.origo.juigle.application.exception.PerspectiveException;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.1 09/13/09
 * @since 0.1.0 (07/19/09)
 * @see JXPanel
 * @see Perspective
 */
public class PerspectivePanel extends JXPanel {

	/** Only for serialization */
	private static final long serialVersionUID = -6773985483599106242L;

	private Perspective currentPerspective;
	
	private static int count = 1;

	private boolean test = true;

	/**
	 * Default constructor
	 * 
	 * @throws PerspectiveException
	 */
	public PerspectivePanel() throws PerspectiveException {
		
	}

	/**
	 * 
	 * @param perspective
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
			if (currentPerspective.getMenuPanel() == null ) {
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
							.setIcon(currentPerspective.getIcon());
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
					//test = false;
				}
				if (test) { // FIXME only for testing
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

	/*
	 * @Override public void fireLanguageChanged(LanguageEvent e) { // TODO tato
	 * metoda mozna nepouzita if (e.getId() == LanguageEvent.LANGUAGE_CHANGED) {
	 * this.setTitle(currentPerspective.getTitle()); // tento kod nefunguje
	 * System.out.println("....Zmena titulku v Perspective Panelu....."); }
	 * 
	 * }
	 */
}
package ch.ethz.origo.juigle.prezentation.perspective;

import java.awt.BorderLayout;

import org.jdesktop.swingx.JXTitledPanel;

import ch.ethz.origo.juigle.application.exceptions.PerspectiveException;
import ch.ethz.origo.juigle.application.listener.LanguageEvent;
import ch.ethz.origo.juigle.application.listener.LanguageListener;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 07/19/09
 * @since 0.1.0
 */
public class PerspectivePanel extends JXTitledPanel implements LanguageListener {

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
	 * 
	 * @param perspective
	 * @throws PerspectiveException
	 */
	public PerspectivePanel(Perspective perspective) throws PerspectiveException {
		setPerspective(perspective);
		initialize();
	}

	private void initialize() throws PerspectiveException {
		// this.removeAll();
		if (currentPerspective != null) {
			//this.setLayout(new BorderLayout());
			this.setOpaque(false);
			currentPerspective.setResourceBundlePath(currentPerspective.getResourceBundlePath());
			currentPerspective.setLocalizedResource();
			currentPerspective.initPerspectivePanel();
			currentPerspective.initPerspectiveMenuPanel();
			currentPerspective.updateText();
			this.setTitle(currentPerspective.getTitle()); // TODO tento kod nefunguje
			if (currentPerspective.getMenu() != null) {
				currentPerspective.getMenuPanel().setTitle(currentPerspective.getTitle());
				this.add(currentPerspective.getMenuPanel(), currentPerspective
						.getMenu().getMenuPosition());
			}
			this.add(currentPerspective.getMainPerspectivePanel(),
					BorderLayout.CENTER);
			// TODO zvazit jestli pridat neco do footeru
		}
		this.revalidate();
	}

	public void add(Perspective perspective) throws PerspectiveException {
		setPerspective(perspective);
		initialize();
	}

	private void setPerspective(Perspective perspective) {
		this.currentPerspective = perspective;
	}

	@Override
	public void fireLanguageChanged(LanguageEvent e) {
		if (e.getId() == LanguageEvent.LANGUAGE_CHANGED) {
			this.setTitle(currentPerspective.getTitle()); // tento kod nefunguje
			System.out.println("....Zmena titulku v Perspective Panelu.....");
		}
	}

}
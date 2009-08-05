package ch.ethz.origo.jerpaui.prezentation.perspective;

import java.awt.BorderLayout;

import nezarazeno.PerspectiveException;

import org.jdesktop.swingx.JXTitledPanel;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 07/19/09
 * @since 0.1.0
 */
public class PerspectivePanel extends JXTitledPanel {

	/** Only for serialization */
	private static final long serialVersionUID = -6773985483599106242L;

	private Perspective currentPerspective;

	/**
	 * Default constructor
	 * 
	 * @throws PerspectiveException
	 */
	public PerspectivePanel() throws PerspectiveException {
		super("aaaaaaaa");
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
		this.setLayout(new BorderLayout());

		this.setOpaque(false);

		if (currentPerspective != null) {
			currentPerspective.initPerspectivePanel();
			currentPerspective.initPerspectiveMenuPanel();
			this.setTitle(currentPerspective.getTitle());
			if (currentPerspective.getMenu() != null) {
				this.add(currentPerspective.getMenuPanel(), currentPerspective
						.getMenu().getMenuPosition());

				// this.add(currentPerspective.getMenu(),
				// currentPerspective.getMenu().getMenuPosition());
			}
			this.add(currentPerspective.getMainPerspectivePanel(),
					BorderLayout.CENTER);
			// TODO zvazit jestli pridat neco do footeru
		}
	}

	public void add(Perspective perspective) throws PerspectiveException {
		setPerspective(perspective);
		initialize();
	}

	private void setPerspective(Perspective perspective) {
		this.currentPerspective = perspective;
	}

}
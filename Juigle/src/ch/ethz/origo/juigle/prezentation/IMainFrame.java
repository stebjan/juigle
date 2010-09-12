package ch.ethz.origo.juigle.prezentation;

import ch.ethz.origo.juigle.application.ILanguage;
import ch.ethz.origo.juigle.application.exception.PerspectiveException;
import ch.ethz.origo.juigle.application.observers.IObserver;
import ch.ethz.origo.juigle.prezentation.menu.JUIGLEMainMenu;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail dot com)
 * @version 0.1.0 (9/04/2010)
 * @since 2.0.0 (9/04/2010)
 * @see IObserver
 * @see ILanguage
 */
public interface IMainFrame extends IObserver, ILanguage {
	
	/**
	 * 
	 * @return
	 * @throws PerspectiveException
	 * @version 0.1.0 (9/04/2010)
	 * @since 0.1.0 (9/04/2010)
	 */
	public JUIGLEMainMenu getMainMenu() throws PerspectiveException;
	
	/**
	 * 
	 * @version 0.1.0 (9/04/2010)
	 * @since 0.1.0 (9/04/2010)
	 */
	public void applicationClose();
	
	/**
	 * 
	 * @version 0.1.0 (9/04/2010)
	 * @since 0.1.0 (9/04/2010)
	 */
	public String getLogoPath();
}

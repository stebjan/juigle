package ch.ethz.origo.juigle.application.project;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.2 (2/21/2010) 
 * @since 0.1.0 (06/07/2009)
 *     
 */
public interface Caretaker {

	/**
	 * Return class which represents memento state of project.
	 * 
	 * @return project memento state Class
	 */
	public Project getState();
}

package ch.ethz.origo.juigle.application.project;

/**
 * Memento Pattern for undo/redo mechanism in projects.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.1 (2/21/2010) 
 * @since 0.1.0 (10/21/2009)
 *      
 */
public interface IProjectOriginator {

	public ProjectMementoCaretaker createMemento();
	
	public void setMemento(ProjectMementoCaretaker memento);
	
}

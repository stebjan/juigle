package ch.ethz.origo.juigle.application.project;

import ch.ethz.origo.juigle.application.exception.ProjectOperationException;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/21/2010)
 * @since 0.1.0
 *
 */
public interface IProjectLoader {
	
	/**
	 * Load project
	 * 
	 * @return loaded project
	 * @throws ProjectOperationException
	 * @version 0.1.0 (3/21/2010)
	 * @since 0.1.0 (3/21/2010)
	 */
	public Project loadProject() throws ProjectOperationException;

}

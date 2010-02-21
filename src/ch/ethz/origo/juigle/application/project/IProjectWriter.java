package ch.ethz.origo.juigle.application.project;

import ch.ethz.origo.juigle.application.exception.ProjectWriterException;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (2/21/2010)
 * @since 0.1.0 (2/21/2010)
 *
 */
public interface IProjectWriter {

	public void saveProject() throws ProjectWriterException;
}

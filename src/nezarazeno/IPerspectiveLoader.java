package nezarazeno;

import java.util.List;

import ch.ethz.origo.juigle.application.exception.PerspectiveException;
import ch.ethz.origo.juigle.prezentation.perspective.Perspective;

/**
 * Interface for loader of perspective.
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.1 (2/7/2010)
 * @since 0.1.0 (7/20/09)
 */
public interface IPerspectiveLoader {

	/**
	 * List of available perspectives.
	 * 
	 * @return List of available perspectives as Collection
	 * @throws PerspectiveException
	 */
	public List<Perspective> getListOfPerspectives() throws PerspectiveException;
	
	/**
	 * Return default (main) perspective
	 * 
	 * @return default perspective
	 */
	public Perspective getDefaultPerspective(); 
	
}

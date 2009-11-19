package nezarazeno;

import java.util.Collection;
import java.util.List;

import ch.ethz.origo.juigle.application.exception.PerspectiveException;
import ch.ethz.origo.juigle.prezentation.perspective.Perspective;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 07/20/09
 * @since 0.1.0
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

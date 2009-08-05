package nezarazeno;

import java.util.Collection;

import ch.ethz.origo.jerpaui.prezentation.perspective.Perspective;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 07/20/09
 * @since 0.1.0
 */
public interface IPerspectiveLoader {

	/**
	 * 
	 * 
	 * @return
	 * @throws PerspectiveException
	 */
	public Collection<Perspective> getListOfPerspectives() throws PerspectiveException;
	
	/**
	 * 
	 * 
	 * @return
	 */
	public Perspective getDefaultPerspective(); 
	
}

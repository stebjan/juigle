package ch.ethz.origo.juigle.application;

import java.util.List;

import ch.ethz.origo.juigle.application.exception.PerspectiveException;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0.00 (9/30/2010)
 * @since 2.0.0 (9/30/2010)
 */
public interface IPerspectiveReader {

	/**
	 * Return name of default perspective
	 * 
	 * @return name of default perspective
	 */
	public String getDefaultPerspectiveName();

	/**
	 * Return perspective package path
	 * 
	 * @param perpectiveName
	 *          name of perspective
	 * 
	 * @return perspective package path
	 */
	public String getPerspectivePackagePath(String perpectiveName);

	public List<String> getListOfInnerPerspectivesNames()
	    throws PerspectiveException;

	public void setPerspectiveFilePath(String filePath);
	
	public void readFile() throws PerspectiveException;

}

package ch.ethz.origo.juigle.data.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.ethz.origo.juigle.application.IPerspectiveReader;
import ch.ethz.origo.juigle.application.exception.PerspectiveException;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0.00 (9/30/2010)
 * @since 2.0.0 (9/30/2010)
 * @see AXMLReader
 * @see IPerspectiveReader
 */
public class XMLPerspectiveReader extends AXMLReader implements
    IPerspectiveReader {

	private String defaultPerspective;
	
	private List<String> listOfPerspectiveNames;
	
	private Map<String, String> mapOfPerspPackages;

	public XMLPerspectiveReader(String xml) {
		super(xml);
	}

	/**
	 * 
	 * @return
	 * @throws PerspectiveException
	 * @version 0.1.0.00 (9/24/2010)
	 * @since 0.1.0.0 (9/24/2010)
	 */
	@Override
	public List<String> getListOfInnerPerspectivesNames() throws PerspectiveException {
		return listOfPerspectiveNames;
	}

	@Override
	public String getDefaultPerspectiveName() {
		return defaultPerspective;
	}

	@Override
	public void setPerspectiveFilePath(String filePath) {
		setXMLPath(filePath);
	}

	@Override
  public String getPerspectivePackagePath(String perspectiveName) {
	 return mapOfPerspPackages.get(perspectiveName);
  }

	@Override
  public void readFile() throws PerspectiveException {
		if (xmlFile == null) {
			throw new PerspectiveException("JG025");
		}
		listOfPerspectiveNames = new ArrayList<String>();
		mapOfPerspPackages = new HashMap<String, String>();
		//TODO
	  throw new UnsupportedOperationException("Method is not implemented yet...");
  }
	
	

}
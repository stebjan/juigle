package ch.ethz.origo.juigle.data.xml;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0.00 (9/24/2010)
 * @since 2.0.0 (9/24/2010)
 *
 */
public abstract class AXMLReader {
	
	protected String xmlFile;
	
	public AXMLReader(String xmlFile) {
		setXMLPath(xmlFile);
	}
	
	/**
	 * 
	 * @param xmlFile
	 * @version 0.1.0.00 (9/24/2010)
	 * @since 0.1.0.00 (9/24/2010)
	 */
	public void setXMLPath(String xmlFile) {
		this.xmlFile = xmlFile;
	}

}

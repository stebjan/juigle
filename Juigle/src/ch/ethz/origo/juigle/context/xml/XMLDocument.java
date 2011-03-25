package ch.ethz.origo.juigle.context.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.helpers.DefaultHandler;

import ch.ethz.origo.juigle.context.exceptions.XMLException;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see DefaultHandler
 * @version 1.0.0 (3/25/2011)
 * @since 2.0.0 (3/25/2011)
 */
public class XMLDocument extends DefaultHandler {

	/** Logger for this class */
	private static final Logger logger = Logger.getLogger(XMLDocument.class);

	private DocumentBuilderFactory dbf;
	private Document doc;

	public XMLDocument() {
		// do nothing
	}

	public XMLDocument(String xmlFile) {
		parseDocument(xmlFile);
	}

	public Document parseDocument(String xmlFile) {
		try {
			dbf = DocumentBuilderFactory.newInstance(); //
			dbf.setValidating(false); // nechceme validovat
			dbf.setIgnoringComments(false); // ignorace komentaru nepovolena
			DocumentBuilder buider = dbf.newDocumentBuilder(); // vytvoreni vlastniho
																												 // parseru - ve
																												 // skutecnosti je to
																												 // XERCES
			buider.setErrorHandler(new XMLException()); // nastaveni reakce na chyby
			// nacteni dokumentu do pameti
			doc = buider.parse(xmlFile);
			// pro programatorske ucely - ODSTRANIT PRI vysledne verzi
			logger.info("File : " + xmlFile + " is valid");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
		return doc;
	}
	
}

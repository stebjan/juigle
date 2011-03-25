package ch.ethz.origo.juigle.context.exceptions;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see ErrorHandler
 * @version 1.0.0 (3/25/2011)
 * @since 2.0.0 (3/25/2011)
 */
public class XMLException implements ErrorHandler {

	private String xmlTextException(SAXParseException e) {
		String exception = "";
		exception = e.getSystemId() + "\n" + "row: " + e.getLineNumber() + "\n"
				+ "column: " + e.getColumnNumber() + "\n" + "row: " + e.getMessage();
		return exception;
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		System.out.println("XML Error\n" + xmlTextException(e));

	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		System.out.println("XML FatalError\n" + xmlTextException(e));

	}

	@Override
	public void warning(SAXParseException e) throws SAXException {
		System.out.println("XML Warning" + xmlTextException(e));

	}
}

package ch.ethz.origo.juigle.context.xml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

import ch.ethz.origo.juigle.context.exceptions.LoaderXMLException;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 1.0.0 (3/25/2011)
 * @since 2.0.0 (3/25/2011)
 */
public class XMLParser {

	/** Logger for this class */
	private static final Logger logger = Logger.getLogger(XMLParser.class);
	//
	private static final String METHOD_PREFIX = "parse_";
	//
	private Class<?> commandHandlerArgTypes = Node.class;
	//
	protected NodeIterator nodeIt;

	/**
	 * 
	 * 
	 * @since 1.0.0 (3/25/2011)
	 * @param doc
	 * @return
	 */
	private NodeIterator loadNode(Document doc, NodeFilter filter) {
		return ((DocumentTraversal) doc).createNodeIterator(
				doc.getDocumentElement(), // korenovy nod
				NodeFilter.SHOW_ELEMENT + NodeFilter.SHOW_TEXT, // typy nodu pro
																												// zobrazeni
				filter, false); // vybrani - filtrovani danych nodu, false - expenze
												// entit jen pro dokumenty vyuzivajici DTDO
	}

	/**
	 * 
	 * 
	 * @since 1.0.0 (3/25/2011)
	 * @param team
	 * @param nodeFilter
	 * @throws LoaderXMLException
	 */
	protected void loadDocument(String documentPath, NodeFilter nodeFilter)
			throws LoaderXMLException {
		XMLDocument xmlD = new XMLDocument();
		Document doc = xmlD.parseDocument(documentPath);
		nodeIt = loadNode(doc, nodeFilter);

		Node node;
		while ((node = nodeIt.nextNode()) != null) {
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Method commandHandler;
				Object args[] = { node };
				try {
					commandHandler = getClass().getMethod(
							METHOD_PREFIX + node.getNodeName(), commandHandlerArgTypes);
					// logger.info("Calling method=" + commandHandler.getName());
					commandHandler.invoke(this, args);
					// FIXME - dodelat chybovy engine
				} catch (SecurityException e) {
					throw new LoaderXMLException(e);
				} catch (NoSuchMethodException e) {
					throw new LoaderXMLException(e);
				} catch (InvocationTargetException e) {
					throw new LoaderXMLException(e);
				} catch (IllegalArgumentException e) {
					throw new LoaderXMLException(e);
				} catch (IllegalAccessException e) {
					throw new LoaderXMLException(e);
				}
			}
		}
		endOfDocument();
	}

	/**
	 * Method is called by <code>XMLParser</code> engine if position of the parser
	 * is on end of document. In this method you can provide last work with XML
	 * parser and cleanup objects.
	 * 
	 * @since 1.0.0 (3/25/2011)
	 */
	protected void endOfDocument() {
		// do nothing for this
	}
}

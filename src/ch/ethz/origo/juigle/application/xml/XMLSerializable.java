package ch.ethz.origo.juigle.application.xml;

import org.w3c.dom.Node;
import org.w3c.dom.Document;

/**
 * XML serialization
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public interface XMLSerializable {
	
    /**
     * Create XML structure from class
     *
     * @param doc Document - new dom document
     * @return Node - is highest node of this dom-structure
     */
    public Node xmlSave(Document doc);

    /**
     * Initialization of class with DOM node
     * Note: usege in constructor this(Element element)
     *
     * @param node Node - is highest node of this dom-structure
     */
    public void xmlLoad(Node node);
}

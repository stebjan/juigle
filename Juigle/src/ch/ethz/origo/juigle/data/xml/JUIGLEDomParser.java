package ch.ethz.origo.juigle.data.xml;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/02/2010)
 * @since 0.1.0 (3/02/2010)
 *
 */
public class JUIGLEDomParser {
	
	/**
   * Static method. Return PCDATA text of element
   * 
   * @param element parent
   * @param elementName String
   * @return String PCDATA
   */
  public static String getTextFromElement(Element element, String elementName) {
    final int FIRST = 0;
    NodeList nlist = element.getElementsByTagName(elementName); // subnodes
    if (nlist.getLength() > 0) {
      NodeList nnlist = nlist.item(FIRST).getChildNodes(); // text nodes
      if (nnlist.getLength() > 0) {
        return nnlist.item(FIRST).getNodeValue();        
      } else {
        return null;
      }
    } else {
      return null;
    }
  }

}

package ch.ethz.origo.juigle.context.xml;

import org.w3c.dom.Node;

/**
*
*
* @author Vaclav Souhrada (v.souhrada at gmail.com)
* @version 1.0.0 (3/25/2011)
* @since 2.0.0 (3/25/2011)
*/
public class XMLUtils {

 /**
  *
  * @since 1.0.0 (3/25/2011)
  * @param node
  * @param attributeName
  * @return
  */
 public static String getAttributeValue(Node node, String attributeName) {
   if (node.hasAttributes()) {
     Node resultNode = node.getAttributes().getNamedItem(attributeName);
     if (resultNode != null) {
       return resultNode.getNodeValue();
     }
   }

   return null;
 }

 /**
  *
  * @param node
  * @since 1.0.0 (3/25/2011)
  * @return
  */
 public static String getTextNodeValue(Node node) {
   return node.getTextContent().trim();
 }
 
}

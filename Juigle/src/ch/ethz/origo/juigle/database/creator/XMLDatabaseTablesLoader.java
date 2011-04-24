package ch.ethz.origo.juigle.database.creator;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeFilter;

import ch.ethz.origo.juigle.context.exceptions.LoaderXMLException;
import ch.ethz.origo.juigle.context.exceptions.XMLDatabaseLoaderException;
import ch.ethz.origo.juigle.context.xml.XMLParser;
import ch.ethz.origo.juigle.context.xml.XMLUtils;
import ch.ethz.origo.juigle.database.model.DBColumn;
import ch.ethz.origo.juigle.database.model.DBIndex;
import ch.ethz.origo.juigle.database.model.DBIndexColumn;
import ch.ethz.origo.juigle.database.model.DBModel;
import ch.ethz.origo.juigle.database.model.DBTable;
import ch.ethz.origo.juigle.database.model.FKColumnPair;
import ch.ethz.origo.juigle.database.model.constraint.DBForeignKeyConstraint;
import ch.ethz.origo.juigle.database.model.constraint.DBPrimaryKeyConstraint;
import ch.ethz.origo.juigle.database.model.constraint.DBUniqueConstraint;
import ch.ethz.origo.juigle.database.model.types.ASQLDataType;
import ch.ethz.origo.juigle.database.model.types.SQLBoolean;
import ch.ethz.origo.juigle.database.model.types.SQLChar;
import ch.ethz.origo.juigle.database.model.types.SQLDate;
import ch.ethz.origo.juigle.database.model.types.SQLInteger;
import ch.ethz.origo.juigle.database.model.types.SQLLong;
import ch.ethz.origo.juigle.database.model.types.SQLSmallint;
import ch.ethz.origo.juigle.database.model.types.SQLTime;
import ch.ethz.origo.juigle.database.model.types.SQLTimestamp;
import ch.ethz.origo.juigle.database.model.types.SQLVarchar;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see XMLParser
 * @version 0.3.0 (3/6/2011)
 * @since 1.0.0 (1/11/2011)
 */
public class XMLDatabaseTablesLoader extends XMLParser {

  /** Logger for this class */
  private static final Logger logger = Logger.getLogger(
          XMLDatabaseTablesLoader.class);
  /** XML Nodes */
  private static final String NODE_ROOT = "tables";
  private static final String NODE_TABLE = "table";
  private static final String NODE_COLUMN = "column";
  private static final String NODE_PRIMARY_KEY = "primaryKey";
  private static final String NODE_PK = "pk";
  private static final String NODE_INDEX = "index";
  private static final String NODE_IDX = "idx";
  private static final String NODE_UNIQUE = "unique";
  private static final String NODE_UNQ = "uqe";
  private static final String NODE_RELATIONSHIPS = "relationships";
  private static final String NODE_RELATION = "relation";
  private static final String NODE_PAIR = "pair";
  /** XML Attributes */
  private static final String ATTRIBUTE_NAME = "name";
  private static final String ATTRIBUTE_TYPE = "type";
  private static final String ATTRIBUTE_SIZE = "size";
  private static final String ATTRIBUTE_ZONE = "zone";
  private static final String ATTRIBUTE_DEFAULT = "default";
  private static final String ATTRIBUTE_COLUMN = "column";
  private static final String ATTRIBUTE_PARENT = "parent";
  private static final String ATTRIBUTE_ON_DELETE = "onDelete";
  private static final String ATTRIBUTE_NOT_NULL = "notNull";
  private static final String ATTRIBUTE_ORDER_ASC = "orderASC";
  private static final String ATTRIBUTE_CHILD = "child";
  private IDBStructureCreator dbStructureCreator;
  private DBModel model;
  private DBTable table;

  public XMLDatabaseTablesLoader(EDBStructureCreatorType creator) {
    if (creator == EDBStructureCreatorType.DATABASE) {
      dbStructureCreator = new DBStructureCreator();
    } else {
      dbStructureCreator = new SQLFileDBStructureCreator();
    }
    model = new DBModel();
    dbStructureCreator.setDatabaseModel(model);
  }

  /**
   *
   * 
   * @version 0.1.2 (1/30/2011)
   * @since 0.2.0 (1/22/2011)
   * @param documentPath
   * @return
   * @throws DatabaseLoaderException
   */
  public IDBStructureCreator parseDocument(String documentPath)
          throws XMLDatabaseLoaderException {
    try {
      loadDocument(documentPath, new BasicFilterXML());
    } catch (LoaderXMLException ex) {
      logger.error(ex);
      throw new XMLDatabaseLoaderException(ex);
    }

    return dbStructureCreator;
  }

  /**
   *
   * 
   * @version 0.1.2 (1/22/2011)
   * @since 0.1.0 (1/11/2011)
   * @param node
   */
  public void parse_table(Node node) {
    if (table == null) {
      table = new DBTable(node.getAttributes().getNamedItem(
              ATTRIBUTE_NAME).getNodeValue());
    } else {
      model.addTable(table);
      table = null;
      parse_table(node);
    }
  }

  /**
   *
   *
   * @version 0.2.1 (1/22/2011)
   * @since 0.1.0 (1/11/2011)
   * @param node
   */
  public void parse_column(Node node) {
    ASQLDataType sqlType = null;
    String type = XMLUtils.getAttributeValue(node, ATTRIBUTE_TYPE);
    // now set database sql type
    if (type.equals(SQLChar.CHAR_TYPE)) {
      sqlType = new SQLChar(Integer.valueOf(XMLUtils.getAttributeValue(node,
              ATTRIBUTE_SIZE)));
    } else if (type.equals(SQLVarchar.VARCHAR_TYPE)) {
      sqlType = new SQLVarchar(Integer.valueOf(XMLUtils.getAttributeValue(node,
              ATTRIBUTE_SIZE)));
    } else if (type.equals(SQLTimestamp.TIMESTAMP_TYPE)) {
      sqlType = new SQLTimestamp();
      //FIXME neparsuji ZONE
    } else if (type.equals(SQLDate.DATE_TYPE)) {
      sqlType = new SQLDate();
    } else if (type.equals(SQLInteger.INTEGER_TYPE)) {
      sqlType = new SQLInteger();
    } else if (type.equals(SQLLong.LONG_TYPE)) {
      sqlType = new SQLLong();
    } else if (type.equals(SQLSmallint.SMALLINT_TYPE)) {
      sqlType = new SQLSmallint();
    } else if (type.equals(SQLBoolean.BOOLEAN_TYPE)) {
      sqlType = new SQLBoolean();
      // FIXME neparsuji DEFAULT
    } else if (type.equals(SQLTime.TIME_TYPE)) {
      sqlType = new SQLTime();
    }

    String isNotNull = XMLUtils.getAttributeValue(node, ATTRIBUTE_NOT_NULL);
    if (isNotNull == null) {
      table.addColumn(new DBColumn(XMLUtils.getAttributeValue(node,
              ATTRIBUTE_NAME), sqlType));
    } else {
      table.addColumn(new DBColumn(XMLUtils.getAttributeValue(node,
              ATTRIBUTE_NAME), sqlType, Boolean.valueOf(isNotNull)));
    }
  }

  /**
   *
   *
   * @version 0.2.0 (1/22/2011)
   * @since 0.1.0 (1/11/2011)
   * @param node
   */
  public void parse_primaryKey(Node node) {
    DBPrimaryKeyConstraint pk = new DBPrimaryKeyConstraint(
            XMLUtils.getAttributeValue(node, ATTRIBUTE_NAME),
            table.getComponentName());
    NodeList nodeList = node.getChildNodes();
    for (int i = 0; i < nodeList.getLength(); i++) {
      Node childNode = nodeList.item(i);
      if (!isNewlineNode(childNode)) {
        pk.addColumnName(XMLUtils.getAttributeValue(childNode,
                ATTRIBUTE_COLUMN));
      }
    }
    table.addConstraint(pk);
  }

  /**
   *
   *
   * @version 0.2.0 (1/23/2011)
   * @since 0.1.0 (1/11/2011)
   * @param node
   */
  public void parse_index(Node node) {
    DBIndex index = new DBIndex(XMLUtils.getAttributeValue(node, ATTRIBUTE_NAME),
            table.getComponentName());
    NodeList nodeList = node.getChildNodes();
    for (int i = 0; i < nodeList.getLength(); i++) {
      Node childNode = nodeList.item(i);
      if (!isNewlineNode(childNode)) {
        DBIndexColumn column = new DBIndexColumn();
        column.setName(XMLUtils.getAttributeValue(childNode,
                ATTRIBUTE_COLUMN));
        String orderASC = XMLUtils.getAttributeValue(childNode,
                ATTRIBUTE_ORDER_ASC);
        if (orderASC != null) {
          column.setOrder(new Boolean(orderASC));
        }
        index.addColumn(column);
      }
    }
    table.addIndex(index);
  }

  /**
   *
   *
   * @version 0.1.0 (1/23/2011)
   * @since 0.2.1 (1/23/2011)
   * @param node
   */
  public void parse_unique(Node node) {
    DBUniqueConstraint unique = new DBUniqueConstraint(
            XMLUtils.getAttributeValue(node, ATTRIBUTE_NAME),
            table.getComponentName());
    NodeList nodeList = node.getChildNodes();
    for (int i = 0; i < nodeList.getLength(); i++) {
      Node childNode = nodeList.item(i);
      if (!isNewlineNode(childNode)) {
        unique.addColumnName(XMLUtils.getAttributeValue(childNode,
                ATTRIBUTE_COLUMN));
      }
    }
    table.addConstraint(unique);
  }

  /**
   *
   * 
   * @version 0.1.0 (1/23/2011)
   * @since 0.2.1 (1/23/2011)
   * @param node
   */
  public void parse_relation(Node node) {
    DBForeignKeyConstraint fkConstraint = new DBForeignKeyConstraint(
            XMLUtils.getAttributeValue(node, ATTRIBUTE_NAME),
            table.getComponentName(),
            XMLUtils.getAttributeValue(node, ATTRIBUTE_PARENT));
    // TODO - nacitani onDelete - cascade delete
    NodeList nodeList = node.getChildNodes();
    for (int i = 0; i < nodeList.getLength(); i++) {
      Node childNode = nodeList.item(i);
      if (!isNewlineNode(childNode)) {
        FKColumnPair pair = new FKColumnPair();
        pair.setChildName(XMLUtils.getAttributeValue(childNode, ATTRIBUTE_CHILD));
        pair.setParentName(XMLUtils.getAttributeValue(childNode, ATTRIBUTE_PARENT));
        fkConstraint.addFKColumnPair(pair);
      }
    }
    model.addRelation(fkConstraint);

  }

  @Override
  protected void endOfDocument() {
    if (table != null) {
      model.addTable(table);
      table = null;
    }
  }

  /**
   *
   * 
   * @version 0.1.0 (1/22/2011)
   * @since 0.2.0 (1/22/2011)
   * @param node
   * @return
   */
  public boolean isNewlineNode(Node node) {
    if (node.getNodeType() == Node.TEXT_NODE
            && node.getNodeValue().trim().length() == 0) {

      return true;
    }

    return false;
  }

  /**
   *
   *
   * @author Vaclav Souhrada (v.souhrada at gmail.com)
   * @see NodeFilter
   * @version 0.1.0 (1/22/2011)
   * @since 0.2.0 (1/22/2011)
   */
  public class BasicFilterXML implements NodeFilter {

    /**
     * {@inheritDoc}
     */
    @Override
    public short acceptNode(Node node) {
      String nodeName = node.getNodeName();
      if (nodeName.equals(NODE_ROOT) || nodeName.equals(NODE_PK)
              || nodeName.equals(NODE_IDX) || nodeName.equals(NODE_UNQ)
              || nodeName.equals(NODE_RELATIONSHIPS)
              || nodeName.equals(NODE_PAIR)) {
        return NodeFilter.FILTER_SKIP;
      }
      if (node.getNodeType() == Node.TEXT_NODE
              && node.getNodeValue().trim().length() == 0) {
        // odradkovani
        return NodeFilter.FILTER_SKIP;
      }

      return NodeFilter.FILTER_ACCEPT;
    }
  }
}

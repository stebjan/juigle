package ch.ethz.origo.juigle.database.dbi;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.2.2 (3/13/2011)
 * @since 1.0.0 (2/6/2011)
 */
public class Condition {

  /**
   *
   * @param columnName
   * @param value
   *
   * @version 1.0.2 (3/13/2011)
   * @since 0.2.0 (2/24/2011)
   * @return
   */
  public static Block equals(String columnName, Object value) {
    Block block = new Block(Block.BlockType.AND);
    if (value instanceof String) {
      block.setBlockCondition(columnName + "='" + value + "'");
    } else {
      block.setBlockCondition(columnName + "=" + String.valueOf(value));
    }
    
    return block;
  }
}

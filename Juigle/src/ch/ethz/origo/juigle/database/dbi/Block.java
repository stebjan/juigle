package ch.ethz.origo.juigle.database.dbi;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (2/24/2011)
 * @since 1.0.0 (2/24/2011)
 */
public class Block {

  private String condition;
  
  public enum BlockType {
    AND,
    OR,
    NOT
  }

  public static final String AND = "and";
  public static final String OR = "or";

  private BlockType type;

  public Block(BlockType type) {
    this.type = type;
  }

  public BlockType getType() {
    return type;
  }

  public boolean isTypeAnd() {
    return type == BlockType.AND;
  }

  public boolean isTypeOr() {
    return type == BlockType.OR;
  }

  public boolean isTypeNot() {
    return type == BlockType.NOT;
  }

  public void setBlockCondition(String condition) {
    this.condition = condition;
  }

  public String getBlockCondition() {
    return condition;
  }

}

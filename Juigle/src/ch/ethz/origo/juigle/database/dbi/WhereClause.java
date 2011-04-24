package ch.ethz.origo.juigle.database.dbi;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.2 (3/6/2011)
 * @since 1.0.0 (2/06/2011)
 */
public class WhereClause {

  private static final String CLAUSE_WHERE = "where";
  //
  private List<Block> listOfAndBlocks;
  private List<Block> listOfOrBlocks;

  /**
   * @version 1.0.0 (2/24/2011)
   * @since 0.1.1 (2/16/2011)
   * @param block
   */
  public void and(Block block) {
    if (listOfAndBlocks == null) {
      listOfAndBlocks = new ArrayList<Block>();
    }
    listOfAndBlocks.add(block);
  }

  /**
   *
   * @param block
   * @version 1.0.0 (2/24/2011)
   * @since 0.1.1 (2/16/2011)
   */
  public void or(Block block) {
    if (listOfOrBlocks == null) {
      listOfOrBlocks = new ArrayList<Block>();
    }
    listOfOrBlocks.add(block);
  }

  /**
   * 
   * @version 1.0.0 (2/24/2011)
   * @since 1.0.0 (2/24/2011)
   * 
   * @return
   */
  public boolean hasCondition() {
    return listOfAndBlocks != null && listOfAndBlocks.size() > 0;
  }

  /**
   * @version 0.2.1 (3/6/2011)
   * @since 0.1.1 (2/16/2011)
   * @return
   */
  public String getWhereClause() {
    if (hasCondition()) {
      int blockAndSize = listOfAndBlocks.size();
      StringBuilder sb = new StringBuilder();
      sb.append(CLAUSE_WHERE);

      for (int i = 0; i < blockAndSize; i++) {
        sb.append(" ");
        if (i > 0) {
          sb.append(Block.AND);
          sb.append(" ");
        }
        sb.append(listOfAndBlocks.get(i).getBlockCondition());
      }

      if (listOfOrBlocks != null && listOfOrBlocks.size() > 0) {
        for (int j = 0; j < listOfOrBlocks.size(); j++) {
          sb.append(" ");
          if (j > 0) {
            sb.append(Block.OR);
            sb.append(" ");
          }
          sb.append(listOfOrBlocks.get(j).getBlockCondition());
        }
      }

      return sb.toString();
    }

    return null;
  }
}

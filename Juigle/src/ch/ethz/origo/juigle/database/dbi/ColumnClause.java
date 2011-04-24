package ch.ethz.origo.juigle.database.dbi;

import java.util.ArrayList;
import java.lang.String;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (2/13/2011)
 * @since 1.0.0 (2/13/2011)
 */
public class ColumnClause<String> extends ArrayList<String> {
	
  /**
	 * 
	 */
	private static final long serialVersionUID = 3376356926978939080L;

	/**
   *
   *
   * @version 0.1.0 (2/11/2011)
   * @since 1.0.0 (2/11/2011)
   * @return
   */
  public java.lang.String getColumnsSeparatedByComma() {
    StringBuilder sb = new StringBuilder();
    int insComma = size() - 1;
    for (int i = 0; i < size(); i++) {
      sb.append(get(i));
      if (i < insComma) {
        sb.append(", ");
      }
    }
   return sb.toString();
  }

}

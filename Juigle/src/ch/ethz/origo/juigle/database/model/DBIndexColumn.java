package ch.ethz.origo.juigle.database.model;

/**
 *
 * ASC order is DEFAULT
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.1 (1/23/2011)
 * @since 1.0.0 (1/22/2011)
 */
public class DBIndexColumn {

  private String name;
  private boolean order;

  /**
   * Default constructor. Set ORDER ASC as default value
   *
   * @version 0.1.1 (1/23/2011)
   * @since 0.1.0 (1/22/2011)
   */
  public DBIndexColumn() {
    this.order = true;
  }

  public DBIndexColumn(String name, boolean order) {
    this.name = name;
    this.order = order;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isOrder() {
    return order;
  }

  public void setOrder(boolean order) {
    this.order = order;
  }

  @Override
  public String toString() {
    return "DBIndexColumn{" + "name=" + name + " order=" + order + '}';
  }


}

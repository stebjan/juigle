package ch.ethz.origo.juigle.database.model;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (1/22/2011)
 * @since 1.0.0 (1/22/2011)
 */
public class DBComponentProperties {

  /** wrapped table, columns; E.g. Firebird 1.5 version **/
  private String wrappedCommands = ""; // default

  /**
   * Get wrapped command syntax:
   * "table1", "column1"
   *
   * @return wrapped command String
   */
  public String getWrappedCommands() {
    return wrappedCommands;
  }

  /**
   * Set Wrapped prefix and suffix
   *
   * @param wrappedCommands prefix/suffix String
   */
  public void setWrappedCommands(String wrappedCommands) {
    this.wrappedCommands = wrappedCommands;
  }
}

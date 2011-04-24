package ch.ethz.origo.juigle.database.dbi;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (2/11/2011)
 * @since 1.0.0 (2/11/2011)
 */
public class DbiMethodPair {

  private String setMethod;
  private String getMethod;

  public DbiMethodPair() {
  
  }

  public DbiMethodPair(String setMethod, String getMethod) {
    this.setMethod = setMethod;
    this.getMethod = getMethod;
  }

  public String getGetMethod() {
    return getMethod;
  }

  public void setGetMethod(String getMethod) {
    this.getMethod = getMethod;
  }

  public String getSetMethod() {
    return setMethod;
  }

  public void setSetMethod(String setMethod) {
    this.setMethod = setMethod;
  }

}
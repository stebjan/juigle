package ch.ethz.origo.juigle.database.model;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (1/23/2011)
 * @since 1.0.0 (1/23/2011)
 */
public class FKColumnPair {

  private String childName;
  private String parentName;
  
  public FKColumnPair() {
    
  }

  public FKColumnPair(String childName, String parentName) {
    this.childName = childName;
    this.parentName = parentName;
  }

  public String getChildName() {
    return childName;
  }

  public void setChildName(String childName) {
    this.childName = childName;
  }

  public String getParentName() {
    return parentName;
  }

  public void setParentName(String parentName) {
    this.parentName = parentName;
  }
  
}

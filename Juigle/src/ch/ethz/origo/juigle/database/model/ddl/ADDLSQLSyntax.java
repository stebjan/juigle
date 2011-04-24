package ch.ethz.origo.juigle.database.model.ddl;

import ch.ethz.origo.juigle.database.model.ADBComponent;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see IDDLSQLSyntax
 * @version 0.1.0 (1/22/2011)
 * @since 1.0.0 (1/20/2011)
 */
public abstract class ADDLSQLSyntax implements IDDLSQLSyntax {

  protected ADBComponent dbComponent;

  public ADDLSQLSyntax(ADBComponent dbComponent) {
    this.dbComponent = dbComponent;
  }

}

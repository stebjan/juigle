package ch.ethz.origo.juigle.database.model.types;

import java.sql.Date;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see ASQLDataType
 * @version 0.1.0 (1/18/2011)
 * @since 1.0.0 (1/18/2011)
 */
public class SQLDate extends ASQLDataType {

  public static final String DATE_TYPE = "DATE";

  @Override
  public String getSyntax() {
    return syntaxType;
  }

  @Override
  public String setBasicType() {
    return DATE_TYPE;
  }

  @Override
  public Class setJavaType() {
    return Date.class;
  }

  @Override
  protected String getSyntaxFIREBIRD() {
    return "TIMESTAMP";
  }

  @Override
  protected String getSyntaxMSSQL() {
    return "DATETIME";
  }

  @Override
  protected String getSyntaxMYSQL() {
    return null;
  }

  @Override
  protected String getSyntaxORACLE() {
    return "DATE";
  }

  @Override
  protected String getSyntaxPOSTGRESQL() {
    return "DATE";
  }

  @Override
  protected String getSyntaxMaxDB() {
    return "DATE";
  }

  @Override
  protected String getSyntaxHSQLDB() {
    return "DATE";
  }

}

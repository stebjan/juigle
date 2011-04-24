package ch.ethz.origo.juigle.database.model.types;

import java.sql.Timestamp;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see ASQLDataType
 * @version 0.1.0 (1/18/2011)
 * @since 1.0.0 (1/18/2011)
 */
public class SQLTimestamp extends ASQLDataType {

  public static final String TIMESTAMP_TYPE = "TIMESTAMP";

  @Override
  public String getSyntax() {
    return syntaxType;
  }

  @Override
  public String setBasicType() {
    return TIMESTAMP_TYPE;
  }

  @Override
  public Class setJavaType() {
    return Timestamp.class;
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
    return null;
  }

  @Override
  protected String getSyntaxMaxDB() {
    return "TIMESTAMP";
  }

  @Override
  protected String getSyntaxHSQLDB() {
    return "TIMESTAMP";
  }

}

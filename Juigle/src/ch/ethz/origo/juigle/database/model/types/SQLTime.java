package ch.ethz.origo.juigle.database.model.types;

import java.sql.Time;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see ASQLDataType
 * @version 0.1.0 (1/22/2011)
 * @since 1.0.0 (1/22/2011)
 */
public class SQLTime extends ASQLDataType {

  public static final String TIME_TYPE = "TIME";

  @Override
public String getSyntax() {
    return syntaxType;
  }

  @Override
  public String setBasicType() {
    return TIME_TYPE;
  }

  @Override
  public Class setJavaType() {
    return Time.class;
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
    return "TIME";
    //return "DATE";  // For ORA MODE
  }

  @Override
  protected String getSyntaxHSQLDB() {
    return "TIME";
  }

}

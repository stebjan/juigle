package ch.ethz.origo.juigle.database.model.types;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see ASQLDataType
 * @version 0.1.0 (1/18/2011)
 * @since 1.0.0 (1/18/2011)
 */
public class SQLLong extends ASQLDataType {

  public static final String LONG_TYPE = "BIGINT";

  public SQLLong() {}

  @Override
  public String getSyntax() {
    return syntaxType;
  }

  @Override
  public String setBasicType() {
    return LONG_TYPE;
  }

  @Override
  public Class setJavaType() {
    return long.class;
  }

  @Override
  protected String getSyntaxFIREBIRD() {
    return "DOUBLE PRECISION";
  }

  @Override
  protected String getSyntaxMSSQL() {
    return "DECIMAL";
  }

  @Override
  protected String getSyntaxMYSQL() {
    return null;
  }

  @Override
  protected String getSyntaxORACLE() {
    return "NUMBER";
  }

  @Override
  protected String getSyntaxPOSTGRESQL() {
    return "BIGINT";
  }

  @Override
  protected String getSyntaxMaxDB() {
    return "FIXED(38)";
  }

  @Override
  protected String getSyntaxHSQLDB() {
    return "BIGINT";
  }

}

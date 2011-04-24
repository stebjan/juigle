package ch.ethz.origo.juigle.database.model.types;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see ASQLDataType
 * @version 0.1.0 (1/18/2011)
 * @since 1.0.0 (1/18/2011)
 */
public class SQLSmallint extends ASQLDataType {

  public static final String SMALLINT_TYPE = "SMALLINT";

  @Override
  public String getSyntax() {
    return syntaxType;
  }

  @Override
  public String setBasicType() {
    return SMALLINT_TYPE;
  }

  @Override
  public Class setJavaType() {
    return short.class;
  }

  @Override
  protected String getSyntaxFIREBIRD() {
    return "SMALLINT";
  }

  @Override
  protected String getSyntaxMSSQL() {
    return "SMALLINT";
  }

  @Override
  protected String getSyntaxMYSQL() {
    return null;
  }

  @Override
  protected String getSyntaxORACLE() {
    return "SMALLINT";
  }

  @Override
  protected String getSyntaxPOSTGRESQL() {
    return "SMALLINT";
  }

  @Override
  protected String getSyntaxMaxDB() {
    return "SMALLINT";
  }

  @Override
  protected String getSyntaxHSQLDB() {
    return "SMALLINT";
  }

}

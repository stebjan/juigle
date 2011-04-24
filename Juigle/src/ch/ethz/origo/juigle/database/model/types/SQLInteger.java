package ch.ethz.origo.juigle.database.model.types;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see ASQLDataType
 * @version 0.1.0 (1/18/2011)
 * @since 1.0.0 (1/18/2011)
 */
public class SQLInteger extends ASQLDataType {

  public static final String INTEGER_TYPE = "INTEGER";

  public SQLInteger() {}

  @Override
  public String getSyntax() {
    return syntaxType;
  }

  @Override
  public String setBasicType() {
    return INTEGER_TYPE;
  }

  @Override
  public Class setJavaType() {
    return int.class;
  }

  @Override
  protected String getSyntaxFIREBIRD() {
    return "INTEGER";
  }

  @Override
  protected String getSyntaxMSSQL() {
    return "INT";
  }

  @Override
  protected String getSyntaxMYSQL() {
    return null;
  }

  @Override
  protected String getSyntaxORACLE() {
    return "INTEGER";
  }

  @Override
  protected String getSyntaxPOSTGRESQL() {
    return "INTEGER";
  }

  @Override
  protected String getSyntaxMaxDB() {
    return "INTEGER";
  }

  @Override
  protected String getSyntaxHSQLDB() {
    return "INTEGER";
  }


}

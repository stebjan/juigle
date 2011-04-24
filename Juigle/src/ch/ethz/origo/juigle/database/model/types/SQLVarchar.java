package ch.ethz.origo.juigle.database.model.types;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see ASQLDataType
 * @version 0.1.0 (1/16/2011)
 * @since 1.0.0 (1/16/2011)
 */
public class SQLVarchar extends ASQLDataType {

  public static final String VARCHAR_TYPE = "VARCHAR";

  public SQLVarchar(int size) {
    this.size = size;
  }

  @Override
  public String getSyntax() {
    return syntaxType + "(" + size + ")";
  }

  @Override
  public String setBasicType() {
    return VARCHAR_TYPE;
  }

  @Override
  public Class<?> setJavaType() {
    return String.class;
  }

  @Override
  protected String getSyntaxMSSQL() {
    return VARCHAR_TYPE;
  }

  @Override
  protected String getSyntaxORACLE() {
    return VARCHAR_TYPE;
  }

  @Override
  protected String getSyntaxFIREBIRD() {
    return VARCHAR_TYPE;
  }

  @Override
  protected String getSyntaxMYSQL() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  protected String getSyntaxPOSTGRESQL() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  protected String getSyntaxMaxDB() {
    return VARCHAR_TYPE;
  }

  @Override
  protected String getSyntaxHSQLDB() {
    return VARCHAR_TYPE;
  }
}

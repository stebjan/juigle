package ch.ethz.origo.juigle.database.model.types;

/**
 *
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @see ASQLDataType
 * @version 0.1.0 (1/22/2011)
 * @since 1.0.0 (1/22/2011)
 */
public class SQLBoolean extends ASQLDataType {

  public static final String BOOLEAN_TYPE = "BOOLEAN";

  @Override
   public String setBasicType() {
    return BOOLEAN_TYPE;
  }

  @Override
  public Class setJavaType() {
    return boolean.class;
  }

  @Override
  public String getSyntax() {
    return syntaxType;
  }

  @Override
  protected String getSyntaxFIREBIRD() {
    return "CHAR(1)";
  }

  @Override
  protected String getSyntaxMSSQL() {
    return "BIT";
  }

  @Override
  protected String getSyntaxMYSQL() {
    return null;
  }

  @Override
  protected String getSyntaxORACLE() {
    return "VARCHAR2(10)";
  }

  @Override
  protected String getSyntaxPOSTGRESQL() {
    return "BOOLEAN";
  }

  @Override
  protected String getSyntaxMaxDB() {
    return "BOOLEAN";
    //return "VARCHAR2(10)"; // For ORA MODE
  }

  @Override
  protected String getSyntaxHSQLDB() {
    return "BOOLEAN";
  }

}

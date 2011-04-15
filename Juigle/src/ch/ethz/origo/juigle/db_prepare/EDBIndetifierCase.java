package ch.ethz.origo.juigle.db_prepare;

/**
 *
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0.00 (9/24/2010)
 * @since 2.0.0 (9/24/2010)
 *
 */
public enum EDBIndetifierCase {

  /**
   * The database stores unquoted identifiers as lower case
   */
  LOWER_CASE,

  /**
   * The database stores unquoted identifiers as upper case
   */
  UPPER_CASE,

  /**
   * The database stores unquoted identifiers as case sensitive
   */
  MIXED_CASE

}

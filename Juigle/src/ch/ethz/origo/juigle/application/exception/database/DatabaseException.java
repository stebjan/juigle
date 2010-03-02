package ch.ethz.origo.juigle.application.exception.database;

/**
 * Global database Exception
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (2/28/2010)
 * @since 0.1.0 (2/28/2010)
 * @see Exception
 * 
 */
public class DatabaseException extends Exception {

	/** Only for serialization */
	private static final long serialVersionUID = -2807693313188443328L;
	
	/**
   * Constructs a new Database Exception with the specified cause and a detail
   * message of <tt>(cause==null ? null : cause.toString())</tt> (which
   * typically contains the class and detail message of <tt>cause</tt>).
   * This constructor is useful for exceptions that are little more than
   * wrappers for other throwables (for example, {@link
   * java.security.PrivilegedActionException}).
   *
   * @param  cause the cause (which is saved for later retrieval by the
   *         {@link #getCause()} method).  (A <tt>null</tt> value is
   *         permitted, and indicates that the cause is nonexistent or
   *         unknown.)
   */
  public DatabaseException(Throwable cause) {
      super(cause);
  }
  
  public DatabaseException(String message) {
  	super(message);
  }
	/**
	 * Constructs a new Database Exception with the specified detail message and cause.
	 * <p>
	 * Note that the detail message associated with <code>cause</code> is <i>not</i>
	 * automatically incorporated in this exception's detail message.
	 * 
	 * @param message
	 *          the detail message (which is saved for later retrieval by the
	 *          {@link #getMessage()} method).
	 * @param cause
	 *          the cause (which is saved for later retrieval by the
	 *          {@link #getCause()} method). (A <tt>null</tt> value is
	 *          permitted, and indicates that the cause is nonexistent or
	 *          unknown.)
	 */
	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}

}

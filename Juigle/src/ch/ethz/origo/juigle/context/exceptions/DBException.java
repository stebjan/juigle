package ch.ethz.origo.juigle.context.exceptions;

/**
 * Exception which represents problem with the global database problem.
 * 
 * @author vsouhrada (v.souhrada at gmail.com)
 * @version 0.1.0.00 (9/24/2010)
 * @since 2.0.0 (9/24/2010)
 * @see Exception
 */
public class DBException extends Exception {

	/** Only for serialization */
	private static final long serialVersionUID = -9154048814036673276L;

	/**
	 * Constructs a new DBException with the specified cause and a detail message
	 * of <tt>(cause==null ? null : cause.toString())</tt> (which typically
	 * contains the class and detail message of <tt>cause</tt>). This constructor
	 * is useful for exceptions that are little more than wrappers for other
	 * throwables (for example, {@link java.security.PrivilegedActionException}).
	 * 
	 * @param cause
	 *          the cause (which is saved for later retrieval by the
	 *          {@link #getCause()} method). (A <tt>null</tt> value is permitted,
	 *          and indicates that the cause is nonexistent or unknown.)
	 */
	public DBException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new exception with the specified detail message. The cause is
	 * not initialized, and may subsequently be initialized by a call to
	 * {@link #initCause}.
	 * 
	 * @param message
	 *          the detail message. The detail message is saved for later
	 *          retrieval by the {@link #getMessage()} method.
	 */
	public DBException(String message) {
		super(message);
	}

	/**
	 * Constructs a new DBException exception with the specified detail message
	 * and cause.
	 * <p>
	 * Note that the detail message associated with <code>cause</code> is
	 * <i>not</i> automatically incorporated in this exception's detail message.
	 * 
	 * @param message
	 *          the detail message (which is saved for later retrieval by the
	 *          {@link #getMessage()} method).
	 * @param cause
	 *          the cause (which is saved for later retrieval by the
	 *          {@link #getCause()} method). (A <tt>null</tt> value is permitted,
	 *          and indicates that the cause is nonexistent or unknown.)
	 */
	public DBException(String message, Throwable cause) {
		super(message, cause);
	}

}

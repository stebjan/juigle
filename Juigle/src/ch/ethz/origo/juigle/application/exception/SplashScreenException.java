package ch.ethz.origo.juigle.application.exception;

/**
 * Exception which represents problem with splash screen.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail dot com)
 * @version 0.1.0 (8/29/2010)
 * @since 2.0.0 (8/29/2010)
 * @see Exception
 */
public class SplashScreenException extends Exception {

	/** Only for serialization */
  private static final long serialVersionUID = -6864652459492026121L;
  
  /**
	 * Constructs a new SplashScreen exception with the specified cause and a
	 * detail message of <tt>(cause==null ? null : cause.toString())</tt> (which
	 * typically contains the class and detail message of <tt>cause</tt>). This
	 * constructor is useful for exceptions that are little more than wrappers for
	 * other throwables (for example,
	 * {@link java.security.PrivilegedActionException}).
	 * 
	 * @param cause
	 *          the cause (which is saved for later retrieval by the
	 *          {@link #getCause()} method). (A <tt>null</tt> value is permitted,
	 *          and indicates that the cause is nonexistent or unknown.)
	 */
	public SplashScreenException(Throwable cause) {
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
	public SplashScreenException(String message) {
		super(message);
	}

	/**
	 * Constructs a new SplashScreenException exception with the specified detail
	 * message and cause.
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
	public SplashScreenException(String message, Throwable cause) {
		super(message, cause);
	}

}

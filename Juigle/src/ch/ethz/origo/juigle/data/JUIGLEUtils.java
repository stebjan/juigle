package ch.ethz.origo.juigle.data;

/**
 * 
 * Class contains utilities which are used by application. Next contains static
 * methods provided some utilities for application and also contains main
 * constants.
 * 
 * @author Vaclav Souhrada (v dot souhrada at gmail dot com)
 * @version 0.1.2 (4/25/2011)
 * @since 1.0.1 (9/05/2010)
 * 
 */
public class JUIGLEUtils {

	/** Path to application used images */
	public static final String IMAGE_PATH = "ch/ethz/origo/juigle/data/images/";
	
	/** PLUGIN key for the perspective */
	public static final String PLUGIN_PERSPECTIVES_KEY = "Perspective";

	/**
	 * Return text from error stack trace - same as is print by call e.g.
	 * <code>e.printStackTrace()</code>
	 * 
	 * @param errorException
	 *          exception - instance of <code>Throwable</code>
	 * @return text from error stack trace - same as is print by call e.g.
	 *         <code>e.printStackTrace()</code>
	 * @version 0.1.0.00 (10/03/2010)
	 * @since 0.1.1.00 (10/03/2010)
	 */
	public static String getErrorStackTraceAsText(Throwable errorException) {
		StackTraceElement[] ste = errorException.getStackTrace();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ste.length; i++) {
			sb.append("\n" + ste[i]);
		}
		return sb.toString();
	}

}

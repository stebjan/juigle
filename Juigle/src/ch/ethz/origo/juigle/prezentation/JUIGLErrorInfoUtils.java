package ch.ethz.origo.juigle.prezentation;

import java.util.logging.Level;

import org.jdesktop.swingx.JXErrorPane;
import org.jdesktop.swingx.error.ErrorInfo;
import org.jdesktop.swingx.error.ErrorReporter;

/**
 * Creates a new ErrorInfo based on the provided data.
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (1/29/2010)
 * @since 0.1.0 (1/29/2010)
 * 
 */
public class JUIGLErrorInfoUtils {

	/**
	 * Constructs and shows the error dialog
	 * 
	 * @param title
	 *          used as a quick reference for the error (for example, it might be
	 *          used as the title of an error dialog or as the subject of an email
	 *          message). May be null.
	 * @param basicInfo
	 *          short description of the problem. May be null.
	 * @param e
	 *          <code>Throwable</code> that can be used as a source for additional
	 *          information such as call stack, thread name, etc. May be null.
	 * 
	 * @version 0.1.0 (1/29/2010)
	 * @since 0.1.0 (1/29/2010)
	 * 
	 */
	public static void showErrorDialog(String title, String basicInfo, Throwable e) {
		JXErrorPane.showDialog(null, new ErrorInfo(title, basicInfo, null, null, e,
				null, null));
	}

	/**
	 * Constructs and shows the error dialog
	 * 
	 * @param title
	 * @param basicInfo
	 * @param e
	 * @param level
	 * 
	 * @version 0.1.0 (1/29/2010)
	 * @since 0.1.0 (1/29/2010)
	 */
	public static void showErrorDialog(String title, String basicInfo,
			Throwable e, Level level) {
		JXErrorPane.showDialog(null, new ErrorInfo(title, basicInfo, null, null, e,
				level, null));
	}

	/**
	 * Constructs and shows the error dialog
	 * 
	 * @param title
	 * @param basicInfo
	 * @param e
	 * @param level
	 * @param reporter
	 * 
	 * @version 0.1.0 (1/29/2010)
	 * @since 0.1.0 (1/29/2010)
	 */
	public static void showErrorDialog(String title, String basicInfo,
			Throwable e, Level level, ErrorReporter reporter) {
		JXErrorPane ep = new JXErrorPane();
		ep
				.setErrorInfo(new ErrorInfo(title, basicInfo, null, null, e, level,
						null));
		ep.setErrorReporter(reporter);
		JXErrorPane.showDialog(null, ep);
	}

}

package ch.ethz.origo.juigle.prezentation;

import javax.swing.JDialog;

import org.jdesktop.swingx.error.ErrorInfo;
import org.jdesktop.swingx.error.ErrorReporter;

/**
 * 
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0.00 (10/17/2010)
 * @since 2.0.0 (10/17/2010)
 */
public class JUIGLEErrorDialog extends JDialog {

	/** Only for serialization */
	private static final long serialVersionUID = -4248719872744112602L;
	
	private ErrorReporter reporter;
	private ErrorInfo info;
	
	public JUIGLEErrorDialog() {
		
	}

	public void setReporter(ErrorReporter reporter) {
		this.reporter = reporter;
	}

	public void setInfo(ErrorInfo info) {
		this.info = info;
	}
	
	
	
	

}

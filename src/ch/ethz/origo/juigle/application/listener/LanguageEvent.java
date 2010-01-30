package ch.ethz.origo.juigle.application.listener;


/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 08/12/09
 * @since 0.1.0 (05/18/09)
 * 
 */
public class LanguageEvent extends EventJUIGLEObject {

	/** Only for serialization */
	private static final long serialVersionUID = -5632813300537519903L;
	
	public static final int LANGUAGE_CHANGED = 0;

	public LanguageEvent(Object source, int id, String message,
			Exception exceptnion) {
		super(source, id, message, exceptnion);
	}


}

package ch.ethz.origo.juigle.application.observers;

/**
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (10/15/2010)
 * @see JUIGLEObservable
 *
 */
public class LanguageObservable extends JUIGLEObservable {
	
	private static final int MSG_LANGUAGE_CHANGED = 1;
	
	private static LanguageObservable instance;

	/**
	 * 
	 * 
	 * @return
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public static synchronized LanguageObservable getInstance() {
		if (instance == null) {
			instance = new LanguageObservable();
		}
		return instance;
	}
	
	
}

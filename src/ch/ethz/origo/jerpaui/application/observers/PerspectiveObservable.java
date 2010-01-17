package ch.ethz.origo.juigle.application.observers;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 10/18/09
 * @since 0.1.0 (08/15/09)
 * @see JUIGLEObservable
 * @see AbstractJUIGLEObservable
 */
public class PerspectiveObservable extends JUIGLEObservable {

	public static final int MSG_SAVE = 1;
	public static final int MSG_SAVE_AS = 2;
	public static final int MSG_OPEN = 3;
	public static final int MSG_CLOSE = 4;
	public static final int MSG_IMPORT = 5;
	public static final int MSG_PERSPECTIVE_CHANGED = 6;
	public static final int MSG_LANGUAGE_CHANGED = 7;
	
	private static PerspectiveObservable instance;
	
	/**
	 * 
	 * @return
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public static synchronized PerspectiveObservable getInstance() {
		if (instance == null) {
			instance = new PerspectiveObservable();
		}
		return instance;
	}
	
}

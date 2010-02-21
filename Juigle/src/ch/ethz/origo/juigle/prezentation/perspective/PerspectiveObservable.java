package ch.ethz.origo.juigle.prezentation.perspective;

import ch.ethz.origo.juigle.application.observers.JUIGLEObservable;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.2.0 (2/21/2010)
 * @since 0.1.0 (08/15/09)
 * @see JUIGLEObservable
 */
public class PerspectiveObservable extends JUIGLEObservable {

	public static final int MSG_APP_CLOSE = 0;
	public static final int MSG_PERSPECTIVE_CHANGED = 1;
	public static final int MSG_PROJECT_CLOSED = 2;
	public static final int MSG_CURRENT_PROJECT_CHANGED = 3;
	public static final int MSG_UNDOABLE_COMMAND_INVOKED = 4; 
	
	
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

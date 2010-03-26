package notUsedYet;

import ch.ethz.origo.juigle.application.observers.IObservable;
import ch.ethz.origo.juigle.application.observers.IObserver;
import ch.ethz.origo.juigle.application.observers.JUIGLEObservable;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.2.0 10/25/09
 * @since 0.1.0 (05/18/09)
 * @see JUIGLEObservable
 * @see IObserver
 */
public class GUIController extends JUIGLEObservable  implements IObserver {

	public static final int MSG_PERSPECTIVE_CHANGED = 0;
	
	
	public static final int MSG_APPLICATION_CLOSING = 1;
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Object state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(IObservable o, Object arg) {
		// TODO Auto-generated method stub
		
	}	
	
}

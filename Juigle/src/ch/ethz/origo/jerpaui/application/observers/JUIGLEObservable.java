package ch.ethz.origo.juigle.application.observers;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 08/15/09
 * @since 0.1.0
 * @see AbstractJUIGLEObservable
 */
public class JUIGLEObservable extends AbstractJUIGLEObservable {

	public static final int MSG_LANGUAGE_CHANGED = 0;
	
	public static final int MSG_APPLICATION_CLOSING = 1;
	
	private boolean state = true;
	
	private static JUIGLEObservable instance;
	
	private List<IObserver> listOfObservers = new ArrayList<IObserver>();
	
	private JUIGLEObservable() {
	}
	
	public static synchronized JUIGLEObservable getInstance() {
		if (instance == null) {
			instance = new JUIGLEObservable();
		}
		return instance;
	}

	@Override
	protected void clearChanged() {
		// TODO ROZHODNOUT O POUZITI
	}

	@Override
	public synchronized Object getState() {
	// TODO ROZHODNOUT O POUZITI
		return null;
	}

	@Override
	protected synchronized void setState() {
		
	}
	
	@Override
	protected void notifyObserver(IObserver observer) {
		
	}

	@Override
	protected void notifyObservers() {
		for (IObserver observer : listOfObservers) {
			
		}
	}


	@Override
	public synchronized void attach(IObserver observer) {
		listOfObservers.add(observer);
	}

	@Override
	public synchronized void detach(IObserver observer) {
		listOfObservers.remove(observer);
	}
	
	@Override
	public synchronized int countObservers() {
		return listOfObservers.size();
	}
	
}

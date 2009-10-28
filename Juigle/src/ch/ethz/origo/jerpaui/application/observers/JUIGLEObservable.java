package ch.ethz.origo.juigle.application.observers;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.2.0 10/02/09
 * @since 0.1.0 (08/15/09)
 * @see AbstractJUIGLEObservable
 */
public class JUIGLEObservable extends AbstractJUIGLEObservable {
	
	public static final int DEFAULT_STATE = -1;

	public static final int MSG_LANGUAGE_CHANGED = 0;
	
	public static final int MSG_APPLICATION_CLOSING = 1;
	
	private int state;
	
	private static JUIGLEObservable instance;
	
	private List<IObserver> listOfObservers = new ArrayList<IObserver>();
	
	public JUIGLEObservable() {
		this.state = -1;
	}
	
	/**
	 * 
	 * @return
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public static synchronized JUIGLEObservable getInstance() {
		if (instance == null) {
			instance = new JUIGLEObservable();
		}
		return instance;
	}
	
	/**
	 * {@inheritDoc}
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	@Override
	protected void clearChanged() {
		this.state = JUIGLEObservable.DEFAULT_STATE;
	}

	/**
	 * {@inheritDoc}
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	@Override
	public synchronized Object getState() {
		return state;
	}

	@Override
	protected synchronized void setState(Object state) {
		if (state instanceof Integer) {
			this.state = (Integer)state;
			notifyObservers();
		}
	}
	
	@Override
	protected void notifyObserver(IObserver observer) {
		observer.update(getState());
		clearChanged();
	}

	@Override
	protected void notifyObservers() {
		for (IObserver observer : listOfObservers) {
			observer.update(getState());
		}
		clearChanged();
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

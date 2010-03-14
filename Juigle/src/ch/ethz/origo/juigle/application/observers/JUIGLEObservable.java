package ch.ethz.origo.juigle.application.observers;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.2.2 (01/30/2010)
 * @since 0.1.0 (08/15/09)
 * @see AbstractJUIGLEObservable
 */
public class JUIGLEObservable extends AbstractJUIGLEObservable {

	public static final int DEFAULT_STATE = -1;

	public static final int MSG_LANGUAGE_CHANGED = -2;

	public static final int MSG_APPLICATION_CLOSING = -3;

	protected int state;

	private List<IObserver> listOfObservers = new ArrayList<IObserver>();

	public JUIGLEObservable() {
		this.state = -1;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	@Override
	protected void clearChanged() {
		this.state = JUIGLEObservable.DEFAULT_STATE;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	@Override
	public synchronized Object getState() {
		return state;
	}

	@Override
	public synchronized void setState(int state) {
		this.state = state;
		notifyObservers();
	}

	@Override
	protected void notifyObserver(IObserver observer) {
		observer.update(getState());
		clearChanged();
	}

	@Override
	protected void notifyObservers() {
		for (IObserver observer : listOfObservers) {
			observer.update(this, getState());
		}
		clearChanged();
	}
	
	@Override
	protected void notifyObserver(Object obj) {
		for (IObserver observer : listOfObservers) {
			observer.update(this, obj);
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

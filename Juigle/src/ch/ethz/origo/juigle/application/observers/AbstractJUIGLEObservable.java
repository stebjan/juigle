package ch.ethz.origo.juigle.application.observers;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.1 10/02/09
 * @since 0.1.0 (08/16/09)
 * @see IObservable
 */
public abstract class AbstractJUIGLEObservable implements IObservable {
	
	/**
	 * Return current state of observable object(subject)
	 * 
	 * @return current state of observable
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public abstract Object getState();
	
	/**
	 * Set state of observable
	 * 
	 * @param state
	 * @version 0.1.1 (10/02/09)
	 * @since 0.1.0
	 */
	protected abstract void setState(int state);
	
	/**
	 * Indicates that this object has no longer changed, 
	 * or that it has already notified all of its observers of its most recent change.
	 * This method should be called by methods <code>notifyObservers()</code> and <code>notfyObserver(IObserver observer)</code>
	 * 
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	protected abstract void clearChanged();
	
	/**
	 * 
	 * @param observer
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	protected abstract void notifyObserver(IObserver observer);
	
	/**
	 * 
	 * 
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	protected abstract void notifyObservers();
	
}

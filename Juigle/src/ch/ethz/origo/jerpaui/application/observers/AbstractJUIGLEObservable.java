package ch.ethz.origo.juigle.application.observers;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 08/16/09
 * @since 0.1.0
 * @see IObservable
 */
public abstract class AbstractJUIGLEObservable implements IObservable {
	
	/**
	 * Return current state of observable object(subject)
	 * 
	 * @return current state of observable
	 */
	public abstract Object getState();
	
	/**
	 * Set state of observable
	 */
	protected abstract void setState();
	
	/**
	 * Indicates that this object has no longer changed, 
	 * or that it has already notified all of its observers of its most recent change.
	 * This method should be called by methods <code>notifyObservers()</code> and <code>notfyObserver(IObserver observer)</code>
	 */
	protected abstract void clearChanged();
	
	protected abstract void notifyObserver(IObserver observer);
	
	protected abstract void notifyObservers();
	
}

package ch.ethz.origo.juigle.application.observers;

/**
 *
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (08/16/09)
 * @since 0.1.0 (08/16/09)
 */
public interface IObservable {
	
	/**
	 * Attaching observer to list of registered Observers
	 * 
	 * @param observer
	 */
	public void attach(IObserver observer);
	
	/**
	 * Detach observer from list of observers
	 * 
	 * @param observer
	 */
	public void detach(IObserver observer);
	
	/**
	 * Get count of registered observers
	 * 
	 * @return count of registered observers
	 */
	public int countObservers();

}
package ch.ethz.origo.juigle.application.observers;


/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.1 (1/30/2010)
 * @since 0.1.0 (08/16/09)
 */
public interface IObserver {

	public void update();
	
	public void update(Object state);
	
	public void update(IObservable o, Object state);	
	
}

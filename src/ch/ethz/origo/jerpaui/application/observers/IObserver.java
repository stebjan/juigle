package ch.ethz.origo.juigle.application.observers;

import java.util.Observer;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 08/16/09
 * @since 0.1.0
 */
public interface IObserver extends Observer {

	public void update();
	
	public void update(Object state);
	
	public void update(Object object, int state);	
	
}

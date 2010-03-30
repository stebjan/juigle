package ch.ethz.origo.juigle.application.observers;

import java.util.ArrayList;
import java.util.List;

import ch.ethz.origo.juigle.application.ILanguage;
import ch.ethz.origo.juigle.application.exception.JUIGLELangException;

/**
 * 
 * 
 * @author Vaclav Souhrada
 * @version 0.1.2 (3/30/2010)
 * @since 0.1.0 (10/15/2010)
 * @see AbstractJUIGLEObservable
 *
 */
public class LanguageObservable extends AbstractJUIGLEObservable {
	
	private static final int DEFAULT_STATE = -1;
	
	public static final int MSG_LANGUAGE_CHANGED = 1;
	
	private static LanguageObservable instance;
	
	private int state;
	
	private List<ILanguage> listOfObservers = new ArrayList<ILanguage>();
	
	public LanguageObservable() {
		this.state = LanguageObservable.DEFAULT_STATE;
	}

	/**
	 * 
	 * 
	 * @return
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public static synchronized LanguageObservable getInstance() {
		if (instance == null) {
			instance = new LanguageObservable();
		}
		return instance;
	}

	@Override
	protected void clearChanged() {
		this.state = LanguageObservable.DEFAULT_STATE;
		
	}

	@Override
	public Object getState() {
		return state;
	}

	@Override
	protected void notifyObserver(IObserver observer) {

	}

	@Override
	protected void notifyObservers() {
		for (ILanguage observer : listOfObservers) {
			try {
				observer.updateText();
			} catch (JUIGLELangException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		clearChanged();
	}

	@Override
	public void setState(int state) {
		this.state = state;	
		notifyObservers();
	}

	@Override
	public void attach(IObserver observer) {
		
	}
	
	public void attach(ILanguage observer) {
		listOfObservers.add(observer);
	}

	@Override
	public int countObservers() {
		return listOfObservers.size();
	}

	@Override
	public void detach(IObserver observer) {
		listOfObservers.remove(observer);	
	}

	/**
	 * NOT IMPLEMENTED FOR THIS CLASS
	 */
	@Override
	protected void notifyObserver(Object obj) {
		/* NOT IMPLEMENTED */		
	}
	
	
}

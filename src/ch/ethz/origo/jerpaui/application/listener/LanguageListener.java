package ch.ethz.origo.juigle.application.listener;

import java.util.EventListener;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 08/12/09
 * @since 0.1.0 (05/18/09)
 * 
 */
public interface LanguageListener extends EventListener {
	 
  public void fireLanguageChanged(LanguageEvent e);	
	
}

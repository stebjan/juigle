package nezarazeno;

import ch.ethz.origo.juigle.application.exceptions.JUIGLELangException;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 08/11/09
 * @since 0.1.0
 */
public interface ILanguage {
	
	public void setLocalizedResource();
	
	public void setResourceBundlePath(String path);
	
	public void setResourceBundleKey(String key);
	
	public void updateText() throws JUIGLELangException;

}
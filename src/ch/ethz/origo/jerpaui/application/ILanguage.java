package ch.ethz.origo.juigle.application;

import java.util.ResourceBundle;

import ch.ethz.origo.juigle.application.exceptions.JUIGLELangException;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.1 09/15/09
 * @since 0.1.0 / (08/11/09)
 */
public interface ILanguage {
	
	/**
	 * 
	 * 
	 * @param path
	 * @version 0.1.1
	 * @since 0.1.0
	 */
	public void setLocalizedResourceBundle(String path);
	
	/**
	 * 
	 * @return
	 * @version 0.1.0
	 * @since 0.1.1
	 */
	public String getResourceBundlePath();
	
	/**
	 * Set key for resource bundle. In this method you should
	 * set up key for your <code>ResourceBundle</code>.
	 * For example:<br>
	 * key: <juigle.buttons.hide.footer>
	 * 
	 * @param key name of key as String parameter
	 * @see ResourceBundle
	 */
	public void setResourceBundleKey(String key);
	
	/**
	 * 
	 * 
	 * @throws JUIGLELangException
	 */
	public void updateText() throws JUIGLELangException;

}
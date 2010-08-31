package ch.ethz.origo.juigle.application;

import java.awt.Image;
import java.util.Locale;

import org.apache.log4j.Logger;

import ch.ethz.origo.juigle.application.exception.PropertiesException;
import ch.ethz.origo.juigle.application.exception.SplashScreenException;
import ch.ethz.origo.juigle.prezentation.splashscreen.SplashScreen;

/**
 * A new class for the application global settings
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail dot com)
 * @version 0.1.0 (8/29/2010)
 * @since 2.0.0 (8/29/2010)
 */
public class JUIGLEApplication {

	private static Logger logg = Logger.getLogger(JUIGLEApplication.class);

	private static SplashScreen splash;

	/**
	 * Set application locale.
	 * 
	 * Current supported languages are: <br>
	 * <ul>
	 * <li>English - param eng - use <code>ILanguage.ENGLISH</code></li>
	 * <li>Czech - param cze - use <code>ILanguage.CZECH</code></li>
	 * </ul>
	 * 
	 * @param applicationLocale
	 *          name of locale
	 * @version 0.1.0 (8/29/2010)
	 * @since 0.1.0 (8/29/2010)
	 */
	public static void setLocale(String applicationLocale) {
		Locale locale = null;
		if (applicationLocale.equals(ILanguage.ENGLISH)) {
			locale = new Locale("en");
		} else if (applicationLocale.equals(ILanguage.CZECH)) {
			locale = new Locale("cs", "CZ");
		}
		Locale.setDefault(locale);
	}

	/**
	 * Set application locale
	 * 
	 * @param locale
	 *          locale which will be set up as default application locale
	 * @version 0.1.0 (8/29/2010)
	 * @since 0.1.0 (8/29/2010)
	 */
	public static void setLocale(Locale locale) {
		if (locale != null) {
			Locale.setDefault(locale);
		} else {
			logg.warn("Locale is null. will be set up default locale...");
		}
	}

	/**
	 * Set application locale by JUIGLE language properties loader. This method
	 * read locale parameter from the <strong>lang.properties</strong> file.
	 * 
	 * Current supported languages are: <br>
	 * <ul>
	 * <li>English</li>
	 * <li>Czech</li>
	 * </ul>
	 * 
	 * @throws PropertiesException
	 * 
	 * @version 0.1.0 (8/29/2010)
	 * @since 0.1.0 (8/29/2010)
	 * @see LanguagePropertiesLoader#getApplicationLocale()
	 */
	public static void setLocale() throws PropertiesException {
		if (!LanguagePropertiesLoader.isLoad()) {
			JUIGLEApplication.loadLanguageProps();
		}
		setLocale(LanguagePropertiesLoader.getApplicationLocale());
	}

	/**
	 * Method load language properties from
	 * 
	 * @version 0.1.0 (8/29/2010)
	 * @since 0.1.0 (8/29/2010)
	 * @throws PropertiesException
	 */
	public static void loadLanguageProps() throws PropertiesException {
		LanguagePropertiesLoader.loadProperties();
	}

	/**
	 * Method initialize and show splash screen.
	 * 
	 * @param path
	 *          string path of splash screen image
	 * 
	 * @version 0.1.0 (8/29/2010)
	 * @since 0.1.0 (8/29/2010)
	 */
	public static void startSplashScreen(String path)
	    throws SplashScreenException {
		JUIGLEApplication.splash = SplashScreen.getInstance(path);
		JUIGLEApplication.splash.show();
	}

	/**
	 * Method initialize and show splash screen.
	 * 
	 * @param image
	 *          instance of Image class
	 * 
	 * @version 0.1.0 (8/29/2010)
	 * @since 0.1.0 (8/29/2010)
	 */
	public static void startSplashScreen(Image image) {
		JUIGLEApplication.splash = SplashScreen.getInstance(image);
		JUIGLEApplication.splash.show();
	}

	/**
	 * Method close and destroy splash screen
	 * @version 0.1.0 (8/29/2010)
	 * @since 0.1.0 (8/29/2010)
	 */
	public static void stopSplashScreen() {
		if (JUIGLEApplication.splash != null) {
			JUIGLEApplication.splash.close();
		}
	}

}
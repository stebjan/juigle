package ch.ethz.origo.juigle.application;

import java.awt.Image;
import java.util.Locale;

import org.apache.log4j.Logger;

import ch.ethz.origo.juigle.application.exception.JUIGLELangException;
import ch.ethz.origo.juigle.application.exception.PerspectiveException;
import ch.ethz.origo.juigle.application.exception.PropertiesException;
import ch.ethz.origo.juigle.application.exception.SplashScreenException;
import ch.ethz.origo.juigle.application.observers.IObservable;
import ch.ethz.origo.juigle.application.observers.IObserver;
import ch.ethz.origo.juigle.application.observers.JUIGLEObservable;
import ch.ethz.origo.juigle.plugin.PluginEngine;
import ch.ethz.origo.juigle.plugin.exception.PluginEngineException;
import ch.ethz.origo.juigle.prezentation.IMainFrame;
import ch.ethz.origo.juigle.prezentation.JUIGLEFrame;
import ch.ethz.origo.juigle.prezentation.splashscreen.SplashScreen;

/**
 * A new class for the application global settings
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail dot com)
 * @version 0.2.1.00 (9/12/2010)
 * @since 2.0.0 (8/29/2010)
 */
public class JUIGLEApplication implements IObserver, ILanguage {

	private static Logger logg = Logger.getLogger(JUIGLEApplication.class);

	private static JUIGLEApplication app;
	private IMainFrame frame;
	private SplashScreen splash;
	private PluginEngine pluginEng;
	private JUIGLEFrame jgFrame;

	private static String appVersion;

	/**
	 * Default constructor
	 * 
	 * @version 0.1.0 (9/04/2010)
	 * @since 0.2.0 (9/04/2010)
	 */
	private JUIGLEApplication() {
		JUIGLEObservable.getInstance().attach(this);
	}

	/**
	 * Return instance on JUIGLEApplication class. This is based on pattern called
	 * Singleton.
	 * 
	 * @return instance on JUIGLEApplication class
	 * @version 0.1.0 (9/04/2010)
	 * @since 0.2.0 (9/04/2010)
	 */
	public static JUIGLEApplication getInstance() {
		if (app == null) {
			app = new JUIGLEApplication();
		}
		return app;
	}

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
			loadLanguageProps();
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
	public void startSplashScreen(String path) throws SplashScreenException {
		splash = SplashScreen.getInstance(path);
		splash.show();
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
	public void startSplashScreen(Image image) {
		splash = SplashScreen.getInstance(image);
		splash.show();
	}

	/**
	 * Method close and destroy splash screen
	 * 
	 * @version 0.1.0 (8/29/2010)
	 * @since 0.1.0 (8/29/2010)
	 */
	public void stopSplashScreen() {
		if (splash != null) {
			splash.close();
		}
	}

	/**
	 * 
	 * @param frame
	 * @version 0.1.0 (9/04/2010)
	 * @since 0.2.0 (9/04/2010)
	 */
	public void setMainFrame(IMainFrame frame) {
		this.frame = frame;
	}

	/**
	 * 
	 * @param major
	 * @param minor
	 * @param revision
	 * @throws PluginEngineException
	 * @version 0.1.0 (9/04/2010)
	 * @since 0.2.0 (9/04/2010)
	 */
	public void initPluginEngine(int major, int minor, int revision)
	    throws PluginEngineException {
		pluginEng = PluginEngine.getInstance();
		pluginEng.setCurrentVersion(major, minor, revision);
	}

	/**
	 * 
	 * @version 0.1.0 (9/04/2010)
	 * @since 0.2.0 (9/04/2010)
	 */
	public void loadPlugins() {
		// pluginEng.loadPluginsFromDirectory();
	}

	/**
	 * 
	 * @version 0.1.0 (9/04/2010)
	 * @throws PerspectiveException
	 * @since 0.2.0 (9/04/2010)
	 */
	public void startApplication(boolean fullScreen) throws PerspectiveException {
		if (jgFrame == null) {
			jgFrame = new JUIGLEFrame(appVersion, ClassLoader
			    .getSystemResourceAsStream(frame.getLogoPath()));
			jgFrame.setMainMenu(frame.getMainMenu());
			// jgFrame.setPerspectives(perspectiveLoader, perspResourceBundleKey)
			jgFrame.setFullScreen(fullScreen);
			jgFrame.setVisible(true);

		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Object state) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(IObservable o, Object state) {
		if ((o instanceof JUIGLEObservable) && (state instanceof Integer)) {
			int msg = (Integer) state;

			switch (msg) {
			case JUIGLEObservable.MSG_APPLICATION_CLOSING:
				frame.applicationClose();
				frame = null;
				jgFrame = null;
				pluginEng = null;
				break;

			default:
				break;
			}
		}
	}

	@Override
	public String getResourceBundlePath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocalizedResourceBundle(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setResourceBundleKey(String key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateText() throws JUIGLELangException {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param appVersion
	 * @version 0.1.0 (9/04/2010)
	 * @since 2.0.0 (9/04/2010)
	 */
	public void setVersion(String appVersion) {
		JUIGLEApplication.appVersion = appVersion;
	}

	/**
	 * 
	 * @return
	 * @version 0.1.0 (9/12/2010)
	 * @since 2.0.0 (9/12/2010)
	 */
	public static String getVersion() {
		return appVersion;
	}

}
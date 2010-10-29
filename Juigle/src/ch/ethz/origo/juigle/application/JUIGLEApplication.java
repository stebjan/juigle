package ch.ethz.origo.juigle.application;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.logging.Level;

import org.apache.log4j.Logger;

import ch.ethz.origo.juigle.application.exception.JUIGLELangException;
import ch.ethz.origo.juigle.application.exception.PerspectiveException;
import ch.ethz.origo.juigle.application.exception.PropertiesException;
import ch.ethz.origo.juigle.application.exception.SplashScreenException;
import ch.ethz.origo.juigle.application.observers.IObservable;
import ch.ethz.origo.juigle.application.observers.IObserver;
import ch.ethz.origo.juigle.application.observers.JUIGLEObservable;
import ch.ethz.origo.juigle.context.LanguageUtils;
import ch.ethz.origo.juigle.context.exceptions.ApplicationException;
import ch.ethz.origo.juigle.data.EmailErrorReporter;
import ch.ethz.origo.juigle.data.ErrorCodes;
import ch.ethz.origo.juigle.plugin.PluginEngine;
import ch.ethz.origo.juigle.plugin.exception.PluginEngineException;
import ch.ethz.origo.juigle.prezentation.IMainFrame;
import ch.ethz.origo.juigle.prezentation.JUIGLEFrame;
import ch.ethz.origo.juigle.prezentation.JUIGLErrorInfoUtils;
import ch.ethz.origo.juigle.prezentation.menu.JUIGLEMainMenu;
import ch.ethz.origo.juigle.prezentation.splashscreen.SplashScreen;

/**
 * A class for the application global settings and starting.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail dot com)
 * @version 0.2.2.02 (10/20/2010)
 * @since 2.0.0 (8/29/2010)
 * @see IObserver
 * @see ILanguage
 */
public class JUIGLEApplication implements IObserver, ILanguage {

	/** Logger for this class */
	private static Logger logger = Logger.getLogger(JUIGLEApplication.class);

	private static JUIGLEApplication app;
	private IMainFrame frame;
	private SplashScreen splash;
	private PluginEngine pluginEngine;
	private JUIGLEFrame juigleFrame;

	private static String appVersion;

	/**
	 * Default constructor
	 * 
	 * @throws PropertiesException
	 * @version 0.1.0 (9/04/2010)
	 * @since 0.2.0 (9/04/2010)
	 */
	private JUIGLEApplication() throws PropertiesException {
		JUIGLEObservable.getInstance().attach(this);
		LanguageUtils.loadLanguageProps();
		LanguageUtils.setLocale();
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
			try {
				app = new JUIGLEApplication();
			} catch (PropertiesException e) {
				// parsing error message
				String errorMSG = JUIGLEErrorParser.getJUIGLEErrorMessage(e
						.getMessage());
				// display error GUI
				JUIGLErrorInfoUtils.showErrorDialog("Error dialog", errorMSG, e,
						Level.WARNING, new EmailErrorReporter());
				logger.warn(errorMSG, e);
			}
		}
		return app;
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
		splash = SplashScreen.getInstance((BufferedImage) image);
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
	 * @version 0.1.1.00 (10/20/2010)
	 * @since 0.2.0 (9/04/2010)
	 */
	public void initPluginEngine(int major, int minor, int revision)
			throws ApplicationException {
		pluginEngine = PluginEngine.getInstance();
		try {
			pluginEngine.setCurrentVersion(major, minor, revision);
		} catch (PluginEngineException e) {
			logger.error(e.getMessage(), e);
			throw new ApplicationException(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @version 0.2.0.00 (10/20/2010)
	 * @since 0.2.0 (9/04/2010)
	 */
	public void loadPlugins(String filePath) throws ApplicationException {
		if (pluginEngine != null) {
			try {
				pluginEngine.init(filePath);
			} catch (PluginEngineException e) {
				logger.error(e.getMessage(), e);
				throw new ApplicationException(e.getMessage(), e);
			}
		} else {
			logger.warn(JUIGLEErrorParser.getJUIGLEErrorMessage(ErrorCodes.PLUGIN_INSTANCE_IS_NULL));
			throw new ApplicationException(new PluginEngineException(ErrorCodes.PLUGIN_INSTANCE_IS_NULL));
		}
	}

	/**
	 * 
	 * 
	 * @version 0.2.0.01 (10/03/2010)
	 * @throws PerspectiveException
	 * @since 0.2.0 (9/04/2010)
	 */
	public void startApplication(boolean fullScreen, String perspectivePath)
			throws PerspectiveException {
		if (juigleFrame == null) {
			logger.info("Application starting...");
			PerspectiveLoader loader = PerspectiveLoader.getInstance();
			loader.loadPerspectives(perspectivePath);
			juigleFrame = new JUIGLEFrame(appVersion,
					ClassLoader.getSystemResourceAsStream(frame.getLogoPath()));
			juigleFrame.setMainMenu(frame.getMainMenu());
			juigleFrame.setPerspectives(loader,
					JUIGLEMainMenu.KEY_PERSPECTIVE_MAIN_MENU);
			juigleFrame.setFullScreen(fullScreen);
			juigleFrame.setVisible(true);
			logger.info("Application started successfully...");
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
				logger.info("Application closing...");
				frame.applicationClose();
				frame = null;
				juigleFrame.dispose();
				juigleFrame = null;
				pluginEngine = null;
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
	 * @version 0.1.0.00 (9/04/2010)
	 * @since 0.2.1.00 (9/04/2010)
	 */
	public void setVersion(String appVersion) {
		JUIGLEApplication.appVersion = appVersion;
	}

	/**
	 * 
	 * @return
	 * @version 0.1.0.00 (9/12/2010)
	 * @since 0.2.1.00 (9/12/2010)
	 */
	public static String getVersion() {
		return appVersion;
	}

}
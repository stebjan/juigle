package ch.ethz.origo.juigle.context;

import java.util.Locale;

import org.apache.log4j.Logger;

import ch.ethz.origo.juigle.application.ILanguage;
import ch.ethz.origo.juigle.application.LanguagePropertiesLoader;
import ch.ethz.origo.juigle.application.exception.PropertiesException;

/**
 * 
 * @author vsouhrada
 * 
 * @version 1.0.1 (5/07/2011)
 * @since 0.1.0 (10/03/2010)
 */
public class LanguageUtils {

	/** Logger for this class */
	private static Logger logger = Logger.getLogger(LanguageUtils.class);

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
	 * @version 1.0.1 (5/07/2011)
	 * @since 0.1.0 (10/03/2010)
	 */
	public static void setLocale(String applicationLocale) {
		Locale locale = null;
		if (applicationLocale.equals(ILanguage.ENGLISH)) {
			locale = new Locale("en");
		} else if (applicationLocale.equals(ILanguage.CZECH)) {
			locale = new Locale("cs", "CZ");
		}
		if (locale == null) {
			locale = new Locale("en");
			logger.warn("Can not set a locale[" + applicationLocale
					+ "]. Will be used ENGLISH locale");
		}
		// set locale
		Locale.setDefault(locale);
	}

	/**
	 * Set application locale
	 * 
	 * @param locale
	 *          locale which will be set up as default application locale
	 * @since 0.1.0.00 (10/03/2010)
	 */
	public static void setLocale(Locale locale) {
		if (locale == null) {
			logger.warn("Can not set a locale[" + locale
					+ "]. Will be used ENGLISH locale");
			locale = new Locale("en");
		}
		// set locale
		Locale.setDefault(locale);
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
	 * @version 0.1.0.00 (10/03/2010)
	 * @since 0.1.0.00 (10/03/2010)
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
	 * @version 0.1.0.00 (10/03/2010)
	 * @since 0.1.0.00 (10/03/2010)
	 * @throws PropertiesException
	 */
	public static void loadLanguageProps() throws PropertiesException {
		LanguagePropertiesLoader.loadProperties();
	}

}

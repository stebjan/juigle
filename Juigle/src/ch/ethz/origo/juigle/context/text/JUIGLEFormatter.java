package ch.ethz.origo.juigle.context.text;

/**
 * 
 * @author vsouhrada
 * 
 * @version 1.0.0-alpha (6/01/2011)
 * @since 2.0.0 (6/01/2011)
 * 
 */
public class JUIGLEFormatter {

	/**
	 * This method align text and append char(s) to the left side. E.G if you have
	 * string '102' and you call this method
	 * <code>alignStringFromLeft("102", 5, '0')</code> so result will be: '00102'.
	 * 
	 * @param text
	 *          default text for append a special char
	 * @param length
	 *          of result text
	 * @param ch
	 *          a special character which will be append from left side of text
	 * @return aligned text and append char(s) to the left side. E.G if you have
	 *         string '102' and you call this method
	 *         <code>alignStringFromLeft("102", 5, '0')</code> so result will be:
	 *         '00102'.
	 */
	public static String alignStringFromLeft(String text, int length, char ch) {
		StringBuilder result = new StringBuilder();
		// count end of text
		int end = length - text.length();

		for (int i = 0; i < end; i++) {
			result.append(ch);
		}
		// append a default text
		result.append(text);

		return result.toString();
	}

	/**
	 * This method align text and append char(s) to the right side. E.G if you
	 * have string '102' and you call this method
	 * <code>alignStringFromRight("102", 5, '0')</code> so result will be:
	 * '10200'.
	 * 
	 * @param text
	 *          default text for append a special char
	 * @param length
	 *          of result text
	 * @param ch
	 *          a special character which will be append from left side of text
	 * @return aligned text and append char(s) to the right side. E.G if you have
	 *         string '102' and you call this method
	 *         <code>alignStringFromRight("102", 5, '0')</code> so result will be:
	 *         '10200'.
	 */
	public static String alignStringFromRight(String text, int length, char ch) {
		StringBuilder result = new StringBuilder();
		// append a default text
		result.append(text);
		// count end of text
		int end = length - text.length();

		for (int i = 0; i < end; i++) {
			result.append(ch);
		}

		return result.toString();
	}
	
}
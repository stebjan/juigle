package ch.ethz.origo.juigle.application;

import java.io.File;

/**
 * Class contains some useful methods for working with files
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (05/22/2010)
 * @since 1.0.1 (05/22/2010)
 *
 */
public class JUIGLEFileUtils {
	
	public static String getSameAbsolutePathAsOtherFile(File file) {
		String fileName = file.getName();
		String absolutePath = file.getAbsolutePath();
		return absolutePath.substring(0, (absolutePath.length() - fileName.length()));
	}
	

}

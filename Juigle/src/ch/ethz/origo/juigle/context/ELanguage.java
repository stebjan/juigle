package ch.ethz.origo.juigle.context;

/**
 * 
 * @author vsouhrada
 * @version 1.0.0 (5/07/2011)
 * @since 1.1.0 (5/07/2011)
 *
 */
public enum ELanguage {
	
	CZ("ch/ethz/origo/juigle/data/images/cz.gif"), 
	EN("ch/ethz/origo/juigle/data/images/en.gif");
	
	private String imagePath;
	
	private ELanguage(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public String getImagePathAsString() {
		return imagePath;
	}

}

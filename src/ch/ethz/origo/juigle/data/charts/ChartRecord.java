package ch.ethz.origo.juigle.data.charts;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/27/2010)
 * @since 0.1.0 (3/27/2010)
 * 
 */
public class ChartRecord {
	
	private String legendTitle;
	
	public ChartRecord(String legendTitle) {
		this.legendTitle = legendTitle;
	}
	
	public String getLegendTitle() {
		return legendTitle;
	}
	
	public void setLegendTitle(String legendTitle) {
		this.legendTitle = legendTitle;
	}
	
}

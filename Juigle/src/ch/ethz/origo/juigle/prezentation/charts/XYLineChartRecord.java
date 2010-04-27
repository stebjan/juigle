package ch.ethz.origo.juigle.prezentation.charts;

import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;



/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/27/2010)
 * @since 0.1.0 (3/27/2010)
 * @see ChartRecord
 * 
 */
public class XYLineChartRecord extends ChartRecord {

	private XYSeries series;
	
	public XYLineChartRecord(String legendTitle) {
		super(legendTitle);
		this.series = new XYSeries(legendTitle);
	}
	
	public XYLineChartRecord(String legendTitle, double[] data) {
		this(legendTitle);
		addToSeries(data);
	}
	
	public void addToSeries(double[] data) {
		addToSeries(0.0, 1.0, data);
	}
	
	public void addToSeries(double start, double x, double[] data) {
		double xPosition = start;
		for (int i = 0; i < data.length; i++) {
			addToSeries(xPosition, data[i]);
			xPosition += x;
		}	
	}

	public void addToSeries(XYDataItem item) {
		series.add(item);
	}
	
	public void addToSeries(double x, double y) {
		series.add(x, y);
	}
	
	public XYSeries getXYSeries() {
		return series;
	}
	
	public void setXYSeries(XYSeries series) {
		this.series = series;
	}

}
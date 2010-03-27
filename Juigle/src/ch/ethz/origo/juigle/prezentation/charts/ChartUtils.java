package ch.ethz.origo.juigle.prezentation.charts;

import org.jfree.chart.plot.PlotOrientation;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/27/2010)
 * @since 0.1.0 (3/27/2010)
 * 
 */
public class ChartUtils {

	public static PlotOrientation getJFreeChartOrientation(ChartPlotOrientation orientation) {
		if (orientation.getName().equals(ChartPlotOrientation.HORIZONTAL_PLOT)) {
			return PlotOrientation.HORIZONTAL;
		}
		return PlotOrientation.VERTICAL;
	}
	
	public static double[] getSampleSinus() {
		double[] sinus = new double[360];
		for (int i = 0; i < 360; i++) {
			sinus[i] = Math.sin(Math.PI / 180 * i);
		}
		return sinus;
	}
	
	public static double[] getSampleCosinus() {
		double[] cosinus = new double[360];
		for (int i = 0; i < 360; i++) {
			cosinus[i] = Math.cos(Math.PI / 180 * i);
		}
		return cosinus;
	}
	
}

/*
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

/*
 *  
 *    Copyright (C) 2009 - 2010 
 *    							University of West Bohemia, 
 *                  Department of Computer Science and Engineering, 
 *                  Pilsen, Czech Republic
 */
package ch.ethz.origo.juigle.data.charts;

import org.jfree.chart.plot.PlotOrientation;

import ch.ethz.origo.juigle.prezentation.charts.ChartPlotOrientation;

/**
 * Class provides some methods which returns sample signals. 
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
	
	public static double[] getSampleSinus3() {
		double[] sinus = new double[360];
		for (int i = 0; i < 360; i++) {
			sinus[i] = Math.sin(10*(Math.PI / 180 * i));
		}
		return sinus;
	}
	
	public static double[] getSample3() {
		double[] sinus = new double[360];
		for (int i = 0; i < 360; i++) {
			sinus[i] = 0;
		}
		sinus[468] = 0.6;
		sinus[472] = 0.6;
		sinus[469] = 0.8;
		sinus[471] = 0.8;
		sinus[470] = 1.0;
		
		return sinus;
	}
	
	public static double[] getSample4() {
		double[] sinus = new double[360];
		for (int i = 0; i < 360; i++) {
			sinus[i] = 0;
		}
		sinus[50] = -0.6;
		sinus[54] = -0.6;
		sinus[51] = -0.8;
		sinus[53] = -0.8;
		sinus[52] = -1.0;
		
		return sinus;
	}
	
}

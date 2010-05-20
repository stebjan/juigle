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
package ch.ethz.origo.juigle.prezentation.charts;

import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;

/**
 * Record for XYLineChart
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/27/2010)
 * @since 0.1.0 (3/27/2010)
 * @see ChartRecord
 * 
 */
public class XYLineChartRecord extends ChartRecord {

	private XYSeries series;

	/**
	 * Construct record with title of legend
	 * 
	 * @param legendTitle
	 *          legend title
	 */
	public XYLineChartRecord(String legendTitle) {
		super(legendTitle);
		this.series = new XYSeries(legendTitle);
	}

	/**
	 * 
	 * Construct record with title of legend
	 * 
	 * @param legendTitle
	 *          legend title
	 * @param data
	 *          array of data for chart
	 */
	public XYLineChartRecord(String legendTitle, double[] data) {
		this(legendTitle);
		addToSeries(data);
	}

	/**
	 * Add a new data
	 * @param data array of data for chart
	 */
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
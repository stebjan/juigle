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

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;

import ch.ethz.origo.juigle.data.charts.ChartUtils;

/**
 * Construct XY Line chart
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/27/2010)
 * @since 0.1.0 (3/27/2010)
 * 
 */
public class XYLineChart {

	/** Only for serialization */
	private static final long serialVersionUID = -3022583810505452187L;

	private List<XYLineChartRecord> listOfData;

	private String chartTitle;
	private String xAxisLabel;
	private String yAxisLabel;

	private PlotOrientation orientation;

	private boolean legend;
	private boolean tooltips;
	private boolean urls;

	private JFreeChart jc;

	/**
	 * Construct XYLine Chart
	 * 
	 * @param chartTitle
	 *          title of chart
	 * @param xAxisLabel
	 *          label of axis X
	 * @param yAxisLabel
	 *          label of axis Y
	 * @param orientation
	 *          type of chart orientation
	 * @param legend
	 *          true if legend will be showed
	 * @param tooltips
	 *          true if tooltips will be showed
	 * @param urls
	 *          configure chart to generation urls?
	 */
	public XYLineChart(String chartTitle, String xAxisLabel, String yAxisLabel,
			ChartPlotOrientation orientation, boolean legend, boolean tooltips,
			boolean urls) {
		this.chartTitle = chartTitle;
		this.xAxisLabel = xAxisLabel;
		this.yAxisLabel = yAxisLabel;
		this.orientation = ChartUtils.getJFreeChartOrientation(orientation);
		this.legend = legend;
		this.tooltips = tooltips;
		this.urls = urls;
	}

	/**
	 * Construct XYLine Chart
	 * 
	 * @param listOfData
	 *          list of data for chart
	 * @param chartTitle
	 *          title of chart
	 * @param xAxisLabel
	 *          label of axis X
	 * @param yAxisLabel
	 *          label of axis Y
	 * @param orientation
	 *          type of chart orientation
	 * @param legend
	 *          true if legend will be showed
	 * @param tooltips
	 *          true if tooltips will be showed
	 * @param urls
	 *          configure chart to generation urls?
	 */
	public XYLineChart(List<XYLineChartRecord> listOfData, String chartTitle,
			String xAxisLabel, String yAxisLabel, ChartPlotOrientation orientation,
			boolean legend, boolean tooltips, boolean urls) {
		this(chartTitle, xAxisLabel, yAxisLabel, orientation, legend, tooltips,
				urls);
		this.listOfData = listOfData;
	}

	public JPanel getXYLineChartPanel(Dimension preferredSize) {
		setJFreeChartInstance();
		ChartPanel cp = new ChartPanel(jc);
		cp.setPreferredSize(preferredSize);
		return cp;
	}

	public JFrame getXYLineChartAsFrame(String title, boolean scrollpane) {
		setJFreeChartInstance();
		return new ChartFrame(title, jc, scrollpane);
	}

	/**
	 * Set list of record for chart which will be displayed
	 * 
	 * @param listOfData
	 */
	public void setListOfChartData(List<XYLineChartRecord> listOfData) {
		this.listOfData = listOfData;
	}

	/**
	 * Add record for XYLine Chart
	 * 
	 * @param record
	 *          of the XYLine Chart
	 */
	public void addXYLineChartRecord(XYLineChartRecord record) {
		if (listOfData == null) {
			listOfData = new ArrayList<XYLineChartRecord>();
		}
		listOfData.add(record);
	}

	/**
	 * Set instance of JFREEChart library
	 */
	private void setJFreeChartInstance() {
		if (jc == null) {
			jc = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel,
					getDataXYDataset(listOfData), orientation, legend, tooltips, urls);
		}
	}

	/**
	 * Return list of datasets
	 * 
	 * @param listOfData
	 * @return list of datasets
	 */
	private XYDataset getDataXYDataset(List<XYLineChartRecord> listOfData) {
		XYSeriesCollection dataset = new XYSeriesCollection();
		for (XYLineChartRecord lcr : listOfData) {
			dataset.addSeries(lcr.getXYSeries());
		}
		return dataset;
	}

	/**
	 * Only for testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		XYLineChart chart = new XYLineChart("Result of method", "X", "Y",
				ChartPlotOrientation.VERTICAL, true, false, false);
		chart.addXYLineChartRecord(new XYLineChartRecord("noname", ChartUtils
				.getSampleSinus()));
		JFrame frame = chart.getXYLineChartAsFrame("Result FastICA", true);
		frame.setSize(800, 600);
		frame.setVisible(true);
	}

}

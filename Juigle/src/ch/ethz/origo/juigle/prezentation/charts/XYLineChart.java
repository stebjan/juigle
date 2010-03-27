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

/**
 * 
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
	
	public void setListOfChartData(List<XYLineChartRecord> listOfData) {
		this.listOfData = listOfData;
	}
	
	public void addXYLineChartRecord(XYLineChartRecord record) {
		if (listOfData == null) {
			listOfData = new ArrayList<XYLineChartRecord>();
		}
		listOfData.add(record);
	}

	private void setJFreeChartInstance() {
		if (jc == null) {
			jc = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel,
					getDataXYDataset(listOfData), orientation, legend,
					tooltips, urls);
		}
	}

	private XYDataset getDataXYDataset(List<XYLineChartRecord> listOfData) {
		XYSeriesCollection dataset = new XYSeriesCollection();
		for (XYLineChartRecord lcr : listOfData) {
			dataset.addSeries(lcr.getXYSeries());
		}
		return dataset;
	}
	
	public static void main(String[] args) {
		XYLineChart chart = new XYLineChart("Result of FastICA", "X", "Y", ChartPlotOrientation.VERTICAL, true, false, false);
		chart.addXYLineChartRecord(new XYLineChartRecord("noname", ChartUtils.getSampleSinus()));
		JFrame frame = chart.getXYLineChartAsFrame("Result FastICA", true);
		frame.setSize(800, 600);
		frame.setVisible(true);
	}

}

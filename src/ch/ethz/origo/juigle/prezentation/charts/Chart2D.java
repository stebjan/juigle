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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JComponent;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Class construct 2D chart
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/26/2010)
 * @since 0.1.0 (3/26/2010)
 * @see JComponent
 * 
 */
public class Chart2D extends JComponent {

	/** Only for serialization */
	private static final long serialVersionUID = 7146469771045384447L;

	private int top;
	private int bottom;
	private int graphLength;

	private Color axisX;
	private Color axisY;

	private double[] signal;

	public Chart2D() {
		this.axisX = Color.RED;
		this.axisY = Color.RED;
		setBackground(Color.WHITE);
	}

	public Chart2D(double[] signal) {
		this();
		this.signal = signal;
		this.setAutoscrolls(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		initParameters();

		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, (int) getWidth(), (int) getHeight());

		g2.setColor(axisY);
		g2.drawLine(top, top, top, bottom);
		g2.setColor(axisX);
		g2.drawLine(top, bottom, graphLength, bottom);

		double parts = (double) graphLength / (double) signal.length;
		System.out.println("Signal length" + signal.length);
		System.out.println("Parts -> " + parts);
		double positionX1 = 0;
		double positionX2 = positionX1 + parts;

		g2.setColor(Color.BLACK);
		for (int j = 0; j < signal.length - 1; j++) {
			// System.out.println("X1:" + positionX1 + " X2:" + positionX2);
			g2.draw(new Line2D.Double((top + positionX1),
					(bottom - (signal[j] * bottom)), (top + positionX2),
					(bottom - (signal[j + 1] * bottom))));
			positionX1 += parts;
			positionX2 += parts;
		}

	}

	private void initParameters() {
		int width = getWidth();
		int height = getHeight();

		top = 40;
		bottom = 300;// height - 40;
		// graphLength = width - 40;
		graphLength = signal.length;
	}

	public Color getAxisX() {
		return axisX;
	}

	public void setAxisX(Color axisX) {
		this.axisX = axisX;
	}

	public Color getAxisY() {
		return axisY;
	}

	public void setAxisY(Color axisY) {
		this.axisY = axisY;
	}

	public static void main(String[] args) {
		XYSeries series = new XYSeries("ICA result sinus");
		XYSeries series2 = new XYSeries("ICA result cosinu");
		//double[] signals = new double[360];
		
		
		for (int i = 0; i < 360; i++) {
			//signals[i] = Math.sin(Math.PI / 180 * i);
			//System.out.println(signals[i]);
			series.add(i, Math.sin(Math.PI / 180 * i));
			series2.add(i, Math.cos(Math.PI / 180 * i));
		}
		XYSeriesCollection collectionSeries = new XYSeriesCollection();
		collectionSeries.addSeries(series);
		collectionSeries.addSeries(series2);
		XYDataset dataset = collectionSeries;
		
		JFreeChart jc = ChartFactory.createXYLineChart("Result of FastICA", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, true);
		ChartFrame cf = new ChartFrame("XY Chart", jc, true);
		cf.setSize(800, 600);
		cf.setVisible(true);
		
		
		
/*
		JFrame f = new JFrame();
		f.setSize(350, 400);
		
		Chart2D ch = new Chart2D(signals);
		

    int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
    int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
    
    JScrollPane jsp = new JScrollPane(ch, v, h);
    jsp.setEnabled(true);
		
		f.getContentPane().add(jsp);

		WindowListener wndCloser = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		f.addWindowListener(wndCloser);
		f.setVisible(true);*/
	
	}
}
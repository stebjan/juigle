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

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/27/2010)
 * @since 0.1.0 (3/27/2010)
 * 
 */
public final class ChartPlotOrientation {
	
	public static final String HORIZONTAL_PLOT = "horizontal";
	public static final String VERTICAL_PLOT = "vertical";	
	
	public static final ChartPlotOrientation HORIZONTAL = new ChartPlotOrientation(ChartPlotOrientation.HORIZONTAL_PLOT);
	public static final ChartPlotOrientation VERTICAL = new ChartPlotOrientation(ChartPlotOrientation.VERTICAL_PLOT);
	
	 /** The name. */
  private String name;

  /**
   * Private constructor.
   *
   * @param name  the name.
   */
  private ChartPlotOrientation(String name) {
      this.name = name;
  }
  
  public String getName() {
  	return name;
  }

}

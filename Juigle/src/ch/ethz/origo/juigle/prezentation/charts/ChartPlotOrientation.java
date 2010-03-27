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

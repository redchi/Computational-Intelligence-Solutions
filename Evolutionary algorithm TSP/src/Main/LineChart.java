package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.colors.ChartColor;

public class LineChart implements ExampleChart<XYChart> {

	private Route route;
	
	public LineChart(Route route) {
		this.route = route;
	}
	
	 @Override
	  public XYChart getChart() {

	    // generates Log data
	    List<Float> xData = new ArrayList<Float>();
	    List<Float> yData = new ArrayList<Float>();
	    Location start = route.getPath().get(0);
	    for(Location loc:route.getPath()) {
	    	float x = loc.getX();
	    	float y = loc.getY();
	    	xData.add(x);
	    	yData.add(y);
	    	
	    }

	    // Create Chart
	    XYChart chart =
	        new XYChartBuilder()
	            .width(800)
	            .height(600)
	            .title("Evolutionary Algorithm on TSP by Asim")
	            .xAxisTitle("x axis")
	            .yAxisTitle("y axis")
	            .build();

	    // Customize Chart
	    chart.getStyler().setChartTitleVisible(true);
	    chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
	   // chart.getStyler().setYAxisLogarithmic(true);
	  //  chart.getStyler().setXAxisLabelRotation(45);

	    // chart.getStyler().setXAxisLabelAlignment(TextAlignment.Right);
	    // chart.getStyler().setXAxisLabelRotation(90);
	    // chart.getStyler().setXAxisLabelRotation(0);
	    
	    
	    chart.getStyler().setChartBackgroundColor(Color.black);
	    
	    chart.getStyler().setPlotBackgroundColor(ChartColor.getAWTColor(ChartColor.BLACK));
	    chart.getStyler().setPlotGridLinesColor(Color.black);
	    chart.getStyler().setChartFontColor(Color.white);
	    chart.getStyler().setLegendBackgroundColor(Color.black);
	    chart.getStyler().setYAxisTickLabelsColor(Color.white);
	    chart.getStyler().setXAxisTickLabelsColor(Color.white);
	    // Series
	    XYSeries series =   chart.addSeries("Route", xData, yData);
	    XYSeries startPoint =   chart.addSeries("start City", new double[] {start.getX()}, new double[] {start.getY()});
	    startPoint.setMarkerColor(Color.red);
	    return chart;
	  }

	@Override
	public String getExampleChartName() {
		String name = "TSP path";
		return name;
	}
	
}

package visual;

import ar.edu.itba.sia.Config;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * Created by Usuario on 28/5/2017.
 */
public class LineChart extends ApplicationFrame {
    private DefaultCategoryDataset dataset;

    public LineChart() {
        super(Config.getInstance().getProperty("app_title"));
        JFreeChart lineChart = ChartFactory.createLineChart(
                Config.getInstance().getProperty("chart_title"),
                "Fitness","Generation",
                createDataset(),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 800 , 600 ) );
        setContentPane( chartPanel );
    }

    public void addData(double value, String type, Integer xvalue){
        dataset.addValue(value,type,xvalue);
    }

    private DefaultCategoryDataset createDataset( ) {
        dataset = new DefaultCategoryDataset( );
        /*dataset.addValue( 0 , "max" , (Integer)0);
        dataset.addValue( 0 , "avg" , (Integer)0);
        dataset.addValue( 0 , "min" , (Integer)0);*/
        return dataset;
    }

    public static void main( String[ ] args ) {
        LineChart chart = new LineChart();
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
        try{Thread.sleep(5000);}catch (Exception e){e.printStackTrace();};
        chart.addData(10,"max",1);
        chart.addData(8,"avg",1);
        chart.addData(6,"min",1);
        try{Thread.sleep(5000);}catch (Exception e){e.printStackTrace();};
        chart.addData(12,"max",2);
        chart.addData(11,"avg",2);
        chart.addData(10,"min",2);
    }
}

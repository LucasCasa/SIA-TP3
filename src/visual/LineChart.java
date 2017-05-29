package visual;

import ar.edu.itba.sia.Config;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

/**
 * Created by Usuario on 28/5/2017.
 */
public class LineChart extends ApplicationFrame {
    private DefaultCategoryDataset dataset;

    public LineChart() {
        super(Config.getInstance().getProperty("app_title"));
        JFreeChart lineChart = ChartFactory.createLineChart(
                Config.getInstance().getProperty("chart_title"),
                "Generation","Fitness",
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
        return dataset;
    }
}

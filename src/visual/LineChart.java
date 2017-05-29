package visual;

import ar.edu.itba.sia.Config;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.Title;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 28/5/2017.
 */
public class LineChart extends ApplicationFrame {
    private DefaultCategoryDataset dataset;
    private JFreeChart lineChart;

    public LineChart() {
        super(Config.getInstance().getProperty("app_title"));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        lineChart = ChartFactory.createLineChart(
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

    public void addGeneration(double best, double avg, double min, Integer generation){
        dataset.addValue(best, "best", generation);
        dataset.addValue(avg, "avg", generation);
        dataset.addValue(min, "min", generation);
        lineChart.setTitle("Generation: " + generation.toString());
    }

    private DefaultCategoryDataset createDataset( ) {
        dataset = new DefaultCategoryDataset( );
        return dataset;
    }
}

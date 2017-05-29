package visual;

import ar.edu.itba.sia.Config;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;

/**
 * Created by Usuario on 28/5/2017.
 */
public class LineChart extends ApplicationFrame {
    private XYSeriesCollection dataset;
    private JFreeChart lineChart;

    public LineChart() {
        super(Config.getInstance().getProperty("app_title"));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        lineChart = ChartFactory.createXYLineChart(
                Config.getInstance().getProperty("chart_title"),
                "Generation","Fitness",
                createDataset(),
                PlotOrientation.VERTICAL,
                true,true,false);
        lineChart.addSubtitle(0,new TextTitle("Generation: 0"));
        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 800 , 600 ) );
        setContentPane( chartPanel );
    }

    public void addGeneration(double best, double avg, double min, Integer generation){
        ((TextTitle)(lineChart.getSubtitle(0))).setText("Generation: " + generation);
        dataset.getSeries("best").add((double)generation,best);
        dataset.getSeries("avg").add((double)generation,avg);
        dataset.getSeries("min").add((double)generation,min);
    }

    private XYSeriesCollection createDataset( ) {
        XYSeries data1 = new XYSeries("best");
        XYSeries data2 = new XYSeries("avg");
        XYSeries data3 = new XYSeries("min");
        dataset = new XYSeriesCollection();
        dataset.addSeries(data1);
        dataset.addSeries(data2);
        dataset.addSeries(data3);
        return dataset;
    }
}

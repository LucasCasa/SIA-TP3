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
import java.awt.*;

/**
 * Created by Usuario on 28/5/2017.
 */
public class LineChart extends ApplicationFrame {
    private XYSeriesCollection dataset;
    private JFreeChart lineChart;
    private boolean show_best;
    private boolean show_max;
    private boolean show_avg;
    private boolean show_min;

    public LineChart() {
        super(Config.getInstance().getProperty("app_title"));
        show_best = Config.getInstance().getBoolean("show_best");
        show_max = Config.getInstance().getBoolean("show_max");
        show_avg = Config.getInstance().getBoolean("show_avg");
        show_min = Config.getInstance().getBoolean("show_min");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        lineChart = ChartFactory.createXYLineChart(
                "Fitness per Generation",
                "Generation","Fitness",
                createDataset(),
                PlotOrientation.VERTICAL,
                true,true,false);

        lineChart.addSubtitle(0,new TextTitle("Generation: 0"));
        lineChart.addSubtitle(1,new TextTitle("Best: 0"));
        ChartPanel chartPanel = new ChartPanel( lineChart );
        lineChart.getXYPlot().getRenderer().setSeriesPaint(2, new Color(0,100,0));
        chartPanel.setPreferredSize( new java.awt.Dimension( 800 , 600 ) );
        setContentPane( chartPanel );
    }

    public void addGeneration(double best, double max, double avg, double min, Integer generation){
        ((TextTitle)(lineChart.getSubtitle(0))).setText("Generation: " + generation);
        ((TextTitle)(lineChart.getSubtitle(1))).setText("Best: " + best);
        if(show_best)
            dataset.getSeries("Best").add((double)generation,best);
        if(show_max)
            dataset.getSeries("Maximum").add((double)generation,max);
        if(show_avg)
            dataset.getSeries("Average").add((double)generation,avg);
        if(show_min)
            dataset.getSeries("Minimum").add((double)generation,min);
    }

    private XYSeriesCollection createDataset( ) {
        dataset = new XYSeriesCollection();
        if(show_best) {
            XYSeries data1 = new XYSeries("Best");
            data1.setDescription("Best");
            dataset.addSeries(data1);
        }
        if(show_max) {
            XYSeries data2 = new XYSeries("Maximum");
            data2.setDescription("Maximum");
            dataset.addSeries(data2);
        }
        if(show_avg) {
            XYSeries data3 = new XYSeries("Average");
            data3.setDescription("Average");
            dataset.addSeries(data3);
        }
        if(show_min) {
            XYSeries data4 = new XYSeries("Minimum");
            data4.setDescription("Minimum");
            dataset.addSeries(data4);
        }
        return dataset;
    }
}

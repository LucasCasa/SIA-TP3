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

    public LineChart() {
        super(Config.getInstance().getProperty("app_title"));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        lineChart = ChartFactory.createXYLineChart(
                "Best fitness overall and Average and Minimum Fitness per Generation",
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

    public void addGeneration(double best, double avg, double min, Integer generation){
        ((TextTitle)(lineChart.getSubtitle(0))).setText("Generation: " + generation);
        ((TextTitle)(lineChart.getSubtitle(1))).setText("Best: " + best);
        dataset.getSeries("Best").add((double)generation,best);
        dataset.getSeries("Average").add((double)generation,avg);
        dataset.getSeries("Minimum").add((double)generation,min);

    }

    private XYSeriesCollection createDataset( ) {
        XYSeries data1 = new XYSeries("Best");
        XYSeries data2 = new XYSeries("Average");
        XYSeries data3 = new XYSeries("Minimum");
        data1.setDescription("Best");
        data2.setDescription("Average");
        data3.setDescription("Minimum");
        dataset = new XYSeriesCollection();
        dataset.addSeries(data1);
        dataset.addSeries(data2);
        dataset.addSeries(data3);
        return dataset;
    }
}

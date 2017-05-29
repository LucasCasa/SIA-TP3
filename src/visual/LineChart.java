package visual;

import ar.edu.itba.sia.Config;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.entity.TitleEntity;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

/**
 * Created by Usuario on 28/5/2017.
 */
public class LineChart extends ApplicationFrame {
    private XYSeriesCollection dataset;
    private JFreeChart lineChart;

    public LineChart() {
        super(Config.getInstance().getProperty("app_title"));
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

    public void addData(double value, String type, Integer xvalue){
        //dataset.addValue(value,type,xvalue);
    }

    public void addGeneration(double best, double avg, double min, Integer generation){
        ((TextTitle)(lineChart.getSubtitle(0))).setText("Generation: " + generation);
        dataset.getSeries("best").add((double)generation,best);
        dataset.getSeries("avg").add((double)generation,avg);
        dataset.getSeries("min").add((double)generation,min);
        /*dataset.addValue(best, "best", generation);
        dataset.addValue(avg, "avg", generation);
        dataset.addValue(min, "min", generation);*/
        //lineChart.setTitle("Generation: " + generation.toString());

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

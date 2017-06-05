package ar.edu.itba.sia;

import interfaces.*;
import org.jfree.ui.RefineryUtilities;
import visual.LineChart;

import java.io.IOException;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Main {

    public static void main(String[] args) {

        try {
            Config.getInstance().loadConfig("config.properties");
            System.out.println("Loading data...");
            DataLoader.loadData();
            System.out.println("Done");
            for(int i = 0; i<10;i++) {
                Crosser crosser = Config.getInstance().getCrosser();
                Selector selectionSelector = Config.getInstance().getSelectionSelector();
                Selector replacementSelector = Config.getInstance().getReplacementSelector();

                Mutator mutator = Config.getInstance().getMutator();

                int N = Integer.parseInt(Config.getInstance().getProperty("n"));
                int k = Integer.parseInt(Config.getInstance().getProperty("k"));
                Constants.MODIF[Constants.STRENGTH] = Config.getInstance().getDouble("strength_m");
                Constants.MODIF[Constants.AGILITY] = Config.getInstance().getDouble("agility_m");
                Constants.MODIF[Constants.WISDOM] = Config.getInstance().getDouble("wisdom_m");
                Constants.MODIF[Constants.RESISTANCE] = Config.getInstance().getDouble("resistance_m");
                Constants.MODIF[Constants.LIFE] = Config.getInstance().getDouble("life_m");


                LineChart chart = null;
                if (Config.getInstance().getBoolean("visual")) {
                    chart = new LineChart();
                    chart.pack();
                    RefineryUtilities.centerFrameOnScreen(chart);
                    chart.setVisible(true);
                }

                EndCondition condition = Config.getInstance().getEndCondition();

                Evolver e = new Evolver(crosser, selectionSelector, replacementSelector, mutator, N, k, chart);
                e.randomGeneration();
                e.evolve(condition);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

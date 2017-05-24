package ar.edu.itba.sia;

import interfaces.Chromosome;
import interfaces.Crosser;
import interfaces.Mutator;
import interfaces.Selector;

import java.io.IOException;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Main {

    public static void main(String[] args) {

        try {
            Config.getInstance().loadConfig("config.properties");

            Crosser crosser = Config.getInstance().getCrosser();
            Selector selectionSelector = Config.getInstance().getSelectionSelector();
            Selector replacementSelector = Config.getInstance().getSelectionSelector();

            Mutator mutator = Config.getInstance().getMutator();

            int N = Integer.parseInt(Config.getInstance().getProperty("n"));
            int k = Integer.parseInt(Config.getInstance().getProperty("k"));
            double pc = Config.getInstance().getDouble("pc");
            double pm = Config.getInstance().getDouble("pm");
            Constants.MODIF[Constants.STRENGTH] = Config.getInstance().getDouble("strength_m");
            Constants.MODIF[Constants.AGILITY]= Config.getInstance().getDouble("agility_m");
            Constants.MODIF[Constants.WISDOM] = Config.getInstance().getDouble("wisdom_m");
            Constants.MODIF[Constants.RESISTANCE] = Config.getInstance().getDouble("resistance_m");
            Constants.MODIF[Constants.LIFE] = Config.getInstance().getDouble("life_m");
            DataLoader.loadData();
            Evolver e = new Evolver(crosser,selectionSelector,replacementSelector,mutator,N,k);
            e.randomGeneration();
            e.evolve();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

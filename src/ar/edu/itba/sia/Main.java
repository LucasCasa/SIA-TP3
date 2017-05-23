package ar.edu.itba.sia;

import interfaces.Chromosome;
import interfaces.Crosser;
import interfaces.Selector;

import java.io.IOException;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Main {

    public static void main(String[] args) {

        try {
            Config.getInstance().loadConfig("config.properties");
            long time = System.currentTimeMillis();
            Chromosome[][] c = DataLoader.loadData();
            Constants.VALUES = c;
            System.out.println(System.currentTimeMillis() - time);

            Crosser crosser = Config.getInstance().getCrosser();
            Selector selector = Config.getInstance().getSelector();
            int N = Integer.parseInt(Config.getInstance().getProperty("N"));
            int k = Integer.parseInt(Config.getInstance().getProperty("k"));

            Evolver e = new Evolver(crosser,selector,N,k);
            e.randomGeneration();
            e.evolve();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

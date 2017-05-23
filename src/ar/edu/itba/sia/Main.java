package ar.edu.itba.sia;

import interfaces.Chromosome;

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
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

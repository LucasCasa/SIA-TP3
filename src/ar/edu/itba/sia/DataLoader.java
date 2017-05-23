package ar.edu.itba.sia;

import interfaces.Chromosome;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
/**
 * Created by lcasagrande on 23/05/17.
 */

public class DataLoader {


    static Chromosome[][] loadData() throws IOException{
        Chromosome[][] values = new Chromosome[5][Constants.ALELO_COUNT];
        load(values,0,"armas.tsv");
        load(values,1,"botas.tsv");
        load(values,2,"cascos.tsv");
        load(values,3,"guantes.tsv");
        load(values,4,"pecheras.tsv");
        return values;
    }
    private static void load(Chromosome[][] data, int p, String filename) throws IOException{
        Scanner s = new Scanner(new File(filename));
        s.useLocale(Locale.US);
        s.nextLine();
        for(int i = 0; i<Constants.ALELO_COUNT;i++){
            s.nextInt();
            Chromosome cloth = new Clothes();
            for(int j = 0; j<5;j++){
                 cloth.setAtPos(j,s.nextDouble());
            }
            data[p][i] = cloth;
        }
    }
}

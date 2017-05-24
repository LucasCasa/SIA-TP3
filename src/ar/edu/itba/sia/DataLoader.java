package ar.edu.itba.sia;

import interfaces.Chromosome;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;
/**
 * Created by lcasagrande on 23/05/17.
 */

public class DataLoader {


    static void loadData() throws IOException {
        Chromosome[][] values = new Chromosome[5][Constants.ALELO_COUNT];
        File f = new File("data.obj");
        if (f.exists()) {
            loadFile();
        } else {
            load(values, 0, "armas.tsv");
            load(values, 1, "botas.tsv");
            load(values, 2, "cascos.tsv");
            load(values, 3, "guantes.tsv");
            load(values, 4, "pecheras.tsv");
            Constants.VALUES = new Data(values);
            saveFile(Constants.VALUES);

        }
    }

    private static void loadFile() {
        FileInputStream fi = null;
        try {
            fi = new FileInputStream(new File("data.obj"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            Constants.VALUES = (Data) oi.readObject();
            oi.close();
            fi.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }

    private static void saveFile(Data d){
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        try {

            fout = new FileOutputStream("data.obj");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(d);

            System.out.println("Done");

        } catch (Exception ex) {

            ex.printStackTrace();

        } finally {

            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
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

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
/**
 * Created by lcasagrande on 23/05/17.
 */

public class DataLoader {


    static double[][][] loadData() throws IOException{
        double[][][] values = new double[5][100000][5];
        load(values,0,"armas.tsv");
        load(values,1,"botas.tsv");
        load(values,2,"cascos.tsv");
        load(values,3,"guantes.tsv");
        load(values,4,"pecheras.tsv");
        return values;
    }
    private static void load(double[][][] data, int p, String filename) throws IOException{
        Scanner s = new Scanner(new File(filename));
        s.useLocale(Locale.US);
        s.nextLine();
        for(int i = 0; i<100000;i++){
            s.nextInt();
            for(int j = 0; j<5;j++){
                data[p][i][j] = s.nextDouble();
            }
        }
    }
}

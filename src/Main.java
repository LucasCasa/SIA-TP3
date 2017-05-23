import java.io.IOException;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Main {

    public static void main(String[] args) {
        // CARGO TODOS LOS DATOS DEL ARCHIVO
        try {
            long time = System.currentTimeMillis();
            double [][][] c = DataLoader.loadData();
            System.out.println(System.currentTimeMillis() - time);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

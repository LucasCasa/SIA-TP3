package crossers;

import interfaces.Crosser;
import interfaces.Phenotype;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Anular implements Crosser {
    @Override
    public Phenotype[] crossover(Phenotype p1, Phenotype p2) {
        return new Phenotype[0];
    }
}

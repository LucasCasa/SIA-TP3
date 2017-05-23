package interfaces;

/**
 * Created by lcasagrande on 23/05/17.
 */
public interface Selector {

    Phenotype[] selectPhenotypes(Phenotype[] population, int k);
}

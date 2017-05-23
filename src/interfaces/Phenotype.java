package interfaces;

/**
 * Created by lcasagrande on 23/05/17.
 */
public interface Phenotype {

    double getFitness();
    Chromosome getChromosomeAtLocus();
    double getLocusCount();
}

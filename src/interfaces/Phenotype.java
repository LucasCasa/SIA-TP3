package interfaces;

/**
 * Created by lcasagrande on 23/05/17.
 */
public interface Phenotype {

    double getFitness();
    int getChromosomeAtLocus(int pos);
    void setChromosomeAtLocus(int c, int pos);
    int getLocusCount();
    double getHeight();

    Phenotype mutate();
}

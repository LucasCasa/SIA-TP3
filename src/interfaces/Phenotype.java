package interfaces;

/**
 * Created by lcasagrande on 23/05/17.
 */
public interface Phenotype {

    double getFitness();
    Chromosome getChromosomeAtLocus(int pos);
    void setChromosomeAtLocus(Chromosome c, int pos);
    int getLocusCount();
    double getHeight();
    Chromosome[] getChromosomes();
}

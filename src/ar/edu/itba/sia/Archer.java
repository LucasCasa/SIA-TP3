package ar.edu.itba.sia;

import interfaces.Chromosome;
import interfaces.Phenotype;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Archer implements Phenotype{
    int[] chromosomes;

    public Archer(int [] data){
        chromosomes = new int[data.length];
        for(int i = 0; i<data.length;i++) {
            chromosomes[i] = data[i];
        }
    }
    public Archer(){
        chromosomes = new int[Constants.CHROMOSOME_COUNT];
    }
    @Override
    public double getFitness() {
        return 0;
    }

    @Override
    public Chromosome getChromosomeAtLocus(int pos) {
        return Constants.VALUES[pos][chromosomes[pos]];
    }

    @Override
    public void setChromosomeAtLocus(Chromosome c, int pos) {

    }

    @Override
    public int getLocusCount() {
        return 0;
    }
}

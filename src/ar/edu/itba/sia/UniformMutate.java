package ar.edu.itba.sia;

import characters.Archer;
import crossers.Uniform;
import interfaces.Mutator;
import interfaces.Phenotype;

import java.util.Random;

/**
 * Created by lcasagrande on 24/05/17.
 */
public class UniformMutate implements Mutator {
    double pm;
    Random rand = new Random();
    public UniformMutate(double pm){
        this.pm = pm;
    }

    @Override
    public Phenotype mutate(Phenotype original) {
        for(int i = 0; i<Constants.CHROMOSOME_COUNT;i++){
            if(Math.random() < pm){
                if(i == 0){
                    original.setChromosomeAtLocus(new Height(rand.nextDouble()*0.7 + 1.3),i);
                }else {
                    original.setChromosomeAtLocus(Constants.VALUES.get(i-1, rand.nextInt(Constants.ALELO_COUNT)), i);
                }
            }
        }
        return original;
    }
}

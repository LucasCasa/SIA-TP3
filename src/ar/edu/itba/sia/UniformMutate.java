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
    double maxHeight;
    double minHeight;
    Random rand = new Random();

    public UniformMutate(double pm){
        this.pm = pm;
        minHeight = Config.getInstance().getDouble("min_height");
        maxHeight = Config.getInstance().getDouble("max_height");
        if(minHeight<=0 || maxHeight <=0 || minHeight>maxHeight)
            throw new IllegalArgumentException("Invalid heights");
    }

    @Override
    public Phenotype mutate(Phenotype original) {
        for(int i = 0; i<Constants.CHROMOSOME_COUNT;i++){
            if(Math.random() < pm){
                if(i == 0){
                    original.setChromosomeAtLocus(new Height(rand.nextDouble()*(maxHeight-minHeight)+minHeight),i);
                }else {
                    original.setChromosomeAtLocus(Constants.VALUES.get(i-1, rand.nextInt(Constants.ALELO_COUNT)), i);
                }
            }
        }
        return original;
    }
}

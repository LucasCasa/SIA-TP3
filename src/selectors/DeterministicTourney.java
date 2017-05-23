package selectors;

import interfaces.Phenotype;
import interfaces.Selector;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class DeterministicTourney implements Selector{

    @Override
    public Phenotype[] selectPhenotypes(Phenotype[] population, int k) {
        for(int i=0; i<k; i++){
            Phenotype p1 = population[getRand(k)];
            Phenotype p2 = population[getRand(k)];
        }
        return null;
    }

    /*
        Returns random int between 0 and limit (exclusive)
     */
    protected int getRand(int limit){
        int x;
        do{
            x = (int)Math.floor(Math.random()*limit);
        }while(x==limit);
        return x;
    }
}

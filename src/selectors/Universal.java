package selectors;

import interfaces.Phenotype;
import interfaces.Selector;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Universal extends Roulette {
    @Override
    public Phenotype[] selectPhenotypes(Phenotype[] population, int k) {
        Phenotype[] ans = new Phenotype[k];
        double[] accumulatedFitness = getAccumulatedFitness(population);
        double r = Math.random();
        for(int i=0; i<k; i++){
            double rand = (r+i)/((double)k);
            Phenotype selected = getSelected(accumulatedFitness,rand,population);
            ans[i] = selected;
        }
        return ans;
    }

}

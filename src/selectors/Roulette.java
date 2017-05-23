package selectors;

import interfaces.Phenotype;
import interfaces.Selector;
import java.lang.Math;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Roulette implements Selector {
    @Override
    public Phenotype[] selectPhenotypes(Phenotype[] population, int k) {
        Phenotype[] ans = new Phenotype[k];
        double[] accumulatedFitness = getAccumulatedFitness(population);
        for(int i=0; i<k; i++){
            double rand = Math.random();
            Phenotype selected = getSelected(accumulatedFitness,rand,population);
            ans[i] = selected;
        }
        return ans;
    }

    protected double[] getAccumulatedFitness(Phenotype[] pop){
        double[] res = new double[pop.length];
        double total = getTotalFitness(pop);
        double accumulated = 0;
        for(int i=0; i<pop.length; i++){
            double relative = pop[i].getFitness() / total;
            accumulated = accumulated + relative;
            res[i] = accumulated;
        }
        return res;
    }

    protected double getTotalFitness(Phenotype[] pop){
        double total = 0;
        for(Phenotype p: pop)
            total += p.getFitness();
        return total;
    }

    protected Phenotype getSelected(double[] accumulatedFitness, double rand, Phenotype[] pop){
        for(int i=0; i<accumulatedFitness.length; i++){
            if(rand <= accumulatedFitness[i])
                return pop[i];
        }
        return null;
    }
}

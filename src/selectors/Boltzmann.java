package selectors;

import ar.edu.itba.sia.Config;
import interfaces.Phenotype;
import interfaces.Selector;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Boltzmann implements Selector {

    private double T;
    private int generation; // T varies according to generation (more g less t)

    public Boltzmann(){
        T = Config.getInstance().getInteger("initial_temperature");
        if(T<=0){
            throw new IllegalArgumentException("Illegal value for param initial_temperature");
        }
        generation = 1;
    }

    @Override
    public Phenotype[] selectPhenotypes(Phenotype[] population, int k) {
        Phenotype[] ans = new Phenotype[k];
        double[] accumulatedFitness = getAccumulatedFitness(population);
        for(int i=0; i<k; i++){
            double rand = Math.random()*population.length;
            Phenotype selected = getSelected(accumulatedFitness,rand,population);
            ans[i] = selected;
        }
        generation++;
        if(generation%1000==0 && T>=2)
            T--;
        return ans;
    }

    protected double[] getAccumulatedFitness(Phenotype[] pop){
        double[] res = new double[pop.length];
        double avg = getAvgFitness(pop);
        double accumulated = 0;
        for(int i=0; i<pop.length; i++){
            double relative = Math.exp(pop[i].getFitness()/T) / avg;
            accumulated = accumulated + relative;
            res[i] = accumulated;
        }
        return res;
    }

    private double getAvgFitness(Phenotype[] pop){
        double total = 0;
        for(Phenotype p: pop)
            total += Math.exp(p.getFitness()/T);
        return total/(double)pop.length;
    }

    private Phenotype getSelected(double[] accumulatedFitness, double rand, Phenotype[] pop){
        for(int i=0; i<accumulatedFitness.length; i++){
            if(rand <= accumulatedFitness[i])
                return pop[i];
        }
        return null;
    }


}

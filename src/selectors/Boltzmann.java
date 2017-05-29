package selectors;

import ar.edu.itba.sia.Config;
import interfaces.Phenotype;
import interfaces.Selector;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Boltzmann implements Selector {

    private double k;
    private double initial_temperature;
    private int generation;
    private final double minT = 0.1;
    private boolean lessThanMin = false;

    public Boltzmann(){
        k = Config.getInstance().getDouble("temperature_k");
        initial_temperature = Config.getInstance().getDouble("initial_temperature");
        if(initial_temperature<=0 || k<=0){
            throw new IllegalArgumentException("Illegal values for temperature configuration");
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
        return ans;
    }

    protected double[] getAccumulatedFitness(Phenotype[] pop){
        double[] res = new double[pop.length];
        double avg = getAvgFitness(pop);
        double accumulated = 0;
        for(int i=0; i<pop.length; i++){
            double relative = Math.exp(pop[i].getFitness()/getT()) / avg;
            accumulated = accumulated + relative;
            res[i] = accumulated;
        }
        return res;
    }

    private double getAvgFitness(Phenotype[] pop){
        double total = 0;
        for(Phenotype p: pop)
            total += Math.exp(p.getFitness()/getT());
        return total/(double)pop.length;
    }

    private Phenotype getSelected(double[] accumulatedFitness, double rand, Phenotype[] pop){
        for(int i=0; i<accumulatedFitness.length; i++){
            if(rand <= accumulatedFitness[i])
                return pop[i];
        }
        return null;
    }

    private double getT(){
        if(lessThanMin)
            return minT;
        double T = 100*Math.exp(-generation*k/100000);
        if(T<minT) {
            lessThanMin = true;
            return minT;
        }
        return T;
    }
}

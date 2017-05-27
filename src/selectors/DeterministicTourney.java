package selectors;

import ar.edu.itba.sia.Config;
import interfaces.Phenotype;
import interfaces.Selector;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class DeterministicTourney implements Selector{

    int m;

    public DeterministicTourney(){
        m = Config.getInstance().getInteger("tournament_m");
        if(m<2){
            throw new IllegalArgumentException("Illegal value for param tournament_m, must be 2 or greater");
        }
    }

    @Override
    public Phenotype[] selectPhenotypes(Phenotype[] population, int k) {
        Phenotype[] competitors = new Phenotype[m];
        Phenotype[] selected = new Phenotype[k];
        for(int i=0; i<k; i++){
            getCompetitors(competitors,population);
            Phenotype winner = getWinner(competitors);
            selected[i] = winner;
        }
        return selected;
    }

    protected void getCompetitors(Phenotype[] selected, Phenotype[] pop){
        for(int i=0; i<selected.length; i++) {
            int r = getRand(pop.length);
            selected[i] = pop[r];
        }
    }

    private Phenotype getWinner(Phenotype[] competitors){
        Phenotype best = null;
        for(Phenotype p: competitors){
            if(best==null || p.getFitness()>best.getFitness())
                best = p;
        }
        return best;
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

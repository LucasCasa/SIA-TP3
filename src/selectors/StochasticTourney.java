package selectors;

import ar.edu.itba.sia.Config;
import interfaces.Phenotype;
import interfaces.Selector;

import java.util.Set;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class StochasticTourney extends DeterministicTourney{

    double percentage;

    public StochasticTourney(){
        super();
        percentage = Config.getInstance().getDouble("tournament_r");
        if(percentage>1 || percentage<=0.5) {
            throw new IllegalArgumentException("Illegal value for param tournament_r, must be greater than 0.5 and smaller or equal to 1");
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

    private Phenotype getWinner(Phenotype[] competitors){
        Phenotype best = null;
        for(Phenotype p: competitors){
            if(best==null)
                best = p;
            else{
                boolean smallerWins = Math.random()>percentage;
                if(p.getFitness()>best.getFitness()){
                    if(!smallerWins)
                        best = p;
                }else{
                    if(smallerWins)
                        best = p;
                }
            }
        }
        return best;
    }
}

package endconditions;

import ar.edu.itba.sia.Config;
import ar.edu.itba.sia.Evolver;
import interfaces.EndCondition;

/**
 * Created by nkuyumciyan on 29/05/17.
 */
public class EndByBest implements EndCondition{

    int maximum;
    int counter;
    double currentBest;

    public EndByBest(){
        maximum = Config.getInstance().getInteger("best_amount");
        if(maximum<=0)
            throw new IllegalArgumentException("best_amount must be a positive integer");
        counter=0;
        currentBest = 0;
    }

    @Override
    public boolean end(Evolver e) {
        if(e.getBest() == null)
            return false;
        if(e.getBest().getFitness()==currentBest) {
            counter++;
            if(counter>=maximum)
                return true;
            return false;
        }else{
            counter=1;
            currentBest = e.getBest().getFitness();
            return false;
        }
    }
}

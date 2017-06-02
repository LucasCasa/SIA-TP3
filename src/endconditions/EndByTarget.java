package endconditions;

import ar.edu.itba.sia.Config;
import ar.edu.itba.sia.Evolver;
import interfaces.EndCondition;

/**
 * Created by Lucas on 01/06/2017.
 */
public class EndByTarget implements EndCondition{
    double target = 0;
    public EndByTarget(){
        target = Config.getInstance().getDouble("target");
        if(target<=0)
            throw new IllegalArgumentException("target must be a positive double");
    }
    @Override
    public boolean end(Evolver e) {
        return e.getBest().getFitness() >= target || e.getGeneration() > 5000000;
    }
}

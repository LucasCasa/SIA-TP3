package endconditions;

import ar.edu.itba.sia.Config;
import ar.edu.itba.sia.Evolver;
import interfaces.EndCondition;

/**
 * Created by Lucas on 01/06/2017.
 */
public class EndByTarget implements EndCondition{
    double target = 0;
    int target_limit;
    public EndByTarget(){
        target = Config.getInstance().getDouble("target");
        target_limit = Config.getInstance().getInteger("target_limit");
        if(target<=0)
            throw new IllegalArgumentException("target must be a positive double");
        if(target_limit<=0)
            throw new IllegalArgumentException("Invalid target limit");
    }
    @Override
    public boolean end(Evolver e) {
        return e.getBest().getFitness() >= target || e.getGeneration() > target_limit;
    }
}

package endconditions;

import ar.edu.itba.sia.Config;
import ar.edu.itba.sia.Evolver;
import interfaces.EndCondition;

/**
 * Created by nkuyumciyan on 29/05/17.
 */
public class GenerationCount implements EndCondition{

    private int maximum;

    public GenerationCount(){
        maximum = Config.getInstance().getInteger("generations");
        if(maximum<=0)
            throw new IllegalArgumentException("generations must be a positive integer");
    }

    @Override
    public boolean end(Evolver e) {
        return e.getGeneration()>=maximum;
    }
}

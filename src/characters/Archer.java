package characters;

import ar.edu.itba.sia.Constants;
import interfaces.Chromosome;
import interfaces.Phenotype;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Archer extends Character{

    public Archer(Chromosome[] data){
        super(data);
    }

    public Archer(double height){
        super(height);
    }

    @Override
    public double getFitness() {
        return 0.9*getAttack() + 0.1*getDefense();
    }

}

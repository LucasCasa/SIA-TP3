package characters;

import interfaces.Chromosome;

/**
 * Created by nkuyumciyan on 23/05/17.
 */
public class Defender extends Character {

    public Defender(Chromosome[] data){
        super(data);
    }

    public Defender(){
        super();
    }

    @Override
    public double getFitness() {
        return 0.1*getAttack() + 0.9*getDefense();
    }
}

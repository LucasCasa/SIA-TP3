package characters;

import interfaces.Chromosome;

/**
 * Created by nkuyumciyan on 23/05/17.
 */
public class Warrior extends Character{

    public Warrior(Chromosome[] data){
        super(data);
    }

    public Warrior(){
        super();
    }

    @Override
    public double getFitness() {
        return 0.6*getAttack() + 0.4*getDefense();
    }
}

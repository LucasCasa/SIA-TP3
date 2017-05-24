package characters;

import interfaces.Chromosome;

/**
 * Created by nkuyumciyan on 23/05/17.
 */
public class Assassin extends Character{

    public Assassin(Chromosome[] data){
        super(data);
    }

    public Assassin(double height){
        super(height);
    }

    @Override
    public double getFitness() {
        return 0.7*getAttack() + 0.3*getDefense();
    }
}

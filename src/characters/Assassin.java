package characters;

/**
 * Created by nkuyumciyan on 23/05/17.
 */
public class Assassin extends Character{

    public Assassin(int[] data,double height){
        super(data,height);
    }

    public Assassin(double height){
        super(height);
    }

    @Override
    public double getFitness() {
        return 0.7*getAttack() + 0.3*getDefense();
    }
}

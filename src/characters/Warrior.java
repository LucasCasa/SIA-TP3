package characters;

/**
 * Created by nkuyumciyan on 23/05/17.
 */
public class Warrior extends Character{

    public Warrior(int[] data,double height){
        super(data,height);
    }

    public Warrior(double height){
        super(height);
    }

    @Override
    public double getFitness() {
        return 0.6*getAttack() + 0.4*getDefense();
    }
}

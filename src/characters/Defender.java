package characters;

/**
 * Created by nkuyumciyan on 23/05/17.
 */
public class Defender extends Character {

    public Defender(int[] data,double height){
        super(data,height);
    }

    public Defender(double height){
        super(height);
    }

    @Override
    public double getFitness() {
        return 0.1*getAttack() + 0.9*getDefense();
    }
}

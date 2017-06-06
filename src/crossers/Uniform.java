package crossers;

import ar.edu.itba.sia.Constants;
import characters.Character;
import characters.CharacterBuilder;
import interfaces.Crosser;
import interfaces.Phenotype;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Uniform implements Crosser{
    private double pc;

    public Uniform(double pc){
        this.pc = pc;
    }
    @Override
    public Phenotype[] crossover(Phenotype p1, Phenotype p2) {
        Phenotype[] children = new Phenotype[2];
        if(Math.random() > pc){
            children[0] = CharacterBuilder.getInstance().build(p1.getChromosomes());
            children[1] = CharacterBuilder.getInstance().build(p2.getChromosomes());
            return children;
        }
        children[0] = CharacterBuilder.getInstance().build();
        children[1] = CharacterBuilder.getInstance().build();
        for(int i = 0; i< Constants.CHROMOSOME_COUNT;i++){
            if(Math.random() < 0.5){
                children[0].setChromosomeAtLocus(p1.getChromosomeAtLocus(i),i);
                children[1].setChromosomeAtLocus(p2.getChromosomeAtLocus(i),i);
            }else{
                children[0].setChromosomeAtLocus(p2.getChromosomeAtLocus(i),i);
                children[1].setChromosomeAtLocus(p1.getChromosomeAtLocus(i),i);
            }
        }
        return children;
    }
}

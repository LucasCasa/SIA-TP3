package crossers;

import characters.Archer;
import ar.edu.itba.sia.Constants;
import characters.CharacterBuilder;
import interfaces.Crosser;
import interfaces.Phenotype;

import java.util.Random;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Anular implements Crosser {
    Random r = new Random();
    public double pc;

    public Anular(double pc){
        this.pc = pc;
    }

    @Override
    public Phenotype[] crossover(Phenotype p1, Phenotype p2) {
        Phenotype[] children = new Phenotype[2];

        if(Math.random() > pc){
            children[0] = p1;
            children[1] = p2;
            return children;
        }

        children[0] = CharacterBuilder.getInstance().build();
        children[1] = CharacterBuilder.getInstance().build();
        int rand = r.nextInt(Constants.CHROMOSOME_COUNT);
        int l = r.nextInt(Constants.CHROMOSOME_COUNT / 2) + 1;
        if(rand + l > Constants.CHROMOSOME_COUNT) {
            for(int i = 0; i<Constants.CHROMOSOME_COUNT; i++){
                if(rand + l - Constants.CHROMOSOME_COUNT < i && i < rand + l){
                    children[0].setChromosomeAtLocus(p1.getChromosomeAtLocus(i),i);
                    children[1].setChromosomeAtLocus(p2.getChromosomeAtLocus(i),i);
                }else{
                    children[0].setChromosomeAtLocus(p2.getChromosomeAtLocus(i),i);
                    children[1].setChromosomeAtLocus(p1.getChromosomeAtLocus(i),i);
                }
            }
        }else {
            for (int i = 0; i < Constants.CHROMOSOME_COUNT; i++) {
                if (rand <= i || i >= rand + l) {
                    children[0].setChromosomeAtLocus(p1.getChromosomeAtLocus(i), i);
                    children[1].setChromosomeAtLocus(p2.getChromosomeAtLocus(i), i);
                } else {
                    children[0].setChromosomeAtLocus(p2.getChromosomeAtLocus(i), i);
                    children[1].setChromosomeAtLocus(p1.getChromosomeAtLocus(i), i);
                }
            }
        }
        return children;
    }
}

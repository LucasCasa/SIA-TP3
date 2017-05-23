package crossers;

import characters.Archer;
import ar.edu.itba.sia.Constants;
import interfaces.Crosser;
import interfaces.Phenotype;

import java.util.Random;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class OnePoint implements Crosser {
    Random r = new Random();
    @Override
    public Phenotype[] crossover(Phenotype p1, Phenotype p2) {
        Phenotype[] children = new Phenotype[2];
        children[0] = new Archer((p1.getHeight()+p2.getHeight())/2);
        children[1] = new Archer((p1.getHeight()+p2.getHeight())/2);
        int rand = r.nextInt(Constants.CHROMOSOME_COUNT);
        for(int i = 0; i<Constants.CHROMOSOME_COUNT; i++){
            if(rand <= i){
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

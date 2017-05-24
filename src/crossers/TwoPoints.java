package crossers;

import characters.Archer;
import ar.edu.itba.sia.Constants;
import interfaces.Crosser;
import interfaces.Phenotype;

import java.util.Random;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class TwoPoints implements Crosser {
    Random r = new Random();
    double pc;

    public TwoPoints(double pc){
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

        children[0] = new Archer((p1.getHeight()+p2.getHeight())/2);
        children[1] = new Archer((p1.getHeight()+p2.getHeight())/2);
        int rand1 = r.nextInt(Constants.CHROMOSOME_COUNT);
        int rand2 = r.nextInt(Constants.CHROMOSOME_COUNT);
        if(rand1 > rand2){
            int aux = rand1;
            rand1 = rand2;
            rand2 = aux;
        }
        for(int i = 0; i<Constants.CHROMOSOME_COUNT; i++){
            if(rand1 <= i || i >= rand2){
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

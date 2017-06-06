package selectors;

import interfaces.Phenotype;
import interfaces.Selector;

/**
 * Created by lcasagrande on 24/05/17.
 */
public class MixedSelector implements Selector {
    private Selector s1;
    private Selector s2;
    private double part;
    Phenotype[] newPop;

    public MixedSelector(Selector s1, Selector s2, double part){
        this.s1 = s1;
        this.s2 = s2;
        this.part = part;
    }
    @Override
    public Phenotype[] selectPhenotypes(Phenotype[] population, int k) {
        if(newPop == null){
            newPop = new Phenotype[k];
        }
        int partS1 = (int) (k*part);
        int partS2 = k - partS1;
        Phenotype[] pop1 = s1.selectPhenotypes(population,partS1);
        Phenotype[] pop2 = s2.selectPhenotypes(population,partS2);
        System.arraycopy(pop1,0,newPop,0,partS1);
        System.arraycopy(pop2,0,newPop,partS1,partS2);
        return newPop;
    }
}

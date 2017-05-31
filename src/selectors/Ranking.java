package selectors;

import interfaces.Phenotype;
import interfaces.Selector;

import java.util.*;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Ranking implements Selector {

    @Override
    public Phenotype[] selectPhenotypes(Phenotype[] population, int k) {
        Phenotype[] selected = new Phenotype[k];

        List<Phenotype> list = createList(population);
        Collections.sort(list, (o1, o2) -> {
            if(o1.getFitness()-o2.getFitness()>0) return -1;
            if(o1.getFitness()-o2.getFitness()<0) return 1;
            return 0;
        });
        for(int i=0; i<k; i++){
            Phenotype p = select(list);
            selected[i] = p;
        }
        return selected;
    }

    private Phenotype select(List<Phenotype> orderedList){
        int sum = sumToN(orderedList.size());
        double rand = Math.random();
        int original = orderedList.size();
        int numerator = 0;
        int counter = 0;
        for(Phenotype p: orderedList){
            numerator += (original-counter);
            if(rand<=(double)numerator/(double)sum)
                return p;
            counter++;

        }
        return null;
    }

    private int sumToN(int N){
        return (N*(N+1))/2;
    }

    private List<Phenotype> createList(Phenotype[] pop){
        List<Phenotype> list = new ArrayList<>();
        for(Phenotype p: pop)
            list.add(p);
        return list;
    }
}

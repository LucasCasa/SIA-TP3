package selectors;

import interfaces.Phenotype;
import interfaces.Selector;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by nkuyumciyan on 23/05/17.
 */
public class Elite implements Selector {
    @Override
    public Phenotype[] selectPhenotypes(Phenotype[] population, int k) {
        Phenotype[] selected = new Phenotype[k];
        PriorityQueue<Phenotype> q = new PriorityQueue<>((o1, o2) -> {
            if(o1.getFitness()-o2.getFitness()>0) return -1;
            if(o1.getFitness()-o2.getFitness()<0) return 1;
            return 0;
        });
        for(Phenotype p: population) {
            q.add(p);
        }
        int counter = 0;
        while(!q.isEmpty()){
            if(counter==k)
                return selected;
            selected[counter++] = q.poll();
        }
        return selected;
    }
}

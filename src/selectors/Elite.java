package selectors;

import interfaces.Phenotype;
import interfaces.Selector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by nkuyumciyan on 23/05/17.
 */
public class Elite implements Selector {
    @Override
    public Phenotype[] selectPhenotypes(Phenotype[] population, int k) {
        Phenotype[] selected = new Phenotype[population.length];
        PriorityQueue<Phenotype> q = new PriorityQueue<>((o1, o2) -> {
            if(o1.getFitness()-o2.getFitness()>0) return -1;
            if(o1.getFitness()-o2.getFitness()<0) return 1;
            return 0;
        });
        q.addAll(Arrays.asList(population));
        /*for(int i = 0; i<population.length;i++){
            selected[i] = population[i];
        }
        Arrays.sort(selected,(o1, o2) -> {
            if(o1.getFitness()-o2.getFitness()>0) return -1;
            if(o1.getFitness()-o2.getFitness()<0) return 1;
            return 0;
        });
        return Arrays.copyOf(selected,k);*/
        int counter = 0;
        while(!q.isEmpty()){
            if(counter==k)
                return selected;
            selected[counter++] = q.poll();
        }
        return selected;
    }
}

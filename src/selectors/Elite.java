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
    Phenotype[] selected;
    PriorityQueue<Phenotype> q;
    @Override
    public Phenotype[] selectPhenotypes(Phenotype[] population, int k) {
        if(selected == null) {
            selected = new Phenotype[population.length];
            q = new PriorityQueue<>((o1, o2) -> {
                if (o1.getFitness() - o2.getFitness() > 0) return -1;
                if (o1.getFitness() - o2.getFitness() < 0) return 1;
                return 0;
            });
        }
        for(int i = 0; i<population.length;i++){
            q.offer(population[i]);
        }

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
            if(counter==k) {
                q.clear();
                return selected;
            }
            selected[counter++] = q.poll();
        }
        return selected;
    }
}

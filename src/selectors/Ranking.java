package selectors;

import interfaces.Phenotype;
import interfaces.Selector;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Ranking implements Selector {

    @Override
    public Phenotype[] selectPhenotypes(Phenotype[] population, int k) {
        Phenotype[] selected = new Phenotype[k];
        PriorityQueue<Phenotype> q = new PriorityQueue<>(new Comparator<Phenotype>() {
            @Override
            public int compare(Phenotype o1, Phenotype o2) {
                if(o1.getFitness()-o2.getFitness()>0) return 1;
                if(o1.getFitness()-o2.getFitness()<0) return -1;
                return 0;
            }
        });
        for(Phenotype p: population) {
            q.add(p);
        }

        for(int i=0; i<k; i++){
            Phenotype p = select(q);
            selected[i] = p;
        }
        return selected;
    }

    private Phenotype select(PriorityQueue<Phenotype> q){
        int sum = sumToN(q.size());
        double rand = Math.random();
        int counter = q.size();
        int numerator = 0;
        for(Phenotype p: q){
            numerator += counter;
            //System.out.println((double)numerator/(double)sum);
            if(rand<=(double)numerator/(double)sum)
                return p;
            counter--;
        }
        return null;
    }

    private int sumToN(int N){
        return (N*(N+1))/2;
    }

}

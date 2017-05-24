package ar.edu.itba.sia;

import characters.Archer;
import interfaces.Chromosome;
import interfaces.Crosser;
import interfaces.Phenotype;
import interfaces.Selector;

import java.util.Random;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Evolver {
    Crosser cruze;
    Selector selector;
    Random rand = new Random();
    int N;
    int k;
    Phenotype[] currentGeneration;

    public Evolver(Crosser c,Selector s,int N, int k){
        this.cruze = c;
        this.selector = s;
        this.N = N;
        this.k = k;
    }

    public void randomGeneration(){
        currentGeneration = new Phenotype[N];
        for(int i=0; i<N; i++){
            Chromosome[] data = new Chromosome[Constants.CHROMOSOME_COUNT];
            data[0] = new Height(Math.random()*0.7+1.3);
            for(int j = 1; j<Constants.CHROMOSOME_COUNT;j++){
                data[j] = Constants.VALUES[j-1][rand.nextInt(Constants.ALELO_COUNT)];
            }
            currentGeneration[i] = new Archer(data);
        }
    }

    public Phenotype evolve(){
        if(currentGeneration == null){
            throw new RuntimeException("No phenotypes to evolve");
        }
        int counter = 0;
        printFitness(currentGeneration);
        while(counter<5000) { //COMO TERMINA?
            //ELIJO CANDIDATOS
            Phenotype[] selected = selector.selectPhenotypes(currentGeneration,k);
            //LOS CRUZO
            Phenotype[] newPop = new Phenotype[k];
            for(int i = 0; i<k; i++){
                Phenotype[] aux = cruze.crossover(selected[rand.nextInt(k)],selected[rand.nextInt(k)]);
                if(Math.random() > 0.5) {
                    newPop[i++] = aux[0].mutate();
                    newPop[i] = aux[1].mutate();
                }else{
                    newPop[i++] = aux[0];
                    newPop[i] = aux[1];
                }
            }
            //LOS AGREGO A LA POBLACION
            currentGeneration = newPop;

            //TODO: SELECIONO UNA PARTE DE LA POBLACION
            //TODO: ME FIJO SI SE LLEGO A ALGO LINDO
            counter++;
        }
        printFitness(currentGeneration);
        return null;
    }

    private void printFitness(Phenotype[] pop){
        double total = 0;
        for(Phenotype p: pop){
            double fit = p.getFitness();
            total += fit;
            System.out.print(fit + " | ");
        }
        System.out.println("");
        System.out.println("Avg: " + total/(double)pop.length);
    }

}

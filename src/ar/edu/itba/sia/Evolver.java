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
                data[j] = Constants.VALUES.get(j-1,rand.nextInt(Constants.ALELO_COUNT));
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
        Phenotype best = currentGeneration[0];
        while(counter<50000) { //COMO TERMINA?
            //ELIJO CANDIDATOS
            Phenotype[] selected = selector.selectPhenotypes(currentGeneration,k);
            //LOS CRUZO
            Phenotype[] newPop = new Phenotype[k];
            for(int i = 0; i<k; i++){
                Phenotype[] aux = cruze.crossover(selected[rand.nextInt(k)],selected[rand.nextInt(k)]);
                if(Math.random() < 0.01) {
                    newPop[i++] = aux[0].mutate();
                    newPop[i] = aux[1].mutate();
                    if(newPop[i].getFitness() > best.getFitness()){
                        best = newPop[i];
                    }
                    if(newPop[i-1].getFitness() > best.getFitness()){
                        best = newPop[i-1];
                    }
                }else{
                    newPop[i++] = aux[0];
                    newPop[i] = aux[1];
                    if(newPop[i].getFitness() > best.getFitness()){
                        best = newPop[i];
                    }
                    if(newPop[i-1].getFitness() > best.getFitness()){
                        best = newPop[i-1];
                    }
                }
            }
            //Metodo de reemplazo 2. Best Best reemplazo
            Phenotype[] olds = selector.selectPhenotypes(currentGeneration,N-k);
            int i = 0;
            for(i= 0; i<N-k;i++){
                currentGeneration[i] = olds[i];
            }
            for(i = N-k;i<N;i++){
                currentGeneration[i] = newPop[i - N + k];
            }
            //Metodo de reemplazo 1. Best reemplazo
            //currentGeneration = newPop;

            //TODO: SELECIONO UNA PARTE DE LA POBLACION
            //TODO: ME FIJO SI SE LLEGO A ALGO LINDO
            counter++;

        }
        printFitness(currentGeneration);
        System.out.println("MEJOR: " + best.getFitness());
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

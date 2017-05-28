package ar.edu.itba.sia;

import characters.Archer;
import interfaces.*;
import visual.LineChart;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Evolver {
    private Crosser cruze;
    private Selector selectionSelector;
    private Selector replacementSelector;
    private Mutator mutator;
    private Random rand = new Random();
    private int N;
    private int k;
    private double worst = 0;
    private Phenotype[] currentGeneration;

    LineChart chart;

    public Evolver(Crosser c,Selector ss,Selector rs,Mutator m,int N, int k, LineChart chart){
        this.cruze = c;
        this.selectionSelector = ss;
        this.replacementSelector = rs;
        this.mutator = m;
        this.N = N;
        this.k = k;
        this.chart = chart;
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

    public Phenotype evolve(int maxInter) throws IOException{
        if(currentGeneration == null){
            throw new RuntimeException("No phenotypes to evolve");
        }
        int counter = 0;
        int jump = maxInter / 1000;
        System.out.println(averageFitness(currentGeneration));
        FileWriter fl = new FileWriter("fitness.txt");
        FileWriter fl2 = new FileWriter("bestFitness.txt");
        Phenotype best = currentGeneration[0];
        while(counter<maxInter) { //COMO TERMINA?
            //ELIJO CANDIDATOS
            Phenotype[] selected = selectionSelector.selectPhenotypes(currentGeneration,k);

            //LOS CRUZO
            Phenotype[] newPop = new Phenotype[k];
            for(int i = 0; i<k; i++){
                Phenotype[] aux = cruze.crossover(selected[rand.nextInt(k)],selected[rand.nextInt(k)]);
                newPop[i++] = mutator.mutate(aux[0]);
                newPop[i] = mutator.mutate(aux[1]);
                if(newPop[i].getFitness() > best.getFitness()){
                    best = newPop[i];
                }
                if(newPop[i-1].getFitness() > best.getFitness()){
                    best = newPop[i-1];
                }
            }
            //Metodo de reemplazo 2. Best Best reemplazo
            Phenotype[] olds = replacementSelector.selectPhenotypes(currentGeneration,N-k);
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
            if((counter % jump) == 0){
                fl2.write(best.getFitness() + "\n");

                double avg = averageFitness(currentGeneration);
                fl.write(String.valueOf(avg) + "\n");
                System.out.println(avg);
                dispatchToGraph(best.getFitness(),avg,worst,counter);
            }
            counter++;
        }
        fl2.write(best.getFitness() + "\n");

        fl.write(String.valueOf(averageFitness(currentGeneration)) + "\n");
        System.out.println("MEJOR: " + best.getFitness());
        System.out.println("height: " + best.getHeight());

        fl.close();
        fl2.close();
        return null;
    }

    private double averageFitness(Phenotype[] pop){
        double total = 0;
        worst = Integer.MAX_VALUE;
        for(Phenotype p: pop){
            double fit = p.getFitness();
            if(fit<worst)
                worst = fit;
            total += fit;
        }
        return (total/(double)pop.length);
    }

    private void dispatchToGraph(double max, double avg, double min, int generation){
        if(chart != null) {
            chart.addData(max, "max", generation);
            chart.addData(avg, "avg", generation);
            chart.addData(min, "min", generation);
        }
    }
}

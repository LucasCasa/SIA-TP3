package ar.edu.itba.sia;

import characters.Archer;
import characters.Character;
import characters.CharacterBuilder;
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
    private boolean write_to_file;

    private Crosser cruze;
    private Selector selectionSelector;
    private Selector replacementSelector;
    private Mutator mutator;
    private Random rand = new Random();
    private int N;
    private int k;
    private double worst = 0;
    private int generation = 0;
    private Phenotype[] currentGeneration;
    Phenotype best;

    LineChart chart;

    public Evolver(Crosser c,Selector ss,Selector rs,Mutator m,int N, int k, LineChart chart){
        this.cruze = c;
        this.selectionSelector = ss;
        this.replacementSelector = rs;
        this.mutator = m;
        this.N = N;
        this.k = k;
        this.chart = chart;
        write_to_file = (Config.getInstance().getBoolean("write_to_file"));
    }

    public void randomGeneration(){
        double minHeight = Config.getInstance().getDouble("min_height");
        double maxHeight = Config.getInstance().getDouble("max_height");
        if(minHeight<=0 || maxHeight <=0 || minHeight>maxHeight)
            throw new IllegalArgumentException("Invalid heights");
        currentGeneration = new Phenotype[N];
        for(int i=0; i<N; i++){
            Chromosome[] data = new Chromosome[Constants.CHROMOSOME_COUNT];
            data[0] = new Height(Math.random()*(maxHeight-minHeight)+minHeight);
            for(int j = 1; j<Constants.CHROMOSOME_COUNT;j++){
                data[j] = Constants.VALUES.get(j-1,rand.nextInt(Constants.ALELO_COUNT));
            }
            currentGeneration[i] = CharacterBuilder.getInstance().build(data);
        }
    }

    public Phenotype evolve(EndCondition condition) throws IOException{
        if(currentGeneration == null){
            throw new RuntimeException("No phenotypes to evolve");
        }

        int jump = Config.getInstance().getInteger("jump");

        FileWriter fl = null;
        FileWriter fl2 = null;

        if(write_to_file) {
            fl = new FileWriter("fitness.txt");
            fl2 = new FileWriter("bestFitness.txt");
        }
        best = currentGeneration[0];
        Phenotype[] newPop = new Phenotype[k];
        while(!condition.end(this)) { //COMO TERMINA?
            //ELIJO CANDIDATOS
            Phenotype[] selected = selectionSelector.selectPhenotypes(currentGeneration,k);

            //LOS CRUZO

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
            if((generation % jump) == 0){
                double avg = averageFitness(currentGeneration);
                if(write_to_file) {
                    fl2.write(best.getFitness() + "\n");
                    fl.write(String.valueOf(avg) + "\n");
                }
                //System.out.println(avg);
                dispatchToGraph(best.getFitness(),avg,worst,generation);
            }
            generation++;
        }
        if(write_to_file) {
            fl2.write(best.getFitness() + "\n");
            fl.write(String.valueOf(averageFitness(currentGeneration)) + "\n");
            fl.close();
            fl2.close();
        }
        System.out.println("GENERACION: " + generation);
        System.out.println("MEJOR: " + best);
        return best;
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
            chart.addGeneration(max,avg,min,generation);
        }
    }

    public int getGeneration(){
        return this.generation;
    }

    public Phenotype getBest(){
        return best;
    }
}

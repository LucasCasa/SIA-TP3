package ar.edu.itba.sia;

import interfaces.Crosser;
import interfaces.Mutator;
import interfaces.Phenotype;
import interfaces.Selector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Evolver {
    Crosser cruze;
    Mutator mutator;
    Selector selector;
    Random rand = new Random();
    int k;
    Phenotype[] currentGeneration;

    public Evolver(Crosser c,Mutator m,Selector s,int k){
        this.cruze = c;
        this.mutator = m;
        this.selector = s;
        this.k = k;
    }

    public void randomGeneration(int count){
        currentGeneration = new Phenotype[count];
        k = count;
        //BLA BLA BLA
    }

    public Phenotype evolve(){
        if(currentGeneration == null){
            throw new RuntimeException("No hay fenotipos para evolucionar");
        }
        while(true) { //COMO TERMINA?
            //ELIJO CANDIDATOS
            Phenotype[] selected = selector.selectPhenotypes(currentGeneration,k);
            //LOS CRUZO
            Phenotype[] newPop = new Phenotype[k];
            for(int i = 0; i<k; i++){
                Phenotype[] aux = cruze.crossover(selected[rand.nextInt(k)],selected[rand.nextInt(k)]);
                newPop[i++] = aux[0].mutate();
                newPop[i] = aux[1].mutate();
            }
            //LOS AGREGO A LA POBLACION
            currentGeneration = newPop;

            //TODO: SELECIONO UNA PARTE DE LA POBLACION
            //TODO: ME FIJO SI SE LLEGO A ALGO LINDO

        }
    }

}

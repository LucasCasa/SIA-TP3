package ar.edu.itba.sia;

import interfaces.Crosser;
import interfaces.Mutator;
import interfaces.Phenotype;
import interfaces.Selector;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Evolver {
    Crosser cruze;
    Mutator mutator;
    Selector selector;
    Phenotype[] currentGeneration;

    public Evolver(Crosser c,Mutator m,Selector s){
        this.cruze = c;
        this.mutator = m;
        this.selector = s;
    }

    public void randomGeneration(int count){
        currentGeneration = new Phenotype[count];
        //BLA BLA BLA
    }

    public Phenotype evolve(){
        if(currentGeneration == null){
            throw new RuntimeException("No hay fenotipos para evolucionar");
        }
        while(true) { //COMO TERMINA?

            //TODO: ELIJO CANDIDATOS
            //TODO: LOS CRUZO
            //TODO: LOS AGREGO A LA POBLACION
            //TODO: MUTO
            //TODO: SELECIONO UNA PARTE DE LA POBLACION
            //TODO: ME FIJO SI SE LLEGO A ALGO LINDO

        }
    }

}

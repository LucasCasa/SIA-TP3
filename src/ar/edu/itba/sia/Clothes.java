package ar.edu.itba.sia;

import interfaces.Chromosome;

import java.io.Serializable;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Clothes implements Chromosome,Serializable{
    double[] stats;
    private static final long serialVersionUID = 1L;


    public Clothes(double[] d){
        stats = d;
    }

    public Clothes() {
        stats = new double[5];
    }

    public double[] getStat(){
        return stats;
    }

    @Override
    public void setAtPos(int j, double v) {
        stats[j] = v;
    }

    @Override
    public double getAtPos(int j) {
        return stats[j];
    }
}

package ar.edu.itba.sia;

import interfaces.Chromosome;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Cloth implements Chromosome{
    double[] stats;
    public Cloth(double[] d){
        stats = d;
    }
    public Cloth(){
        stats = new double[5];
    }
    public double[] getStat(){
        return stats;
    }

    @Override
    public void setAtPos(int j, double v) {
        stats[j] = v;
    }
}

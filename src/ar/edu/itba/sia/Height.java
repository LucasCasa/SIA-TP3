package ar.edu.itba.sia;

import interfaces.Chromosome;

/**
 * Created by lcasagrande on 24/05/17.
 */
public class Height implements Chromosome {
    private double height;
    public Height(double height){
        this.height = height;
    }
    @Override
    public void setAtPos(int j, double v) {
        height = v;
    }

    @Override
    public double getAtPos(int j) {
        return height;
    }
}

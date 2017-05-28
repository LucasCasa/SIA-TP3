package ar.edu.itba.sia;

import interfaces.Chromosome;

import java.io.Serializable;

/**
 * Created by lcasagrande on 24/05/17.
 */
public class Height implements Chromosome,Serializable {
    private double height;
    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return height + "\n";
    }
}

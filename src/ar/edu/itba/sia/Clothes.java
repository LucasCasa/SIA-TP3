package ar.edu.itba.sia;

import interfaces.Chromosome;

import java.io.Serializable;

/**
 * Created by lcasagrande on 23/05/17.
 */
public class Clothes implements Chromosome,Serializable{
    double[] stats;
    int id = 0;
    private static final long serialVersionUID = 1L;


    public Clothes(double[] d,int id){
        this.id = id;
        stats = d;
    }

    public Clothes(int i) {
        id = i;
        stats = new double[5];
    }

    public double[] getStat(){
        return stats;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    @Override
    public void setAtPos(int j, double v) {
        stats[j] = v;
    }

    @Override
    public double getAtPos(int j) {
        return stats[j];
    }

    public String toString(){
        return "[id: " + id + ",   \tFuerza: " + stats[0] + ",\tAgilidad: " + stats[1] + ",  \tPericia: " + stats[2] +", \tResistencia: " + stats[3] +",\tVida: " + stats[4] +" ]\n";
    }
}

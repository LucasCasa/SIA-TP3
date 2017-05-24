package ar.edu.itba.sia;

import interfaces.Chromosome;

import java.io.Serializable;

/**
 * Created by lcasagrande on 24/05/17.
 */
public class Data implements Serializable {
    Chromosome[][] data;
    private static final long serialVersionUID = 1L;

    public Data(Chromosome[][] data){
        this.data = data;
    }
    public Chromosome get(int clothes, int id){
        return data[clothes][id];
    }
}

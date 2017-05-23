package characters;

import ar.edu.itba.sia.Constants;
import interfaces.Chromosome;
import interfaces.Phenotype;

/**
 * Created by nkuyumciyan on 23/05/17.
 */
public abstract class Character  implements Phenotype {

    int[] chromosomes;
    double height;

    public Character(int[] data,double height){
        chromosomes = new int[data.length];
        for(int i = 0; i<data.length;i++) {
            chromosomes[i] = data[i];
        }
        this.height = height;
    }

    public Character(double height){
        this.height = height;
        chromosomes = new int[Constants.CHROMOSOME_COUNT];
    }

    @Override
    public int getChromosomeAtLocus(int pos) {
        return chromosomes[pos];
    }

    @Override
    public void setChromosomeAtLocus(int c, int pos) {
        chromosomes[pos] = c;
    }

    @Override
    public int getLocusCount() {
        return 0;
    }

    @Override
    public Phenotype mutate() {
        int mut = (int)(Math.random()*Constants.CHROMOSOME_COUNT);
        chromosomes[mut] = (int)(Math.random()*Constants.ALELO_COUNT);
        return this;
    }

    protected double getAttack(){
        return (getAgility()+getWisdom())*getStrength()*getATM();
    }

    protected double getDefense(){
        return (getWisdom()+getResistance())*getLife()*getDTM();
    }

    private double getAgility() {
        return Math.tanh(0.01*getStat(Constants.AGILITY));
    }

    private double getStrength() {
        return 100*Math.tanh(0.01*getStat(Constants.STRENGTH));
    }

    private double getWisdom() {
        return 0.6*Math.tanh(0.01*getStat(Constants.WISDOM));
    }

    private double getLife() {
        return 100*Math.tanh(0.01*getStat(Constants.LIFE));
    }

    private double getResistance() {
        return Math.tanh(0.01*getStat(Constants.RESISTANCE));
    }

    private double getATM(){
        return 0.5 - Math.pow(3*height-5,4) + Math.pow(3*height - 5,2) + height/2;
    }

    private double getDTM(){
        return 2 + Math.pow(3*height-5,4) - Math.pow(3*height - 5,2) - height/2;
    }

    @Override
    public double getHeight(){
        return height;
    }

    private double getStat(int index){
        double total = 0;
        for(int i=0; i<chromosomes.length; i++){
            total+= Constants.VALUES[i][chromosomes[i]].getAtPos(index);
        }
        return total;
    }
}

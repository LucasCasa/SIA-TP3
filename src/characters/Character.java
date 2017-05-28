package characters;

import ar.edu.itba.sia.Constants;
import ar.edu.itba.sia.Height;
import ar.edu.itba.sia.Main;
import interfaces.Chromosome;
import interfaces.Phenotype;

/**
 * Created by nkuyumciyan on 23/05/17.
 */
public abstract class Character  implements Phenotype {

    private Chromosome[] chromosomes;
    private double height;
    double fitness = -1;

    public Character(Chromosome[] data){
        chromosomes = new Chromosome[data.length];
        if(data.length == Constants.CHROMOSOME_COUNT) {
            System.arraycopy(data,0,chromosomes,0,data.length);
        }
    }

    public Character(double height){
        chromosomes = new Chromosome[Constants.CHROMOSOME_COUNT];
    }

    @Override
    public Chromosome getChromosomeAtLocus(int pos) {
        return chromosomes[pos];
    }

    @Override
    public void setChromosomeAtLocus(Chromosome c, int pos) {
        chromosomes[pos] = c;
    }

    @Override
    public int getLocusCount() {
        return 0;
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
        return 0.5 - Math.pow(3*getHeight()-5,4) + Math.pow(3*getHeight() - 5,2) + getHeight()/2;
    }

    private double getDTM(){
        return 2 + Math.pow(3*getHeight()-5,4) - Math.pow(3*getHeight() - 5,2) - getHeight()/2;
    }

    @Override
    public double getHeight(){
        return chromosomes[0].getAtPos(0);
    }

    private double getStat(int index){
        double total = 0;
        for(int i=1; i<chromosomes.length; i++){
            total+= chromosomes[i].getAtPos(index);
        }
        return total*Constants.MODIF[index];
    }

    public Chromosome[] getChromosomes(){
        return chromosomes;
    }

    @Override
    public String toString() {
        return "{Height: " + chromosomes[0] + "\nClothes: {\nWeapon: " + chromosomes[1] + "Boots: " + chromosomes[2] + "Helmet" + chromosomes[3] + "Gloves" + chromosomes[4]+ "Body" + chromosomes[5] + "]";
    }
}

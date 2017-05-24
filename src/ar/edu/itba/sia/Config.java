package ar.edu.itba.sia;

import crossers.Anular;
import crossers.OnePoint;
import crossers.TwoPoints;
import crossers.Uniform;
import interfaces.Crosser;
import interfaces.Mutator;
import interfaces.Selector;
import selectors.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by nkuyumciyan on 23/05/17.
 */
public class Config {

    private static Config instance = null;
    private Map<String,String> map;
    private Properties p;

    public static Config getInstance(){
        if(instance == null)
            instance = new Config();
        return instance;
    }

    private Config(){
        p = new Properties();
        map = new HashMap<>();
    }

    public void loadConfig(String path) throws IOException{
        InputStream input = new FileInputStream(path);

        p.load(input);

        for (final String name: p.stringPropertyNames()) {
            map.put(name, p.getProperty(name));
        }
    }

    public String getProperty(String prop){
        prop = prop.toLowerCase();
        if(!map.containsKey(prop))
            return null;
        return map.get(prop);
    }

    public Integer getInteger(String prop){
        prop = prop.toLowerCase();
        if(!map.containsKey(prop))
            return null;
        return Integer.parseInt(map.get(prop));
    }

    public Double getDouble(String prop){
        prop = prop.toLowerCase();
        if(!map.containsKey(prop))
            return null;
        return Double.parseDouble(map.get(prop));
    }

    public Crosser getCrosser(){
        String s = map.get("crosser");
        if(s==null)
            throw new IllegalArgumentException("Argument crosser missing");
        s = s.toLowerCase();
        switch (s) {
            case "uniform": return new Uniform(getDouble("pc"));
            case "onepoint": return new OnePoint(getDouble("pc"));
            case "twopoints": return new TwoPoints(getDouble("pc"));
            case "anular": return new Anular(getDouble("pc"));
            default: throw new IllegalArgumentException("Invalid crosser param");
        }
    }

    public Selector getSelectionSelector(){
        String st1 = map.get("selector1");
        String st2 = map.get("selector2");
        if(st1==null || st2 == null)
            throw new IllegalArgumentException("Argument selector1 or 2 missing");
        st1 = st1.toLowerCase();
        st2 = st2.toLowerCase();
        Selector s1;
        Selector s2;
        switch (st1) {
            case "boltzmann": s1 = new Boltzmann(); break;
            case "deterministictourney": s1 =  new DeterministicTourney(); break;
            case "stochastictourney": s1 = new StochasticTourney(); break;
            case "elite": s1 =  new Elite(); break;
            case "ranking": s1 =  new Ranking(); break;
            case "roulette": s1 =  new Roulette(); break;
            case "universal": s1 =  new Universal(); break;
            default: throw new IllegalArgumentException("Invalid selector1 param");
        }
        switch (st2){
            case "boltzmann": s2 = new Boltzmann(); break;
            case "deterministictourney": s2 =  new DeterministicTourney(); break;
            case "stochastictourney": s2 = new StochasticTourney(); break;
            case "elite": s2 =  new Elite(); break;
            case "ranking": s2 =  new Ranking(); break;
            case "roulette": s2 =  new Roulette(); break;
            case "universal": s2 =  new Universal(); break;
            default: throw new IllegalArgumentException("Invalid selector2 param");
        }
        return new MixedSelector(s1,s2,getDouble("a"));
    }
    public Selector getReplacementSelector(){
        String st1 = map.get("selector3");
        String st2 = map.get("selector4");
        if(st1==null || st2 == null)
            throw new IllegalArgumentException("Argument selector1 or 2 missing");
        st1 = st1.toLowerCase();
        st2 = st2.toLowerCase();
        Selector s1;
        Selector s2;
        switch (st1) {
            case "boltzmann": s1 = new Boltzmann(); break;
            case "deterministictourney": s1 =  new DeterministicTourney(); break;
            case "stochastictourney": s1 = new StochasticTourney(); break;
            case "elite": s1 =  new Elite(); break;
            case "ranking": s1 =  new Ranking(); break;
            case "roulette": s1 =  new Roulette(); break;
            case "universal": s1 =  new Universal(); break;
            default: throw new IllegalArgumentException("Invalid selector1 param");
        }
        switch (st2){
            case "boltzmann": s2 = new Boltzmann(); break;
            case "deterministictourney": s2 =  new DeterministicTourney(); break;
            case "stochastictourney": s2 = new StochasticTourney(); break;
            case "elite": s2 =  new Elite(); break;
            case "ranking": s2 =  new Ranking(); break;
            case "roulette": s2 =  new Roulette(); break;
            case "universal": s2 =  new Universal(); break;
            default: throw new IllegalArgumentException("Invalid selector2 param");
        }
        return new MixedSelector(s1,s2,getDouble("b"));
    }
    public Mutator getMutator(){
        return new UniformMutate(getDouble("pm"));
    }

}

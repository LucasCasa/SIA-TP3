package ar.edu.itba.sia;

import crossers.Anular;
import crossers.OnePoint;
import crossers.TwoPoints;
import crossers.Uniform;
import interfaces.Crosser;
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
            case "uniform": return new Uniform();
            case "onepoint": return new OnePoint();
            case "twopoints": return new TwoPoints();
            case "anular": return new Anular();
            default: throw new IllegalArgumentException("Invalid crosser param");
        }
    }

    public Selector getSelector(){
        String s = map.get("selector");
        if(s==null)
            throw new IllegalArgumentException("Argument selector missing");
        s = s.toLowerCase();
        switch (s) {
            case "boltzmann": return new Boltzmann();
            case "deterministictourney": return new DeterministicTourney();
            case "stochastictourney": return new StochasticTourney();
            case "elite": return new Elite();
            case "ranking": return new Ranking();
            case "roulette": return new Roulette();
            case "universal": return new Universal();
            default: throw new IllegalArgumentException("Invalid selector param");
        }
    }

}

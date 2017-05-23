package ar.edu.itba.sia;

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
        if(!map.containsKey(prop))
            return null;
        return map.get(prop);
    }

}

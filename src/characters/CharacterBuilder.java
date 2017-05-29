package characters;

import ar.edu.itba.sia.Config;
import interfaces.Chromosome;

/**
 * Created by nkuyumciyan on 29/05/17.
 */
public class CharacterBuilder {

    private static CharacterBuilder instance = null;
    private int type;

    public static CharacterBuilder getInstance(){
        if(instance==null)
            instance = new CharacterBuilder();
        return instance;
    }

    private CharacterBuilder(){
        String char_type = Config.getInstance().getProperty("character_type").toLowerCase();
        switch (char_type){
            case "archer":      type = 0;
                                break;
            case "assassin":    type = 1;
                                break;
            case "defender":    type = 2;
                                break;
            case "warrior":     type = 3;
                                break;
            default:            throw new IllegalArgumentException("Invalid character type");
        }
    }

    public Character build(Chromosome[] data){
        Character c = null;
        switch(type){
            case 0:     c = new Archer(data);
                        break;
            case 1:     c = new Assassin(data);
                        break;
            case 2:     c = new Defender(data);
                        break;
            case 3:     c = new Warrior(data);
                        break;
        }
        return c;
    }

    public Character build(){
        Character c = null;
        switch(type){
            case 0:     c = new Archer();
                        break;
            case 1:     c = new Assassin();
                        break;
            case 2:     c = new Defender();
                        break;
            case 3:     c = new Warrior();
                        break;
        }
        return c;
    }
}

package pt.isel.mpd.Strategies.primitives;

import pt.isel.mpd.jsonzai.JsonParser;

import java.lang.reflect.Field;

/**
 * Created by Ruben Gomes on 09/04/2015.
 */
public class CharStrategy extends PrimitiveStrategies<Character> {

    @Override
    public Character process(String s, Field field, JsonParser jsonParser){
        return s.charAt(0);
    }
}

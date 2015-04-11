package pt.isel.mpd.Strategies.primitives;

import pt.isel.mpd.jsonzai.JsonParser;

/**
 * Created by Ruben Gomes on 09/04/2015.
 */
public class CharStrategy extends PrimitiveStrategies<Character> {

    @Override
    public Character process(String s, Class<?> c, JsonParser jsonParser){
        return s.charAt(0);
    }
}

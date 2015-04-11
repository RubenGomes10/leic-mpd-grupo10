package pt.isel.mpd.Strategies.primitives;

import pt.isel.mpd.jsonzai.JsonParser;

/**
 * Created by Ruben Gomes on 10/04/2015.
 */
public class BooleanStrategy extends PrimitiveStrategies<Boolean> {

    @Override
    public Boolean process(String s, Class<?> c, JsonParser jsonParser){

        return (s.equals("false"))? false : true;
    }
}


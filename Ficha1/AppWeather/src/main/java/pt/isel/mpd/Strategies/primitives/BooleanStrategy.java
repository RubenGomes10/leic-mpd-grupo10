package pt.isel.mpd.Strategies.primitives;

import pt.isel.mpd.jsonzai.JsonParser;

import java.lang.reflect.Field;

/**
 * Created by Ruben Gomes on 10/04/2015.
 */
public class BooleanStrategy extends PrimitiveStrategies<Boolean> {

    @Override
    public Boolean process(String s, Field field, JsonParser jsonParser){

        return (s.equals("false"))? false : true;
    }
}


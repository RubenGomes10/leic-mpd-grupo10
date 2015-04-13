package pt.isel.mpd.Strategies;

import pt.isel.mpd.jsonzai.JsonParser;

import java.lang.reflect.Field;

/**
 * Created by HS on 08/04/2015.
 */
public interface TypeStrategy <T>{

    T process (String s,Field field, JsonParser jsonParser);
}

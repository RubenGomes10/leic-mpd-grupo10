package pt.isel.mpd.Strategies;

import pt.isel.mpd.jsonzai.JsonParser;

/**
 * Created by HS on 08/04/2015.
 */
public interface TypeStrategy <T>{

    T process (String s,Class<?> c, JsonParser jsonParser);
}

package pt.isel.mpd.Strategies;

/**
 * Created by HS on 08/04/2015.
 */
public interface TypeStrategy <T>{

    T process (String s,Class<?> c);
}

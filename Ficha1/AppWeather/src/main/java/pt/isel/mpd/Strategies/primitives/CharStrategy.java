package pt.isel.mpd.Strategies.primitives;

/**
 * Created by Ruben Gomes on 09/04/2015.
 */
public class CharStrategy extends PrimitiveStrategies<Character> {

    @Override
    public Character process(String s, Class<?> c){
        return s.charAt(0);
    }
}

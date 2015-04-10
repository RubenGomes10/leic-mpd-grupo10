package pt.isel.mpd.Strategies.primitives;

/**
 * Created by HS on 08/04/2015.
 */
public class StringStrategy extends PrimitiveStrategies<String> {


    @Override
    public String process(String s, Class<?> c) {
        return s;
    }
}
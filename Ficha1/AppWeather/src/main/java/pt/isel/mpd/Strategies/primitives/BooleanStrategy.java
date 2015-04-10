package pt.isel.mpd.Strategies.primitives;

/**
 * Created by Ruben Gomes on 10/04/2015.
 */
public class BooleanStrategy extends PrimitiveStrategies<Boolean> {

    @Override
    public Boolean process(String s, Class<?> c){
        return (s.equals("false"))? false : true;
    }
}

package pt.isel.mpd.Strategies.primitives;

import pt.isel.mpd.Strategies.primitives.PrimitiveStrategies;

/**
 * Created by HS on 08/04/2015.
 */
public class IntegerStrategy extends PrimitiveStrategies<Integer> {

    @Override
    public Integer process(String s, Class<?> c) {
        int i=0;
        try {
            i = Integer.parseInt(s);
        }
        catch (NumberFormatException n){
        }

        return (int)i;
    }
}

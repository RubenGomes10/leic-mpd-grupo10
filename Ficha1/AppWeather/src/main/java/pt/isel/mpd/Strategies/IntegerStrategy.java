package pt.isel.mpd.Strategies;

/**
 * Created by HS on 08/04/2015.
 */
public class IntegerStrategy extends PrimitiveStrategies{
    @Override
    public Integer method(String s) {
        int i=0;
        try {
            i = Integer.parseInt(s);
        }
        catch (NumberFormatException n){
        }

        return (int)i;
    }
}

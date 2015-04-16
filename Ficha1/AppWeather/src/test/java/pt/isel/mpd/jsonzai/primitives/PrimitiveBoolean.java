package pt.isel.mpd.jsonzai.primitives;


import java.util.Arrays;
import java.util.List;

/**
 * Created by Ruben Gomes on 16/04/2015.
 */
public class PrimitiveBoolean extends Primitive {

    public boolean key1;
    public boolean key2;
    public boolean key3;


    @Override
    public List<Boolean> getValues() {
        return Arrays.asList(key1,key2,key3);
    }
}

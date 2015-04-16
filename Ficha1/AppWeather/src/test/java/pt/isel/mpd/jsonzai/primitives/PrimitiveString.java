package pt.isel.mpd.jsonzai.primitives;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ruben Gomes on 16/04/2015.
 */
public class PrimitiveString extends Primitive {

    public String key1;
    public String key2;
    public String key3;

    @Override
    public List<String> getValues() {
        return Arrays.asList(key1,key2,key3);
    }
}

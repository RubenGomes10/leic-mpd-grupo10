package pt.isel.mpd.jsonzai.primitives;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ruben Gomes on 16/04/2015.
 */
public class PrimitiveChar extends Primitive {

    public char key1;
    public char key2;
    public char key3;

    @Override
    public List<Character> getValues() {
        return Arrays.asList(key1,key2,key3);
    }
}

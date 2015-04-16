package pt.isel.mpd.jsonzai.primitives;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruben Gomes on 16/04/2015.
 */
public class PrimitiveInt extends Primitive {

    public int key1;
    public int key2;
    public int key3;

    @Override
    public List<Integer> getValues() {
        ArrayList res =  new ArrayList<Integer>();
                res.add(key1);
                res.add(key2);
                res.add(key3);
         return res;
    }
}

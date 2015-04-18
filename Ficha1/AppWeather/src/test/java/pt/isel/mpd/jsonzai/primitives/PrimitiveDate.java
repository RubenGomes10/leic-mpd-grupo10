package pt.isel.mpd.jsonzai.primitives;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ruben Gomes on 16/04/2015.
 */
public class PrimitiveDate extends Primitive {

    public Date key1;
    public Date key2;
    public Date key3;

    @Override
    public List<String> getValues() {
        ArrayList res =  new ArrayList<String>();
        res.add(key1);
        res.add(key2);
        res.add(key3);
        return res;

    }
}

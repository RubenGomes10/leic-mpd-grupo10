package pt.isel.mpd.jsonzai.primitives;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ruben Gomes on 16/04/2015.
 */
public class PrimitiveDate extends Primitive {

    public Date key1;


    @Override
    public List<Date> getValues() {
        return Arrays.asList(key1);
    }
}

package pt.isel.mpd.jsonzai.objects;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ruben Gomes on 16/04/2015.
 */
public class TestObject extends MyObject {

    public int value;
    public char character;
    public boolean state;
    public String str;

    @Override
    public List<Object> getValues() {
        return Arrays.asList(value,character,state,str);
    }
}

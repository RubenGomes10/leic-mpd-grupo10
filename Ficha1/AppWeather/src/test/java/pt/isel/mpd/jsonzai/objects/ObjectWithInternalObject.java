package pt.isel.mpd.jsonzai.objects;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ruben Gomes on 16/04/2015.
 */
public class ObjectWithInternalObject extends MyObject{

    public String course;
    public InternalObject person ;

    @Override
    public List<Object> getValues() {
        return Arrays.asList(course,person.name, person.number, person.isStudent);
    }
}

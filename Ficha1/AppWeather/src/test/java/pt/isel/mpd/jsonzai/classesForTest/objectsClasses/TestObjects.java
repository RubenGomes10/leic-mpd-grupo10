package pt.isel.mpd.jsonzai.classesForTest.objectsClasses;

import pt.isel.mpd.jsonzai.ISupplier;

import java.util.ArrayList;
import java.util.List;


public abstract class TestObjects implements ISupplier<String> {

    public List<Object> values;

    public TestObjects(int value, char character, boolean state, String str){
        values = new ArrayList<>();
        values.add(value);
        values.add(character);
        values.add(state);
        values.add(str);
    }

    public TestObjects(String course,String name,int number,boolean isStudent){
        values = new ArrayList<>();
        values.add(course);
        values.add(name);
        values.add(number);
        values.add(isStudent);
    }
}

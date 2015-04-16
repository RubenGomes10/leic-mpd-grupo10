package pt.isel.mpd.jsonzai.classesForTest.objectsClasses;

/**
 * Created by Ruben Gomes on 16/04/2015.
 */
public class TestSimpleObject extends TestObjects {

    public TestSimpleObject(int value, char character, boolean state, String str) {
        super(value,character,state,str);

    }

    @Override
    public String getString() {
        return "{ \n" +
                "\"value\" = "+ values.get(0) +", \n" +
                "\"character\" = "+"\""+ values.get(1) +"\"" +", \n" +
                "\"state\" = "+ values.get(2) +", \n" +
                "\"str\" = "+"\""+ values.get(3) +"\""+ " \n +" +
                "}";
    }

    @Override
    public String getStringWithSpaces() {
        return null;
    }

}

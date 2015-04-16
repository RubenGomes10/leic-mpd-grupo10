package pt.isel.mpd.jsonzai.classesForTest.primitivesClasses;

/**
 * Created by Ruben Gomes on 16/04/2015.
 */
public class IntTest extends TestPrimitives {

    public IntTest(int value1, int value2, int value3) {
        super(value1, value2, value3);
    }


    @Override
    public String getString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        sb.append("{").append("\n");
        for(; i<3; ++i) {
            sb.append(" \"key").append(i).append("\" = ")
                    .append(values.get(i - 1)).append(", \n");
        }
        sb.append(" \"key").append(i).append("\" = ")
                .append(values.get(i - 1)).append("\n }");

        return sb.toString();
    }

    @Override
    public String getStringWithSpaces() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        sb.append("\t").append("{ \n");
        for(; i<3; ++i) {
            sb.append(" \t  \"key").append(i).append("\" \t = ")
                    .append("\t").append(values.get(i-1)).append("\t , \n");
        }
        sb.append(" \t  \"key").append(i).append("\" \t = ")
                .append(values.get(i - 1)).append("\t \n }");

        return sb.toString();
    }
}

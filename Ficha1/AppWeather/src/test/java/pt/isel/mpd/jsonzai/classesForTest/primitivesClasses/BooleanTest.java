package pt.isel.mpd.jsonzai.classesForTest.primitivesClasses;

/**
 * Created by Ruben Gomes on 16/04/2015.
 */
public class BooleanTest extends TestPrimitives<Boolean> {

    public BooleanTest(boolean flag1, boolean flag2, boolean flag3) {
        super(flag1,flag2,flag3);
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

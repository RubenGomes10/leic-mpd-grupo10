package pt.isel.mpd.jsonzai.classesForTest.primitivesClasses;

import pt.isel.mpd.jsonzai.ISupplier;

import java.util.ArrayList;
import java.util.List;


public abstract class TestPrimitives<T> implements ISupplier<String> {

    protected List<T> values;

    public TestPrimitives(T value1, T value2, T value3){
        this.values = new ArrayList<T>();
        values.add(value1);
        values.add(value2);
        values.add(value3);
    }

    @Override
    public String getString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        sb.append("{").append("\n");
        for(; i<3; ++i) {
            sb.append(" \"key").append(i).append("\" = ")
                    .append("\"").append(values.get(i - 1))
                    .append("\"").append(", \n");
        }
        sb.append(" \"key").append(i).append("\" = ")
                .append("\"").append(values.get(i - 1))
                .append("\"").append("\n }");

        return sb.toString();
    }

    @Override
    public String getStringWithSpaces() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        sb.append("\t").append("{ \n");
        for(; i<3; ++i) {
            sb.append(" \t  \"key").append(i).append("\" \t = ")
                    .append("\t").append("\"").append(values.get(i-1))
                    .append("\"").append("\t , \n");
        }
        sb.append(" \t  \"key").append(i).append("\" \t = ")
                .append("\t").append("\"").append(values.get(i - 1))
                .append("\"").append("\t \n }");

        return sb.toString();
    }
}

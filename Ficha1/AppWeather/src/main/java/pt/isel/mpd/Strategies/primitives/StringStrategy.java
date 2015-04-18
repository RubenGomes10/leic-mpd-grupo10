package pt.isel.mpd.Strategies.primitives;

import pt.isel.mpd.Strategies.TypeStrategy;
import pt.isel.mpd.jsonzai.JsonParser;

import java.lang.reflect.Field;
import java.util.Date;

public class StringStrategy<T> implements TypeStrategy<T> {

    @Override
    public void process(String src, T instance, Field field, JsonParser jsonParser) throws Exception {
        int pos = jsonParser.getPos();
        int currentPos = pos;
        char character = src.charAt(++currentPos);

        while(character != '\"'){
            character = src.charAt(++currentPos);
        }

        jsonParser.setPos(currentPos + 1);
        if (field!= null) {
            if (Date.class.isAssignableFrom(field.getType())) {
                String s = src.substring(pos + 1, currentPos);
                Date date = jsonParser.getDateFormat().parse(s);
                field.set(instance, date);
            } else if (char.class.isAssignableFrom(field.getType())) {
                field.set(instance, src.charAt(pos + 1));
            } else
                field.set(instance, src.substring(pos + 1, currentPos));
        }
    }
}
package pt.isel.mpd.Strategies.primitives;


import pt.isel.mpd.Strategies.TypeStrategy;
import pt.isel.mpd.jsonzai.JsonParser;

import java.lang.reflect.Field;

public class BooleanStrategy<T> implements TypeStrategy<T>{

    @Override
    public void process(String src, T instance, Field field, JsonParser jsonParser) throws Exception {

        int pos = jsonParser.getPos();
        int currentPos = pos;
        char character = src.charAt(currentPos);

        while(character != ',' && character != '}'
                && character != ']' && character != '\n'){

            character = src.charAt(++currentPos);
        }
        jsonParser.setPos(currentPos);

        boolean b = Boolean.parseBoolean(src.substring(pos, currentPos));
        if (field!= null) field.set(instance, b);
    }
}
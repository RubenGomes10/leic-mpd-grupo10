package pt.isel.mpd.Strategies.primitives;

import pt.isel.mpd.Strategies.TypeStrategy;
import pt.isel.mpd.jsonzai.JsonParser;

import java.lang.reflect.Field;
import java.util.function.Predicate;

/**
 * Created by HS on 08/04/2015.
 */
public class IntegerStrategy<T> implements TypeStrategy<T> {

    @Override
    public void process(String src, T instance, Field field, JsonParser jsonParser) throws Exception {
//        jsonParser.consume(s,jsonParser::consumeSpaces);
        int pos = jsonParser.getPos();
        int lastNumberPos=pos;
        int i=0;
        char character = src.charAt(lastNumberPos);

        while (isValid(character,(Character c) -> c >= '0' && c<='9')){
            character = src.charAt(++lastNumberPos);
        }
        jsonParser.setPos(lastNumberPos);

        i = Integer.parseInt(src.substring(pos,lastNumberPos));

        if (field!= null) field.set(instance, i);
    }

    public <T> boolean isValid(T obj,Predicate<T> predicate){
        return predicate.test(obj);
    }
}

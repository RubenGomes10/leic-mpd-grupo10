package pt.isel.mpd.Strategies.primitives;

import pt.isel.mpd.jsonzai.JsonParser;

import java.lang.reflect.Field;
import java.util.function.Predicate;

/**
 * Created by HS on 08/04/2015.
 */
public class IntegerStrategy extends PrimitiveStrategies<Integer> {

    @Override
    public Integer process(String s, Field field, JsonParser jsonParser) {
//        jsonParser.consume(s,jsonParser::consumeSpaces);
        int pos = jsonParser.getPos();
        int lastNumberPos=pos;
        int i=0;
        char character = s.charAt(lastNumberPos);

        while (isValid(character,(Character c) -> c >= '0' && c<='9')){
            character = s.charAt(++lastNumberPos);
        }
        jsonParser.setPos(lastNumberPos);

        try {
            i = Integer.parseInt(s.substring(pos,lastNumberPos));
        }
        catch (NumberFormatException n){
            n.printStackTrace();
        }

        return (int)i;
    }

    public <T> boolean isValid(T obj,Predicate<T> predicate){
        return predicate.test(obj);
    }
}

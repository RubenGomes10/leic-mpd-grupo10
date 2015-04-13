package pt.isel.mpd.Strategies.objects;

import pt.isel.mpd.Strategies.TypeStrategy;
import pt.isel.mpd.jsonzai.JsonParser;

import java.lang.reflect.Field;

/**
 * Created by HS on 10/04/2015.
 */
    public class ObjectStrategy<T> implements TypeStrategy<T> {

    @Override
    public T process(String s, Field field, JsonParser jsonParser) { // 2 erro nao estavamos a incrementar a posicao na string da chamada anterior

        Class<T> classField = (Class<T>) field.getType();
        T newInstance = null;
        try {
            newInstance = jsonParser.toObject(s, classField);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return newInstance;
    }
}

package pt.isel.mpd.Strategies.objects;

import pt.isel.mpd.Strategies.TypeStrategy;
import pt.isel.mpd.jsonzai.JsonParser;

import java.lang.reflect.Field;

/**
 * Created by HS on 10/04/2015.
 */
    public class ObjectStrategy<T> implements TypeStrategy<T> {

    @Override
    public void process(String src, T instance, Field field, JsonParser jsonParser) throws Exception { // 2 erro nao estavamos a incrementar a posicao na string da chamada anterior

        Class<T> classField = (Class<T>) field.getType();

        if (field!= null) field.set(instance, jsonParser.toObject(src, classField));

    }
}

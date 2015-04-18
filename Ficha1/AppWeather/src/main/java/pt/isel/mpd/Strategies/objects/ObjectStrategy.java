package pt.isel.mpd.Strategies.objects;

import pt.isel.mpd.Strategies.TypeStrategy;
import pt.isel.mpd.jsonzai.JsonParser;

import java.lang.reflect.Field;

    public class ObjectStrategy<T> implements TypeStrategy<T> {

    @Override
    public void process(String src, T instance, Field field, JsonParser jsonParser) throws Exception {
        Class<T> fieldType = (Class<T>) field.getType();

        if (field!= null) field.set(instance, jsonParser.toObject(src, fieldType));

    }
}

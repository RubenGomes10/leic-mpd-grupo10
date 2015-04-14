package pt.isel.mpd.Strategies.lists;

import pt.isel.mpd.Strategies.TypeStrategy;
import pt.isel.mpd.jsonzai.JsonParser;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

public class ListStrategy<T> implements TypeStrategy<T> {

    @Override
    public void process(String src, T instance, Field field, JsonParser jsonParser) throws Exception {
        ParameterizedType pType = (ParameterizedType)field.getGenericType();
        Class<T> classField = (Class<T>) pType.getActualTypeArguments()[0];

        if (field!= null) field.set(instance, jsonParser.toList(src, classField));

    }
}

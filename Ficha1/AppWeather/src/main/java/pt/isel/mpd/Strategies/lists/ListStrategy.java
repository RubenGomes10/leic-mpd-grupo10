package pt.isel.mpd.Strategies.lists;

import pt.isel.mpd.Strategies.TypeStrategy;
import pt.isel.mpd.jsonzai.JsonParser;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Ruben Gomes on 09/04/2015.
 */
public class ListStrategy<T> implements TypeStrategy<T> {

    @Override
    public T process(String s, Field field, JsonParser jsonParser) {

        ParameterizedType pType = (ParameterizedType)field.getGenericType();
        Class<T> classField = (Class<T>) pType.getActualTypeArguments()[0];

        return (T) jsonParser.toList(s,classField);
    }
}

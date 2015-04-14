package pt.isel.mpd.Strategies;

import pt.isel.mpd.jsonzai.JsonParser;

import java.lang.reflect.Field;

public interface TypeStrategy <T>{

    void process(String src, T instance, Field field, JsonParser jsonParser) throws Exception;
}

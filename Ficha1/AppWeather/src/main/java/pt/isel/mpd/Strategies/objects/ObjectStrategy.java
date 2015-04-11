package pt.isel.mpd.Strategies.objects;

import pt.isel.mpd.Strategies.TypeStrategy;
import pt.isel.mpd.jsonzai.JsonParser;

/**
 * Created by HS on 10/04/2015.
 */
    public class ObjectStrategy<T> implements TypeStrategy{

    @Override
    public T process(String s, Class c, JsonParser jsonParser)  {
        try {
            return (T) jsonParser.toObject(s,c);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


}

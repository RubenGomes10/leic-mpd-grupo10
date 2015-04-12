package pt.isel.mpd.Strategies.objects;

import pt.isel.mpd.Strategies.TypeStrategy;
import pt.isel.mpd.jsonzai.JsonParser;

/**
 * Created by HS on 10/04/2015.
 */
    public class ObjectStrategy<T> implements TypeStrategy{

    @Override
    public T process(String s, Class c, JsonParser jsonParser)  { // 2 erro nao estavamos a incrementar a posicao na string da chamada anterior
        try {
            JsonParser nextParseObject = new JsonParser();
            T internalObject = (T) nextParseObject.toObject(s.substring(jsonParser.getPos()),c);
            jsonParser.setPos(jsonParser.getPos() + nextParseObject.getPos());
            return internalObject;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


}

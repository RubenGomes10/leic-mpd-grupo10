package pt.isel.mpd.Strategies.primitives;

import pt.isel.mpd.Strategies.TypeStrategy;
import pt.isel.mpd.jsonzai.JsonParser;

import java.lang.reflect.Field;


/**
 * Created by HS on 08/04/2015.
 */
public class StringStrategy<T> implements TypeStrategy<T> {

    @Override
    public void process(String src, T instance, Field field, JsonParser jsonParser) throws Exception {
       //Ficou equivalente ao do IntegerStrategy
       //Fiz isto para resolver o bug de par chave valor ( key : " ",), ou seja, o valor ser vazio;
       // TODO implementar mais tarde um método genérico para ser chamado aqui com expressão lambda!

        int pos = jsonParser.getPos();
        int currentPos = pos;
        char character = src.charAt(++currentPos);

        while(character != '\"'){
            character = src.charAt(++currentPos);
        }
        jsonParser.setPos(currentPos + 1);

        if (field!= null) field.set(instance, src.substring(pos + 1, currentPos));
    }
}